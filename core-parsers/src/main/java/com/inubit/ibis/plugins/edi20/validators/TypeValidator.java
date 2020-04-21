package com.inubit.ibis.plugins.edi20.validators;

public interface TypeValidator {

    void validate(String value) throws InvalidTypeException;
}
