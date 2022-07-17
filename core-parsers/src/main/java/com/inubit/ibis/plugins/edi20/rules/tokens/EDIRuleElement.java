package com.inubit.ibis.plugins.edi20.rules.tokens;

import com.inubit.ibis.plugins.edi20.rules.interfaces.ElementRuleToken;
import com.inubit.ibis.utils.StringUtil;
import org.dom4j.Element;

public abstract class EDIRuleElement extends EDIRuleBaseToken implements ElementRuleToken {

    private static final int INTEGER_NOT_SET = -1;
    private static final String ATTRIBUTE_NAME_TYPE = "type";

    protected EDIRuleElement(final Element ruleElement) {
        super(ruleElement);
    }

    /**
     * @param integerValue
     *         integer value as string
     * @return parsed integer value or -1
     */
    protected int getIntegerValue(final String integerValue) {
        if (StringUtil.isSet(integerValue)) {
            try {
                return Integer.parseInt(integerValue);
            } catch (final NumberFormatException e) {
                // do nothing
            }
        }
        return INTEGER_NOT_SET;
    }

    public String getType() {
        return getRuleElement().attributeValue(ATTRIBUTE_NAME_TYPE, "");
    }

    @Override
    public String toString() {
        return "(Element) " + super.toString() + ", type=" + getType();
    }

}
