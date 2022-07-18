package com.inubit.ibis.plugins.edi20.rules;

import org.dom4j.Document;
import org.dom4j.Element;

import com.inubit.ibis.plugins.edi20.rules.interfaces.RuleToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleElement;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleRoot;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegment;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegmentGroup;
import com.inubit.ibis.plugins.edi20.rules.tokens.hwfpe.HwfpeRuleTokenFactory;
import com.inubit.ibis.utils.EDIException;

public abstract class AbstractHWFPERule extends AbstractEDIRule {

    /**
     * @param ruleDocument rule document
     * @throws EDIException if the given rule document is not a valid EDI rule document
     */
    protected AbstractHWFPERule(final Document ruleDocument) throws EDIException {
        super(ruleDocument);
    }

    @Override
    protected RuleToken getRuleToken(final Element element) {
        return new HwfpeRuleTokenFactory().createInstance(element);
    }

    @Override
    public String getLayout() {
        return "hwfpe";
    }

    private EDIRuleSegment findNextRuleSegment(final RuleToken ruleToken) throws EDIException {
        final String message = String.format("findNextRuleSegment(): ruleToken=" + ruleToken);
        logMessage(message);

        if (ruleToken instanceof EDIRuleRoot) {
            return findNextRuleSegmentFromRoot((EDIRuleRoot) ruleToken);
        }
        if (ruleToken instanceof EDIRuleSegmentGroup) {
            return findNextRuleSegmentFromSegmentGroup((EDIRuleSegmentGroup) ruleToken);
        }
        if (ruleToken instanceof EDIRuleSegment) {
            return findNextRuleSegmentFromSegment((EDIRuleSegment) ruleToken);
        }
        if (ruleToken instanceof EDIRuleElement) {
            return findNextRuleSegmentFromElement((EDIRuleElement) ruleToken);
        }
        throw new EDIException("No next rule segment found!");
    }

    private EDIRuleSegment findNextRuleSegmentFromElement(final EDIRuleElement ruleElement) throws EDIException {
        final RuleToken nextRuleToken = RuleUtil.getParentFollowingSibling(ruleElement);

        final String message = String.format("findNextRuleSegmentFromElement(): nextRuleToken=%s", nextRuleToken);
        logMessage(message);

        return findNextRuleSegment(nextRuleToken);
    }

    private EDIRuleSegment findNextRuleSegmentFromSegment(final EDIRuleSegment ruleSegment) {
        //        if (ruleSegment.equals(getCurrentRuleToken())) {
        //            if (ruleSegment.canLoop()) {
        //                ruleSegment.looped();
        //                return ruleSegment;
        //            }
        //            return findNextRuleSegment(RuleUtil.getFollowingSibling(ruleSegment));
        //        }
        return ruleSegment;
    }

    private EDIRuleSegment findNextRuleSegmentFromSegmentGroup(final EDIRuleSegmentGroup ruleSegmentGroup) throws EDIException {
        if (ruleSegmentGroup.canLoop()) {
            final RuleToken nextRuleToken = RuleUtil.getChildOrFollowingSibling(ruleSegmentGroup);

            final String message = String.format("findNextRuleSegmentFromSegmentGroup(): nextRuleToken=%s",
                    nextRuleToken);
            logMessage(message);

            return findNextRuleSegment(nextRuleToken);
        }
        final RuleToken nextRuleToken = RuleUtil.getFollowingSibling(ruleSegmentGroup);

        final String message = String.format("findNextRuleSegmentFromSegmentGroup(): nextRuleToken=%s", nextRuleToken);
        logMessage(message);

        return findNextRuleSegment(nextRuleToken);
    }

    private EDIRuleSegment findNextRuleSegmentFromRoot(final EDIRuleRoot ruleRoot) throws EDIException {
        final RuleToken nextRuleToken = RuleUtil.getChildOrFollowingSibling(ruleRoot);

        final String message = String.format("findNextRuleSegmentFromRoot(): nextRuleToken=%s", nextRuleToken);
        logMessage(message);

        return findNextRuleSegment(nextRuleToken);
    }

    private void logMessage(final String message) {
//        System.out.println(message);
    }
}
