package com.inubit.ibis.plugins.edi20.rules;

import com.inubit.ibis.utils.InubitException;
import org.dom4j.Document;

/**
 * @author r4fter
 */
public class EDIGASRule extends EDIFACTRule {

    public EDIGASRule(final Document edigasRuleDocument) throws InubitException {
        super(edigasRuleDocument);
    }

    @Override
    public String getStandard() {
        return "EDIGAS";
    }

}
