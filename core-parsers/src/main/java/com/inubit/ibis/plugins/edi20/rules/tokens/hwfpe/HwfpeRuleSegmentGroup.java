package com.inubit.ibis.plugins.edi20.rules.tokens.hwfpe;

import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegmentGroup;
import com.inubit.ibis.plugins.edi20.rules.tokens.RuleTokenFactory;
import org.dom4j.Element;

public class HwfpeRuleSegmentGroup extends EDIRuleSegmentGroup {

    public HwfpeRuleSegmentGroup(final Element ruleElement) {
        super(ruleElement);
    }

    @Override
    protected RuleTokenFactory getFactory() {
        return new HwfpeRuleTokenFactory();
    }
}
