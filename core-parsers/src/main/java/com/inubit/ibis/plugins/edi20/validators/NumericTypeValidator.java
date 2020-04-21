package com.inubit.ibis.plugins.edi20.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumericTypeValidator implements TypeValidator {

    private final Pattern pattern = Pattern.compile("[\\s\\d]+");

    @Override
    public void validate(final String value) throws InvalidTypeException {
        // Whitespaces allowed?
        final Matcher m = pattern.matcher(value);
        if (!m.matches()) {
            final String message = String.format("Value [%s] is not of numeric type!", value);
            throw new InvalidTypeException(message);
        }
    }
}
