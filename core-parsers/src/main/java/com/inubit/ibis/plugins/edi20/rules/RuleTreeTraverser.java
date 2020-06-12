package com.inubit.ibis.plugins.edi20.rules;

import com.inubit.ibis.plugins.edi20.rules.interfaces.RuleToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleBaseToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegment;
import com.inubit.ibis.plugins.edi20.rules.tokens.Loop;

import java.util.List;
import java.util.Optional;

class RuleTreeTraverser {

    public Optional<EDIRuleSegment> findNextSegment(
            final EDIRuleBaseToken ruleToken,
            final String segmentID) {
//        logMessage(String.format("Next segment: %s - start from: %s", segmentID, ruleToken.getID()));

        return traverseForward(ruleToken, segmentID);
    }

    private Optional<EDIRuleSegment> traverseForward(
            final EDIRuleBaseToken ruleToken,
            final String segmentID) {
        final Optional<EDIRuleSegment> child = traverseChildrenOrSelf(ruleToken, segmentID);
        if (child.isPresent()) {
            return child;
        }

//        logMessage(String.format("No child found. rule token: %s", ruleToken.getID()));

        final Optional<EDIRuleSegment> sibling = traverseSiblings(ruleToken, segmentID);
        if (sibling.isPresent()) {
            return sibling;
        }

//        logMessage(String.format("No sibling found. rule token: %s", ruleToken.getID()));

        return traverseParents(ruleToken, segmentID);
    }

    private Optional<EDIRuleSegment> traverseParents(
            final EDIRuleBaseToken ruleToken,
            final String segmentID) {
        if (!ruleToken.hasParent()) {
            return Optional.empty();
        }

//        logMessage(String.format("traverseParents from: %s", ruleToken.getID()));

        final EDIRuleBaseToken parent = (EDIRuleBaseToken) ruleToken.getParent();
        final Optional<EDIRuleSegment> sibling = traverseSiblings(parent, segmentID);
        if (sibling.isPresent()) {
            return sibling;
        }
        return traverseParents(parent, segmentID);
    }

    private Optional<EDIRuleSegment> traverseSiblings(
            final EDIRuleBaseToken ruleToken,
            final String segmentID) {
        if (!ruleToken.hasParent()) {
            return Optional.empty();
        }

//        logMessage(String.format("traverseSiblings from: %s", ruleToken.getID()));

        final EDIRuleBaseToken parent = (EDIRuleBaseToken) ruleToken.getParent();
        final int index = parent.getIndexOfChild(ruleToken);

//        logMessage(String.format("Child %s has index: %d", ruleToken.getID(), index));

        if (index >= 0) {
            final List<RuleToken> children = parent.getChildren();
            for (int i = index + 1; i < children.size(); i++) {
                final EDIRuleBaseToken child = (EDIRuleBaseToken) children.get(i);

//                logMessage(String.format("Next child to traverse: %s", child.getID()));

                Optional<EDIRuleSegment> segment = traverseChildrenOrSelf(child, segmentID);
                if (segment.isPresent()) {
                    return segment;
                }
            }
        }

        return Optional.empty();
    }

    private Optional<EDIRuleSegment> traverseChildrenOrSelf(
            final EDIRuleBaseToken ruleToken,
            final String segmentID) {
        final Optional<EDIRuleSegment> self = checkSegment(ruleToken, segmentID);
        if (self.isPresent()) {
            return self;
        }

        final List<RuleToken> children = ruleToken.getChildren();
        for (final RuleToken child : children) {
//            logMessage(String.format("AbstractEDIRule.traverseChildren(%s): %s", ruleToken.getID(), child.getID()));

            final Optional<EDIRuleSegment> segment = checkSegment((EDIRuleBaseToken) child, segmentID);
            if (segment.isPresent()) {
                return segment;
            }

            final Optional<EDIRuleSegment> childSegment = traverseChildrenOrSelf((EDIRuleBaseToken) child, segmentID);
            if (childSegment.isPresent()) {
                return childSegment;
            }
        }
        return Optional.empty();
    }

    private Optional<EDIRuleSegment> checkSegment(
            final EDIRuleBaseToken ruleTokenToStartFrom,
            final String segmentID) {
        if (ruleTokenToStartFrom instanceof EDIRuleSegment) {
            final EDIRuleSegment segment = (EDIRuleSegment) ruleTokenToStartFrom;
            if (ruleTokenToStartFrom.getID().equals(segmentID)) {
                if (!segment.isLoopLimitReached()) {
                    return Optional.of(segment);
                } else {
                    final Loop loop = segment.getLoop();

                    final String message = String.format("WARNING: segment for ID %s found but elements loop limit exceeded (%d/%d).", segmentID, segment.getCurrentLoopCount(), loop.isInfinite() ? -1 : loop.getValueAsInteger());
                    logMessage(message);
                }
            }
        }
        return Optional.empty();
    }

    private void logMessage(final String message) {
        System.out.println(message);
    }
}
