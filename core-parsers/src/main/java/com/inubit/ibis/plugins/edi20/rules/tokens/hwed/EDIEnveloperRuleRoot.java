package com.inubit.ibis.plugins.edi20.rules.tokens.hwed;

import org.dom4j.Element;

public class EDIEnveloperRuleRoot extends HwedRuleRoot {

    public EDIEnveloperRuleRoot(final Element ruleElement) {
        super(ruleElement);
    }

    @Override
    public String getStandard() {
        return "ENVELOPER";
    }

    @Override
    public String getLayout() {
        return "hwed";
    }
}
