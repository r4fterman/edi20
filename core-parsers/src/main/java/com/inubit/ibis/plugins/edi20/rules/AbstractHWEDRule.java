package com.inubit.ibis.plugins.edi20.rules;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import com.inubit.ibis.plugins.edi20.rules.interfaces.RuleToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleBaseToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleCompositeElement;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleElement;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegment;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegmentGroup;
import com.inubit.ibis.plugins.edi20.rules.tokens.hwed.HwedRuleTokenFactory;
import com.inubit.ibis.plugins.edi20.scanners.Token;
import com.inubit.ibis.utils.EDIException;
import com.inubit.ibis.utils.XPathUtil;

public abstract class AbstractHWEDRule extends AbstractEDIRule {

    /**
     * Constructor creates HWED rule from the given rule document.
     *
     * @param ediRuleDocument EDI rule document
     * @throws EDIException if the given rule document is not a valid EDI rule document
     */
    protected AbstractHWEDRule(final Document ediRuleDocument) throws EDIException {
        super(ediRuleDocument);
    }

    @Override
    protected RuleToken getRuleToken(final Element element) {
        return new HwedRuleTokenFactory().createInstance(element);
    }

    @Override
    public String getLayout() {
        return "hwed";
    }

    /**
     * @param currentRuleToken current rule token
     * @param segmentID        segment ID
     * @return segment or <code>null</code> if segment is conditional and does not match the given segment ID
     * @throws EDIException if segment is mandatory and does not match the given segment ID
     */
    private EDIRuleSegment parseUntilNextSegment(
            final RuleToken currentRuleToken,
            final String segmentID) throws EDIException {
        logMessage("AbstractHWEDRule.parseUntilNextSegment(" + currentRuleToken.getID() + "): search for segment ["
                + segmentID + "] ...");

        if (currentRuleToken instanceof EDIRuleSegmentGroup) {
            final EDIRuleSegmentGroup ruleSegmentGroup = (EDIRuleSegmentGroup) currentRuleToken;

            logMessage("AbstractHWEDRule.parseUntilNextSegment(): checked=" + ruleSegmentGroup.isChecked());

            if (ruleSegmentGroup.isChecked()) {
                if (ruleSegmentGroup.isMandatory()) {
                    final String message = String.format(
                            "Found mandatory segment group [%s] while trying to find next segment [%s]!",
                            ruleSegmentGroup,
                            segmentID);
                    throw new EDIException(message);
                }
            } else if (ruleSegmentGroup.isInProgress()) {
                ruleSegmentGroup.setChecked();

                logMessage("AbstractHWEDRule.parseUntilNextSegment(): next segment group...");

                return parseUntilNextSegment(RuleUtil.getParentFollowingSibling(ruleSegmentGroup), segmentID);
            } else {
                ruleSegmentGroup.setInProgress();
            }

            final RuleToken childToken = ruleSegmentGroup.nextChildren();
            if (childToken != null) {
                return parseUntilNextSegment(childToken, segmentID);
            }
            final String message = String.format("Segment [%s] not found!", segmentID);
            throw new EDIException(message);
        }
        if (currentRuleToken instanceof EDIRuleSegment) {
            final EDIRuleSegment ruleSegment = (EDIRuleSegment) currentRuleToken;
            if (ruleSegment.getID().equals(segmentID)) {
                logMessage("AbstractHWEDRule.parseUntilNextSegment(): rseg=" + ruleSegment);
                return ruleSegment;
            }
            // if (ruleSegment.isMandatory()) {
            // throw new InubitException("Found mandatory segment [" + ruleSegment + "] while trying to find next segment [" + segmentID + "]!");
            // }
            final EDIRuleSegment segment = parseUntilNextSegment(RuleUtil.getFollowingSibling(ruleSegment), segmentID);

            logMessage("AbstractHWEDRule.parseUntilNextSegment(): seg=" + segment);

            return segment;
        }
        if (currentRuleToken instanceof EDIRuleCompositeElement) {
            final EDIRuleCompositeElement ruleCompositeElement = (EDIRuleCompositeElement) currentRuleToken;
            if (ruleCompositeElement.isMandatory()) {
                final String message = String.format(
                        "Found mandatory composite element [%s] while trying to find next segment [%s]!",
                        ruleCompositeElement,
                        segmentID);
                throw new EDIException(message);
            }
            return parseUntilNextSegment(RuleUtil.getFollowingSibling(ruleCompositeElement), segmentID);
        }
        if (currentRuleToken instanceof EDIRuleElement) {
            final EDIRuleElement ruleElement = (EDIRuleElement) currentRuleToken;
            if (ruleElement.isMandatory()) {
                final String message =
                        String.format("Found mandatory element [%s] while trying to find next segment [%s]!",
                                ruleElement,
                                segmentID);
                throw new EDIException(
                        message);
            }
            return parseUntilNextSegment(RuleUtil.getFollowingSibling(ruleElement), segmentID);
        }
        final String message = String.format("Unknown rule token [%s]!", currentRuleToken);
        throw new EDIException(message);
    }

    private void markLoopOnSegment(
            final EDIRuleSegment segment,
            final String segmentID) throws EDIException {
        if (segment == null) {
            final String message = String.format("Segment [%s] not found!", segmentID);
            throw new EDIException(message);
        }
        segment.looped();
    }

    /**
     * @param segmentID segment ID to check
     * @return <code>true</code> if this rule contains at least one further
     * segment with the given ID,
     * <code>false</code> otherwise
     */
    public boolean containsSegment(final String segmentID) {
        return containsSegment(segmentID, null);
    }

    /**
     * @param segmentID      segment ID to check
     * @param startFromToken rule token to start search from
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
        if (!segments.isEmpty()) {
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
        return String.format("descendant-or-self::Segment[@id='%s']", segmentID);
    }

    public EDIRuleBaseToken nextElement() throws RuleViolationException {
        logMessage("AbstractHWEDRule.nextElement(): current=" + getCurrentRuleToken());
        final EDIRuleBaseToken newCurrentRuleToken = parseUntilNextElement(getCurrentRuleToken());
        setCurrentRuleToken(newCurrentRuleToken);
        return newCurrentRuleToken;
    }

    private EDIRuleBaseToken parseUntilNextElement(final RuleToken currentRuleToken) throws RuleViolationException {
        if (currentRuleToken instanceof EDIRuleElement) {
            return (EDIRuleElement) currentRuleToken;
        }
        if (currentRuleToken instanceof EDIRuleBaseToken) {
            final EDIRuleBaseToken ruleBaseToken = (EDIRuleBaseToken) currentRuleToken;
            logMessage("AbstractHWEDRule.parseUntilNextElement: " + ruleBaseToken);
            final RuleToken nextChild = ruleBaseToken.nextChildren();
            if (nextChild == null) {
                final String message = String.format("Rule element [%s] has no child!", ruleBaseToken);
                throw new RuleViolationException(message);
            }
            return parseUntilNextElement(nextChild);
        }
        final String message = String.format("Unknown rule token [%s]!", currentRuleToken);
        throw new RuleViolationException(message);
    }

    public abstract void closeCurrentRuleToken(Token token);

    public abstract boolean isEndOfRule();

    private void logMessage(final String message) {
        System.out.println(message);
    }
}
