package com.inubit.ibis.plugins.edi20.rules.tokens;

public enum RuleElementType {

    N("Numeric"),
    AN("Alphanumeric");

    private final String value;

    RuleElementType(final String value) {
        this.value = value;
    }

    public String getTypeValue() {
        return value;
    }

}
