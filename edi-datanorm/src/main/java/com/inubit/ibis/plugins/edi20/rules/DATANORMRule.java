package com.inubit.ibis.plugins.edi20.rules;

import com.inubit.ibis.plugins.edi20.scanners.Token;
import com.inubit.ibis.utils.EDIException;
import org.dom4j.Document;

public class DATANORMRule extends AbstractMSWEDRule {

    /**
     * @param datanormRuleDocument
     *         DATANORM rule document
     * @throws EDIException
     *         if the given rule document is not a valid DATANORM rule document
     */
    public DATANORMRule(final Document datanormRuleDocument) throws EDIException {
        super(datanormRuleDocument);
    }

    @Override
    public void closeCurrentRuleToken(final Token token) {
        // ignore
    }

    @Override
    public boolean isEndOfRule() {
        return false;
    }

    @Override
    public String getStandard() {
        return "DATANORM";
    }

}
