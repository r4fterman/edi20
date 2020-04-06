package com.inubit.ibis.plugins.edi20.rules;

import com.inubit.ibis.plugins.edi20.parsers.delimiters.EDIFACTDelimiters;
import com.inubit.ibis.plugins.edi20.rules.interfaces.IRuleToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleBaseToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleCompositeElement;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleElement;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegment;
import com.inubit.ibis.plugins.edi20.scanners.IToken;
import com.inubit.ibis.utils.InubitException;
import org.dom4j.Document;

/**
 * @author r4fter
 */
public class EDIFACTRule extends AbstractHWEDRule {

    private static final String LAST_SEGMENTID = "UNZ";

    /**
     * @param edifactRuleDocument
     *         EDIFACT rule document
     * @throws InubitException
     *         if the given rule document is not a valid EDIFACT rule document
     */
    public EDIFACTRule(final Document edifactRuleDocument) throws InubitException {
        super(edifactRuleDocument);
    }

    @Override
    public String getStandard() {
        return "EDIFACT";
    }

    @Override
    public boolean isEndOfRule() {
//        EDIRuleBaseToken segment = getSegment(getCurrentRuleToken());
//        if (segment != null) {
//            return segment.getID().equals(LAST_SEGMENTID);
//        }
        return false;
    }

    private EDIRuleSegment getSegment(final IRuleToken currentRuleToken) {
        if (currentRuleToken instanceof EDIRuleSegment) {
            return (EDIRuleSegment) currentRuleToken;
        }
        if (currentRuleToken instanceof EDIRuleCompositeElement || currentRuleToken instanceof EDIRuleElement) {
            return getSegment(((EDIRuleBaseToken) currentRuleToken).getParent());
        }
        return null;
    }

    @Override
    public void closeCurrentRuleToken(final IToken token) {
//        EDIRuleBaseToken currentRuleToken = (EDIRuleBaseToken) getCurrentRuleToken();
//        if (isTokenClosingParentRuleToken(token, currentRuleToken)) {
//            if (!isRepeatOnToken(currentRuleToken)) {
//                // go out
//                IRuleToken newCurrentRuleToken = RuleUtil.getParentFollowingSibling(currentRuleToken);
//                //todo: checkRepeat(newCurrentRuleToken, currentRuleToken);
//                setCurrentRuleToken(newCurrentRuleToken);
//            }
//        } else if (isTokenClosingRuleToken(token, currentRuleToken)) {
//            if (!isRepeatOnToken(currentRuleToken)) {
//                // go on
//                IRuleToken newCurrentRuleToken = RuleUtil.getFollowingSibling(currentRuleToken);
//                if (newCurrentRuleToken == null) {
//                    newCurrentRuleToken = RuleUtil.getParentFollowingSibling(newCurrentRuleToken);
//                }
//                //todo: checkRepeat(newCurrentRuleToken, currentRuleToken);
//                setCurrentRuleToken(newCurrentRuleToken);
//            }
//        } else {
//            // go in
//            IRuleToken newCurrentRuleToken = RuleUtil.getChildOrFollowingSibling(currentRuleToken);
//            setCurrentRuleToken(newCurrentRuleToken);
//        }
    }

    //    private void checkRepeat(IRuleToken newCurrentRuleToken, EDIRuleBaseToken currentRuleToken) {
    //        if (newCurrentRuleToken instanceof EDIRuleSegment) {
    //            if (newCurrentRuleToken.equals(currentRuleToken)) {
    //                ((EDIRuleSegment) newCurrentRuleToken).addLoopCount(((EDIRuleSegment) currentRuleToken).getCurrentLoopCount());
    //            }
    //        }
    //    }

    private boolean isRepeatOnToken(final EDIRuleBaseToken currentRuleToken) {
        if (currentRuleToken instanceof EDIRuleSegment) {
            return ((EDIRuleSegment) currentRuleToken).hasLoop();
        }
        return false;
    }

    private boolean isTokenClosingParentRuleToken(final IToken token, final EDIRuleBaseToken currentRuleToken) {
        if (currentRuleToken != null) {
            EDIRuleBaseToken parent = (EDIRuleBaseToken) currentRuleToken.getParent();
            return isTokenClosingRuleToken(token, parent);
        }
        return false;
    }

    private boolean isTokenClosingRuleToken(final IToken token, final EDIRuleBaseToken currentRuleToken) {
        if (currentRuleToken instanceof EDIRuleSegment && isSegmentDelimiter(token)) {
            return true;
        }
        if (currentRuleToken instanceof EDIRuleElement && isElementDelimiter(token)) {
            return true;
        }
        if (currentRuleToken instanceof EDIRuleCompositeElement) {
            return isSegmentDelimiter(token) || isElementDelimiter(token);
        }
        return false;
    }

    private boolean isElementDelimiter(final IToken token) {
        if (token != null) {
            return token.getDelimiterType() == EDIFACTDelimiters.DELIMITER_ELEMENT;
        }
        return false;
    }

    private boolean isSegmentDelimiter(final IToken token) {
        if (token != null) {
            return token.getDelimiterType() == EDIFACTDelimiters.DELIMITER_SEGMENT;
        }
        return false;
    }

}
