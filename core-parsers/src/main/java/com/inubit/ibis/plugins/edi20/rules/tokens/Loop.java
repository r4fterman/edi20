package com.inubit.ibis.plugins.edi20.rules.tokens;

public class Loop {

    private static final String INFINITE = "n";

    private final String value;

    public Loop(final String value) {
        this.value = value;
    }

    public boolean isInfinite() {
        return value.equals(INFINITE);
    }

    public String getValue() {
        return value;
    }

    public int getValueAsInteger() {
        return Integer.parseInt(value);
    }
}
