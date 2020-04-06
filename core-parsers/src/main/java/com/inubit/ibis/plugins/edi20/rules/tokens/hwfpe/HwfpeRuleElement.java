package com.inubit.ibis.plugins.edi20.rules.tokens.hwfpe;

import com.inubit.ibis.plugins.edi20.rules.interfaces.IFixedLengthRuleToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleElement;
import org.dom4j.Element;

public class HwfpeRuleElement extends EDIRuleElement implements IFixedLengthRuleToken {

    public HwfpeRuleElement(final Element ruleElement) {
        super(ruleElement);
    }

    @Override
    public int getFromPosition() {
        final String posStart = getRuleElement().attributeValue("posStart", "");
        return getIntegerValue(posStart);
    }

    @Override
    public int getToPosition() {
        final String posEnd = getRuleElement().attributeValue("posEnd", "");
        return getIntegerValue(posEnd);
    }
}
