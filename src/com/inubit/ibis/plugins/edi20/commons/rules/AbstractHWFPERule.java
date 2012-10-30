package com.inubit.ibis.plugins.edi20.commons.rules;

import org.dom4j.Document;

import com.inubit.ibis.plugins.edi20.commons.rules.tokens.EDIRuleElement;
import com.inubit.ibis.plugins.edi20.commons.rules.tokens.EDIRuleSegment;
import com.inubit.ibis.plugins.edi20.commons.rules.tokens.EDIRuleSegmentGroup;
import com.inubit.ibis.plugins.edi20.commons.rules.tokens.IRuleToken;
import com.inubit.ibis.utils.InubitException;

/**
 * @author r4fter
 */
public abstract class AbstractHWFPERule extends AbstractEDIRule {

    /**
     * @param ruleDocument
     *            rule document
     * @throws InubitException
     *             if the given rule document is not a valid EDI rule document
     */
    public AbstractHWFPERule(final Document ruleDocument) throws InubitException {
        super(ruleDocument);
    }

    @Override
    public String getLayout() {
        return "hwfpe";
    }

    /**
     * @return next segment in rule or <code>null</code> if no further segment was found in rule
     */
    public EDIRuleSegment nextSegment() {
        IRuleToken currentRuleToken = getCurrentRuleToken();
        EDIRuleSegment segment = findNextRuleSegment(currentRuleToken);
        setCurrentRuleToken(segment);
        return segment;
    }

    private EDIRuleSegment findNextRuleSegment(final IRuleToken ruleToken) {
        System.out.println("AbstractHWFPERule.findNextRuleSegment(): ruleToken=" + ruleToken);
        if (ruleToken instanceof EDIRuleSegmentGroup) {
            IRuleToken nextRuleToken = RuleUtil.getChildOrFollowingSibling(ruleToken);
            System.out.println("AbstractHWFPERule.findNextRuleSegment(sg): nextRuleToken=" + nextRuleToken);
            return findNextRuleSegment(nextRuleToken);
        }
        if (ruleToken instanceof EDIRuleSegment) {
            if (ruleToken.equals(getCurrentRuleToken())) {
                EDIRuleSegment segment = (EDIRuleSegment) ruleToken;
                if (segment.hasLoop() && !segment.isLoopLimitReached()) {
                    segment.looped();
                    return segment;
                }
                return findNextRuleSegment(RuleUtil.getFollowingSibling(ruleToken));
            }
            return (EDIRuleSegment) ruleToken;
        }
        if (ruleToken instanceof EDIRuleElement) {
            IRuleToken nextRuleToken = RuleUtil.getParentFollowingSibling(ruleToken);
            System.out.println("AbstractHWFPERule.findNextRuleSegment(el): nextRuleToken=" + nextRuleToken);
            return findNextRuleSegment(nextRuleToken);
        }
        return null;
    }
}
