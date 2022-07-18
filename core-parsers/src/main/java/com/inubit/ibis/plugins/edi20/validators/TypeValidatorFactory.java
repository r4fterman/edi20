package com.inubit.ibis.plugins.edi20.validators;

import java.util.Map;

import com.inubit.ibis.plugins.edi20.rules.tokens.RuleElementType;

public final class TypeValidatorFactory {

    private static final Map<RuleElementType, TypeValidator> VALIDATORS = Map.of(
            RuleElementType.N, new NumericTypeValidator(),
            RuleElementType.AN, new AlphanumericTypeValidator()
    );

    public static TypeValidator getInstance(final RuleElementType type) throws InvalidTypeException {
        final TypeValidator typeValidator = VALIDATORS.get(type);
        if (typeValidator != null) {
            return typeValidator;
        }

        final String message = String.format("Found invalid type: [%s]", type);
        throw new InvalidTypeException(message);
    }

    private TypeValidatorFactory() {
        // do nothing
    }
}
