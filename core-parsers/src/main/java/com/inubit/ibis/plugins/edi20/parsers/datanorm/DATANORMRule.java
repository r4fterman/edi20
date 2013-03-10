package com.inubit.ibis.plugins.edi20.parsers.datanorm;

import com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule;
import com.inubit.ibis.utils.InubitException;
import org.dom4j.Document;

/**
 * @author r4fter
 */
public class DATANORMRule extends AbstractEDIRule {

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
    public String getLayout() {
        return "mswed";
    }

    @Override
    public String getStandard() {
        return "DATANORM";
    }

}