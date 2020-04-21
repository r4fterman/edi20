package com.inubit.ibis.plugins.edi20.parsers.bemis;

import com.inubit.ibis.plugins.edi20.rules.AbstractHWEDRule;
import com.inubit.ibis.plugins.edi20.scanners.IToken;
import com.inubit.ibis.utils.InubitException;
import org.dom4j.Document;

public class BEMISRule extends AbstractHWEDRule {

    /**
     * @param bemisRuleDocument
     *         BEMIS rule document
     * @throws InubitException
     *         if the given rule document is not a valid BEMIS rule document
     */
    public BEMISRule(final Document bemisRuleDocument) throws InubitException {
        super(bemisRuleDocument);
    }

    @Override
    public String getStandard() {
        return "BEMIS";
    }

    @Override
    public boolean isEndOfRule() {
        return false;
    }

    @Override
    public void closeCurrentRuleToken(final IToken token) {
        //todo: implement
    }
}
