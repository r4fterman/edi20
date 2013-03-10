package com.inubit.ibis.plugins.edi20.parsers.hl7;

import com.inubit.ibis.plugins.edi20.rules.AbstractHWEDRule;
import com.inubit.ibis.plugins.edi20.scanners.IToken;
import com.inubit.ibis.utils.InubitException;
import org.dom4j.Document;

/**
 * @author r4fter
 */
public class HL7Rule extends AbstractHWEDRule {

    /**
     * @param hl7RuleDocument
     *         HL7 rule document
     * @throws InubitException
     *         if the given rule document is not a valid HL7 rule document
     */
    public HL7Rule(final Document hl7RuleDocument) throws InubitException {
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
    public void closeCurrentRuleToken(IToken token) {
        // TODO: implement
    }
}
