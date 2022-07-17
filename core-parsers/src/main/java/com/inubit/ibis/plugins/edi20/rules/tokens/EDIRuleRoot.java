package com.inubit.ibis.plugins.edi20.rules.tokens;

import org.dom4j.Element;

public abstract class EDIRuleRoot extends EDIRuleBaseToken {

    private static final String ATTRIBUTE_NAME_AGENCY = "agency";
    private static final String ATTRIBUTE_NAME_RELEASE = "release";
    private static final String ATTRIBUTE_NAME_STANDARD = "standard";
    private static final String ATTRIBUTE_NAME_TYPE = "type";
    private static final String ATTRIBUTE_NAME_VERSION = "version";
    private static final String ATTRIBUTE_NAME_LAYOUT = "layout";

    public EDIRuleRoot(final Element ruleElement) {
        super(ruleElement);
    }

    @Override
    protected String getPathString() {
        return "/" + getElement().getName();
    }

    @Override
    public String getID() {
        return "Root";
    }

    /**
     * @return agency, e.g. UN
     */
    public String getAgency() {
        return getElement().attributeValue(ATTRIBUTE_NAME_AGENCY, "");
    }

    /**
     * @return layout, e.g. HWED
     */
    public String getLayout() {
        return getElement().attributeValue(ATTRIBUTE_NAME_LAYOUT, "");
    }

    /**
     * @return release, e.g. 96A
     */
    public String getRelease() {
        return getElement().attributeValue(ATTRIBUTE_NAME_RELEASE, "");
    }

    /**
     * @return standard, e.g. EDIFACT
     */
    public String getStandard() {
        return getElement().attributeValue(ATTRIBUTE_NAME_STANDARD, "");
    }

    /**
     * @return type, e.g. IFCSUM
     */
    public String getType() {
        return getElement().attributeValue(ATTRIBUTE_NAME_TYPE, "");
    }

    /**
     * @return version, e.g. D
     */
    public String getVersion() {
        return getElement().attributeValue(ATTRIBUTE_NAME_VERSION, "");
    }
}
