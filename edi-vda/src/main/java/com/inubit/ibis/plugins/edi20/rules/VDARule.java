package com.inubit.ibis.plugins.edi20.rules;

import com.inubit.ibis.utils.InubitException;
import org.dom4j.Document;

public class VDARule extends AbstractHWFPERule {

    /**
     * @param vdaRuleDocument
     *         VDA rule document
     * @throws InubitException
     *         if the given rule document is not a valid VDA rule document
     */
    public VDARule(final Document vdaRuleDocument) throws InubitException {
        super(vdaRuleDocument);
    }

    @Override
    public String getStandard() {
        return "VDA";
    }

}
