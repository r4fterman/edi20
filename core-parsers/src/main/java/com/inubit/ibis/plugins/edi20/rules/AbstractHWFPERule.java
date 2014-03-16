package com.inubit.ibis.plugins.edi20.rules;

import com.inubit.ibis.plugins.edi20.rules.interfaces.IRuleToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleElement;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleRoot;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegment;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegmentGroup;
import com.inubit.ibis.plugins.edi20.rules.tokens.hwfpe.HwfpeRuleTokenFactory;
import com.inubit.ibis.utils.InubitException;
import org.dom4j.Document;

/**
 * @author r4fter
 */
public abstract class AbstractHWFPERule extends AbstractEDIRule {

    /**
     * @param ruleDocument
     *         rule document
     * @throws InubitException
     *         if the given rule document is not a valid EDI rule document
     */
    public AbstractHWFPERule(final Document ruleDocument) throws InubitException {
        super(ruleDocument);
    }

    @Override
    protected EDIRuleRoot createRootElement(Document ruleDocument) {
        return (EDIRuleRoot) HwfpeRuleTokenFactory.getInstance(ruleDocument.getRootElement());
    }

    @Override
    public String getLayout() {
        return "hwfpe";
    }

    /**
     * @return next segment in rule or <code>null</code> if no further segment was found in rule
     */
    public EDIRuleSegment nextSegment() throws InubitException {
//        IRuleToken currentRuleToken = getCurrentRuleToken();
//        EDIRuleSegment segment = findNextRuleSegment(currentRuleToken);
//        setCurrentRuleToken(segment);
//        return segment;
        return null;
    }

    private EDIRuleSegment findNextRuleSegment(final IRuleToken ruleToken) throws InubitException {
        System.out.println("findNextRuleSegment(): ruleToken=" + ruleToken);
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
        return null;
    }

    private EDIRuleSegment findNextRuleSegmentFromElement(EDIRuleElement ruleElement) throws InubitException {
        IRuleToken nextRuleToken = RuleUtil.getParentFollowingSibling(ruleElement);
        System.out.println("findNextRuleSegmentFromElement(): nextRuleToken=" + nextRuleToken);
        return findNextRuleSegment(nextRuleToken);
    }

    private EDIRuleSegment findNextRuleSegmentFromSegment(EDIRuleSegment ruleSegment) throws InubitException {
//        if (ruleSegment.equals(getCurrentRuleToken())) {
//            if (ruleSegment.canLoop()) {
//                ruleSegment.looped();
//                return ruleSegment;
//            }
//            return findNextRuleSegment(RuleUtil.getFollowingSibling(ruleSegment));
//        }
        return ruleSegment;
    }

    private EDIRuleSegment findNextRuleSegmentFromSegmentGroup(EDIRuleSegmentGroup ruleSegmentGroup) throws InubitException {
        if (ruleSegmentGroup.canLoop()) {
            IRuleToken nextRuleToken = RuleUtil.getChildOrFollowingSibling(ruleSegmentGroup);
            System.out.println("findNextRuleSegmentFromSegmentGroup(): nextRuleToken=" + nextRuleToken);
            return findNextRuleSegment(nextRuleToken);
        }
        IRuleToken nextRuleToken = RuleUtil.getFollowingSibling(ruleSegmentGroup);
        System.out.println("findNextRuleSegmentFromSegmentGroup(): nextRuleToken=" + nextRuleToken);
        return findNextRuleSegment(nextRuleToken);
    }

    private EDIRuleSegment findNextRuleSegmentFromRoot(EDIRuleRoot ruleRoot) throws InubitException {
        IRuleToken nextRuleToken = RuleUtil.getChildOrFollowingSibling(ruleRoot);
        System.out.println("findNextRuleSegmentFromRoot(): nextRuleToken=" + nextRuleToken);
        return findNextRuleSegment(nextRuleToken);
    }
}