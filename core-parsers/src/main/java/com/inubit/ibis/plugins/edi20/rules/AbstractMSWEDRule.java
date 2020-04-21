package com.inubit.ibis.plugins.edi20.rules;

import com.inubit.ibis.utils.InubitException;
import org.dom4j.Document;

public abstract class AbstractMSWEDRule extends AbstractHWEDRule {

    /**
     * @param ruleDocument
     *         rule document
     * @throws InubitException
     *         if the given rule document is not a valid EDI rule document
     */
    public AbstractMSWEDRule(final Document ruleDocument) throws InubitException {
        super(ruleDocument);
    }

    @Override
    public String getLayout() {
        return "mswed";
    }

}
