package com.inubit.ibis.plugins.edi20.validators;

public class InvalidTypeException extends Exception {

    private static final long serialVersionUID = 156162196836706726L;

    public InvalidTypeException(final String message) {
        super(message);
    }
}
