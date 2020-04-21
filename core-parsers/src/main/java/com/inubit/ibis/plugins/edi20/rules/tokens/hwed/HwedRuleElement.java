package com.inubit.ibis.plugins.edi20.rules.tokens.hwed;

import com.inubit.ibis.plugins.edi20.rules.interfaces.IVariableLengthRuleToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleElement;
import com.inubit.ibis.plugins.edi20.rules.tokens.RuleTokenFactory;
import org.dom4j.Element;

public class HwedRuleElement extends EDIRuleElement implements IVariableLengthRuleToken {

    private static final String ATTRIBUTE_NAME_MINLENGTH = "minLength";
    private static final String ATTRIBUTE_NAME_MAXLENGTH = "maxLength";

    public HwedRuleElement(final Element ruleElement) {
        super(ruleElement);
    }

    @Override
    protected RuleTokenFactory getFactory() {
        return new HwedRuleTokenFactory();
    }

    // <Element id="1153" maxLength="3" minLength="1" name="Reference qualifier" required="M" type="AN" xmlTag="ReferenceQualifier"/>
    @Override
    public int getMinLength() {
        final String minLength = getRuleElement().attributeValue(ATTRIBUTE_NAME_MINLENGTH, "");
        return getIntegerValue(minLength);
    }

    @Override
    public int getMaxLength() {
        final String maxLength = getRuleElement().attributeValue(ATTRIBUTE_NAME_MAXLENGTH, "");
        return getIntegerValue(maxLength);
    }

    @Override
    public String toString() {
        return super.toString() + ", max=" + getMaxLength() + ", min=" + getMinLength();
    }
}
