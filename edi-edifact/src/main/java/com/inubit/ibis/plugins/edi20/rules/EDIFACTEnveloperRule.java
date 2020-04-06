package com.inubit.ibis.plugins.edi20.rules;

import com.inubit.ibis.utils.InubitException;
import org.dom4j.Document;

/**
 * @author r4fter
 */
public class EDIFACTEnveloperRule extends EDIFACTRule {

    @Override
    public String getStandard() {
        return "ENVELOPER";
    }

    public EDIFACTEnveloperRule(final Document enveloperDocument) throws InubitException {
        super(enveloperDocument);
    }

    @Override
    protected String getRootElementName() {
        return "Enveloper";
    }

}
