package com.inubit.ibis.plugins.edi20.rules;

import com.inubit.ibis.utils.EDIException;
import org.dom4j.Document;

public abstract class AbstractMSWEDRule extends AbstractHWEDRule {

    /**
     * @param ruleDocument
     *         rule document
     * @throws EDIException
     *         if the given rule document is not a valid EDI rule document
     */
    protected AbstractMSWEDRule(final Document ruleDocument) throws EDIException {
        super(ruleDocument);
    }

    @Override
    public String getLayout() {
        return "mswed";
    }

}
