package com.inubit.ibis.plugins.edi20.rules.tokens;

public enum RuleElementType {

    Numeric("N", "Numeric"),
    Alphanumeric("AN", "Alphanumeric");

    private final String typeValue;
    private final String typeDescription;

    RuleElementType(
            final String typeValue,
            final String typeDescription) {
        this.typeValue = typeValue;
        this.typeDescription = typeDescription;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public String getTypeDescription() {
        return typeDescription;
    }
}
