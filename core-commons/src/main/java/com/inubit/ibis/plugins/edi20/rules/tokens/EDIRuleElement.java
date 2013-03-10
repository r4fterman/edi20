package com.inubit.ibis.plugins.edi20.rules.tokens;

import com.inubit.ibis.plugins.edi20.rules.interfaces.IElementRuleToken;
import com.inubit.ibis.plugins.edi20.rules.interfaces.ILengthRuleToken;
import com.inubit.ibis.utils.StringUtil;
import org.dom4j.Element;

/**
 * @author r4fter
 */
public class EDIRuleElement extends EDIRuleBaseToken implements IElementRuleToken, ILengthRuleToken {

    private static final String ATTRIBUTE_NAME_MINLENGTH = "minLength";
    private static final String ATTRIBUTE_NAME_MAXLENGTH = "maxLength";
    private static final String ATTRIBUTE_NAME_TYPE = "type";

    private static final int INTEGER_NOT_SET = -1;

    public EDIRuleElement(Element ruleElement) {
        super(ruleElement);
    }

    // <Element id="1153" maxLength="3" minLength="1" name="Reference qualifier" required="M" type="AN" xmlTag="ReferenceQualifier"/>
    @Override
    public int getMinLength() {
        String minLength = getRuleElement().attributeValue(ATTRIBUTE_NAME_MINLENGTH, "");
        if (StringUtil.isSet(minLength)) {
            try {
                return Integer.parseInt(minLength);
            } catch (NumberFormatException e) {
                // do nothing
            }
        }
        return INTEGER_NOT_SET;
    }

    @Override
    public int getMaxLength() {
        String maxLength = getRuleElement().attributeValue(ATTRIBUTE_NAME_MAXLENGTH, "");
        if (StringUtil.isSet(maxLength)) {
            try {
                return Integer.parseInt(maxLength);
            } catch (NumberFormatException e) {
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
        return "(Element) " + super.toString() + ", max=" + getMaxLength() + ", min=" + getMinLength() + ", type="
                + getType();
    }
}
