package com.inubit.ibis.plugins.edi20.rules.tokens.hwed;

import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegment;
import com.inubit.ibis.plugins.edi20.rules.tokens.RuleTokenFactory;
import org.dom4j.Element;

public class HwedRuleSegment extends EDIRuleSegment {

    public HwedRuleSegment(final Element ruleElement) {
        super(ruleElement);
    }

    @Override
    protected RuleTokenFactory getFactory() {
        return new HwedRuleTokenFactory();
    }
}
