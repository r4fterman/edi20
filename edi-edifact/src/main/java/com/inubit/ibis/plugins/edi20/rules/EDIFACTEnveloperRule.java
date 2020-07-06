package com.inubit.ibis.plugins.edi20.rules;

import com.inubit.ibis.utils.EDIException;
import org.dom4j.Document;

public class EDIFACTEnveloperRule extends EDIFACTRule {

    private static final String LAST_SEGMENTID = "UNZ";

    @Override
    public String getStandard() {
        return "ENVELOPER";
    }

    public EDIFACTEnveloperRule(final Document enveloperDocument) throws EDIException {
        super(enveloperDocument);
    }

    @Override
    protected String getRootElementName() {
        return "Enveloper";
    }

    @Override
    public boolean isEndOfRule() {
        return false;
    }

}
