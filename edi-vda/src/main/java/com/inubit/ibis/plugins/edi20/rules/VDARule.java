package com.inubit.ibis.plugins.edi20.rules;

import com.inubit.ibis.plugins.edi20.rules.interfaces.RuleToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegment;
import com.inubit.ibis.utils.InubitException;
import org.dom4j.Document;

public class VDARule extends AbstractHWFPERule {

    private static final String LAST_SEGMENTID = "519";

    /**
     * @param vdaRuleDocument
     *         VDA rule document
     * @throws InubitException
     *         if the given rule document is not a valid VDA rule document
     */
    public VDARule(final Document vdaRuleDocument) throws InubitException {
        super(vdaRuleDocument);
    }

    @Override
    public String getStandard() {
        return "VDA";
    }

    public boolean isEndOfRule() {
//        EDIRuleBaseToken segment = getSegment(getCurrentRuleToken());
//        if (segment != null) {
//            return segment.getID().equals(LAST_SEGMENTID);
//        }
        return false;
    }

    private EDIRuleSegment getSegment(final RuleToken currentRuleToken) {
        if (currentRuleToken instanceof EDIRuleSegment) {
            return (EDIRuleSegment) currentRuleToken;
        }
        return null;
    }

}
