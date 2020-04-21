package com.inubit.ibis.plugins.edi20.rules;

import com.inubit.ibis.plugins.edi20.scanners.IToken;
import com.inubit.ibis.utils.InubitException;
import org.dom4j.Document;

public class DATANORMRule extends AbstractMSWEDRule {

    /**
     * @param datanormRuleDocument
     *         DATANORM rule document
     * @throws InubitException
     *         if the given rule document is not a valid DATANORM rule document
     */
    public DATANORMRule(final Document datanormRuleDocument) throws InubitException {
        super(datanormRuleDocument);
    }

    @Override
    public void closeCurrentRuleToken(final IToken token) {
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
