package com.inubit.ibis.plugins.edi20.rules.tokens.hwed;

import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleRoot;
import org.dom4j.Element;

/**
 * @r4fter
 */
public class EDIEnveloperRuleRoot extends EDIRuleRoot {

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
