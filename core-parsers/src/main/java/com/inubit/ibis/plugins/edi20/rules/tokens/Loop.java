package com.inubit.ibis.plugins.edi20.rules.tokens;

import java.util.Objects;

public class Loop {

    private static final String INFINITE = "n";

    private final String value;

    public Loop(final String value) {
        this.value = value;
    }

    public static Loop valueOf(final int count) {
        return new Loop(String.valueOf(count));
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

    @Override
    public boolean equals(final Object o) {
        if (o instanceof Loop) {
            final Loop loop = (Loop) o;
            return Objects.equals(value, loop.value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Loop{"
                + "value='" + value + '\''
                + '}';
    }
}
