package com.inubit.ibis.plugins.edi20.commons.rules.tokens;

import org.dom4j.Element;

/**
 * @author r4fter
 * 
 */
public class EDIRuleElement extends EDIRuleBaseToken {

    private static final String ATTRIBUTE_NAME_MINLENGTH = "minLength";
    private static final String ATTRIBUTE_NAME_MAXLENGTH = "maxLength";
    private static final String ATTRIBUTE_NAME_TYPE = "type";

    public EDIRuleElement(Element ruleElement) {
        super(ruleElement);
    }

    // <Element id="1153" maxLength="3" minLength="1" name="Reference qualifier" required="M" type="AN" xmlTag="ReferenceQualifier"/>
    public String getMinLength() {
        return getRuleElement().attributeValue(ATTRIBUTE_NAME_MINLENGTH, "");
    }

    public String getMaxLength() {
        return getRuleElement().attributeValue(ATTRIBUTE_NAME_MAXLENGTH, "");
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
