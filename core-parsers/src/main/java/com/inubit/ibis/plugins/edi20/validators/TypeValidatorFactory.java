package com.inubit.ibis.plugins.edi20.validators;

public final class TypeValidatorFactory {

    public static TypeValidator getInstance(final String type) throws InvalidTypeException {
        if (type.equals("N")) {
            return new NumericTypeValidator();
        }
        if (type.equals("AN")) {
            return new AlphanumericTypeValidator();
        }

        final String message = String.format("Found invalid type: [%s]", type);
        throw new InvalidTypeException(message);
    }
}
