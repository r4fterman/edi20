package com.inubit.ibis.plugins.edi20.rules.tokens.hwfpe;

import com.inubit.ibis.plugins.edi20.rules.interfaces.IFixedLengthRuleToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleElement;
import org.dom4j.Element;

/**
 * Created with IntelliJ IDEA. User: r4fter Date: 24.03.13 Time: 16:42 To change this template use File | Settings |
 * File Templates.
 */
public class HwfpeRuleElement extends EDIRuleElement implements IFixedLengthRuleToken {

    public HwfpeRuleElement(Element ruleElement) {
        super(ruleElement);
    }

    @Override
    public int getFromPosition() {
        String posStart = getRuleElement().attributeValue("posStart", "");
        return getIntegerValue(posStart);
    }

    @Override
    public int getToPosition() {
        String posEnd = getRuleElement().attributeValue("posEnd", "");
        return getIntegerValue(posEnd);
    }
}
