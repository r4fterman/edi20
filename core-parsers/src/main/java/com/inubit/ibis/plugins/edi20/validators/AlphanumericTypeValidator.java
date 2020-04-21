package com.inubit.ibis.plugins.edi20.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlphanumericTypeValidator implements TypeValidator {

    private final Pattern pattern = Pattern.compile("[\\s\\w-]+");

    @Override
    public void validate(final String value) throws InvalidTypeException {
        final Matcher matcher = pattern.matcher(value);
        if (!matcher.matches()) {
            final String message = String.format("Value [%s] is not of alpha-numeric type.", value);
            throw new InvalidTypeException(message);
        }
    }
}
