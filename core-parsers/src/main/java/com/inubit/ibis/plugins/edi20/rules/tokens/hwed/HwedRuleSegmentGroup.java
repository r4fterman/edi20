package com.inubit.ibis.plugins.edi20.rules.tokens.hwed;

import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegmentGroup;
import com.inubit.ibis.plugins.edi20.rules.tokens.RuleTokenFactory;
import org.dom4j.Element;

public class HwedRuleSegmentGroup extends EDIRuleSegmentGroup {

    public HwedRuleSegmentGroup(final Element ruleElement) {
        super(ruleElement);
    }

    @Override
    protected RuleTokenFactory getFactory() {
        return new HwedRuleTokenFactory();
    }
}
