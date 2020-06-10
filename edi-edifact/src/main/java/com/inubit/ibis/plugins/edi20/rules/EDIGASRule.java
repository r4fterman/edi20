package com.inubit.ibis.plugins.edi20.rules;

import com.inubit.ibis.utils.EDIException;
import org.dom4j.Document;

public class EDIGASRule extends EDIFACTRule {

    public EDIGASRule(final Document edigasRuleDocument) throws EDIException {
        super(edigasRuleDocument);
    }

    @Override
    public String getStandard() {
        return "EDIGAS";
    }

}
