package com.inubit.ibis.plugins.edi20.parsers.hl7;

import com.inubit.ibis.plugins.edi20.rules.AbstractHWEDRule;
import com.inubit.ibis.plugins.edi20.scanners.Token;
import com.inubit.ibis.utils.EDIException;
import org.dom4j.Document;

public class HL7Rule extends AbstractHWEDRule {

    /**
     * @param hl7RuleDocument
     *         HL7 rule document
     * @throws EDIException
     *         if the given rule document is not a valid HL7 rule document
     */
    public HL7Rule(final Document hl7RuleDocument) throws EDIException {
        super(hl7RuleDocument);
    }

    @Override
    public String getStandard() {
        return "HL7";
    }

    @Override
    public boolean isEndOfRule() {
        return false;
    }

    @Override
    public void closeCurrentRuleToken(final Token token) {
        //todo: implement
    }
}
