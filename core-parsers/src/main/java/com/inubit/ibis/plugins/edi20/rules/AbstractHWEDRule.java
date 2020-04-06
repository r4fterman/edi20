package com.inubit.ibis.plugins.edi20.rules;

import com.inubit.ibis.plugins.edi20.rules.interfaces.IRuleToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleBaseToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleCompositeElement;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleElement;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleRoot;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegment;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegmentGroup;
import com.inubit.ibis.plugins.edi20.rules.tokens.hwed.HwedRuleTokenFactory;
import com.inubit.ibis.plugins.edi20.scanners.IToken;
import com.inubit.ibis.utils.InubitException;
import com.inubit.ibis.utils.XPathUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import java.util.List;

/**
 * @author r4fter
 */
public abstract class AbstractHWEDRule extends AbstractEDIRule {

    /**
     * Constructor creates HWED rule from the given rule document.
     *
     * @param ediRuleDocument
     *         EDI rule document
     * @throws InubitException
     *         if the given rule document is not a valid EDI rule document
     */
    public AbstractHWEDRule(final Document ediRuleDocument) throws InubitException {
        super(ediRuleDocument);
    }

    @Override
    protected EDIRuleRoot createRootElement(final Document ruleDocument) {
        return (EDIRuleRoot) HwedRuleTokenFactory.getInstance(ruleDocument.getRootElement());
    }

    @Override
    public String getLayout() {
        return "hwed";
    }

    /**
     * Method returns the next segment for the given ID.
     *
     * @param segmentID
     *         segment ID
     * @return segment or <code>null</code> if segment is conditional and does
     * not match the given segment ID
     * @throws InubitException
     *         if segment is mandatory and does not match the given segment ID
     */
    public EDIRuleSegment nextSegment(final String segmentID) throws InubitException {
        // IRuleToken currentRuleToken = getCurrentRuleToken();
        // EDIRuleSegment segment = parseUntilNextSegment(currentRuleToken, segmentID);
        // markLoopOnSegment(segment, segmentID);
        // setCurrentRuleToken(segment);
        // return segment;
        return null;
    }

    /**
     * @param currentRuleToken
     *         current rule token
     * @param segmentID
     *         segment ID
     * @return segment or <code>null</code> if segment is conditional and does
     * not match the given segment ID
     * @throws InubitException
     *         if segment is mandatory and does not match the given segment ID
     */
    private EDIRuleSegment parseUntilNextSegment(
            final IRuleToken currentRuleToken,
            final String segmentID) throws InubitException {
        System.out.println("AbstractHWEDRule.parseUntilNextSegment(" + currentRuleToken.getID() + "): search for segment [" + segmentID + "] ...");
        if (currentRuleToken instanceof EDIRuleSegmentGroup) {
            final EDIRuleSegmentGroup ruleSegmentGroup = (EDIRuleSegmentGroup) currentRuleToken;
            System.out.println("AbstractHWEDRule.parseUntilNextSegment(): checked=" + ruleSegmentGroup.isChecked());
            if (ruleSegmentGroup.isChecked()) {
                if (ruleSegmentGroup.isMandatory()) {
                    throw new InubitException("Found mandatory segment group [" + ruleSegmentGroup + "] while trying to find next segment ["
                            + segmentID + "]!");
                }
            } else if (ruleSegmentGroup.isInProgress()) {
                ruleSegmentGroup.setChecked();
                System.out.println("AbstractHWEDRule.parseUntilNextSegment(): next segment group...");
                return parseUntilNextSegment(RuleUtil.getParentFollowingSibling(ruleSegmentGroup), segmentID);
            } else {
                ruleSegmentGroup.setInProgress();
            }

            IRuleToken childToken = ruleSegmentGroup.nextChildren();
            if (childToken != null) {
                return parseUntilNextSegment(childToken, segmentID);
            }
            throw new InubitException("Segment [" + segmentID + "] not found!");
        }
        if (currentRuleToken instanceof EDIRuleSegment) {
            final EDIRuleSegment ruleSegment = (EDIRuleSegment) currentRuleToken;
            if (ruleSegment.getID().equals(segmentID)) {
                System.out.println("AbstractHWEDRule.parseUntilNextSegment(): rseg=" + ruleSegment);
                return ruleSegment;
            }
            // if (ruleSegment.isMandatory()) {
            // throw new InubitException("Found mandatory segment [" + ruleSegment + "] while trying to find next segment [" + segmentID + "]!");
            // }
            final EDIRuleSegment segment = parseUntilNextSegment(RuleUtil.getFollowingSibling(ruleSegment), segmentID);
            if (segment == null) {
                throw new InubitException("Segment [" + segmentID + "] not found!");
            }
            System.out.println("AbstractHWEDRule.parseUntilNextSegment(): seg=" + segment);
            return segment;
        }
        if (currentRuleToken instanceof EDIRuleCompositeElement) {
            final EDIRuleCompositeElement ruleCompositeElement = (EDIRuleCompositeElement) currentRuleToken;
            if (ruleCompositeElement.isMandatory()) {
                throw new InubitException("Found mandatory composite element [" + ruleCompositeElement + "] while trying to find next segment ["
                        + segmentID + "]!");
            }
            return parseUntilNextSegment(RuleUtil.getFollowingSibling(ruleCompositeElement), segmentID);
        }
        if (currentRuleToken instanceof EDIRuleElement) {
            final EDIRuleElement ruleElement = (EDIRuleElement) currentRuleToken;
            if (ruleElement.isMandatory()) {
                throw new InubitException("Found mandatory element [" + ruleElement + "] while trying to find next segment [" + segmentID + "]!");
            }
            return parseUntilNextSegment(RuleUtil.getFollowingSibling(ruleElement), segmentID);
        }
        throw new InubitException("Unknown rule token [" + currentRuleToken + "]!");
    }

    private void markLoopOnSegment(
            final EDIRuleSegment segment,
            final String segmentID) throws InubitException {
        if (segment == null) {
            throw new InubitException("Segment [" + segmentID + "] not found!");
        }
        if (segment.isLoopLimitReached()) {
            throw new InubitException("Loop limit (" + segment.getLoop() + ") for segment [" + segment + "] reached ("
                    + segment.getCurrentLoopCount() + ")!");
        }
        segment.looped();
    }

    /**
     * @param segmentID
     *         segment ID to check
     * @return <code>true</code> if this rule contains at least one further
     * segment with the given ID,
     * <code>false</code> otherwise
     */
    public boolean containsSegment(final String segmentID) {
        return containsSegment(segmentID, null);
    }

    /**
     * @param segmentID
     *         segment ID to check
     * @param startFromToken
     *         rule token to start search from
     * @return <code>true</code> if this rule contains at least one further
     * segment with the given ID,
     * <code>false</code> otherwise
     */
    public boolean containsSegment(
            final String segmentID,
            final EDIRuleBaseToken startFromToken) {
        Element startElement = getRootElement().getElement();
        if (startFromToken != null) {
            startElement = startFromToken.getElement();
        }

        final String xPath = getChildSegmentsXPath(segmentID);
        final List<Node> segments = XPathUtil.evaluateXPathAsNodeList(xPath, startElement);
        if (segments.size() > 0) {
            return true;
        }

        if (startFromToken != null) {
            final EDIRuleBaseToken parentToken = (EDIRuleBaseToken) startFromToken.getParent();
            if (parentToken != null) {
                return containsSegment(segmentID, parentToken);
            }
        }
        return segments.size() == 1;
    }

    private String getChildSegmentsXPath(final String segmentID) {
        return "descendant-or-self::Segment[@id='" + segmentID + "']";
    }

    public EDIRuleBaseToken nextElement() throws InubitException {
        // System.out.println("AbstractHWEDRule.nextElement(): current=" + getCurrentRuleToken());
        // EDIRuleBaseToken newCurrentRuleToken = parseUntilNextElement(getCurrentRuleToken());
        // setCurrentRuleToken(newCurrentRuleToken);
        // return newCurrentRuleToken;
        return null;
    }

    private EDIRuleBaseToken parseUntilNextElement(final IRuleToken currentRuleToken) throws InubitException {
        if (currentRuleToken instanceof EDIRuleElement) {
            return (EDIRuleElement) currentRuleToken;
        }
        if (currentRuleToken instanceof EDIRuleBaseToken) {
            final EDIRuleBaseToken ruleBaseToken = (EDIRuleBaseToken) currentRuleToken;
            final IRuleToken nextChild = ruleBaseToken.nextChildren();
            if (nextChild == null) {
                throw new InubitException("Rule element [" + ruleBaseToken + "] has no child!");
            }
            return parseUntilNextElement(nextChild);
        }
        throw new InubitException("Unknown rule token [" + currentRuleToken + "]!");
    }

    public abstract void closeCurrentRuleToken(IToken token);

    public abstract boolean isEndOfRule();
}
