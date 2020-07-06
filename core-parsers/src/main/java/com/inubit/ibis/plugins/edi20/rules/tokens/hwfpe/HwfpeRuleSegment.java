package com.inubit.ibis.plugins.edi20.rules.tokens.hwfpe;

import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegment;
import com.inubit.ibis.plugins.edi20.rules.tokens.RuleTokenFactory;
import org.dom4j.Element;

public class HwfpeRuleSegment extends EDIRuleSegment {

    public HwfpeRuleSegment(final Element ruleElement) {
        super(ruleElement);
    }

    @Override
    protected RuleTokenFactory getFactory() {
        return new HwfpeRuleTokenFactory();
    }
}
