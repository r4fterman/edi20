package com.inubit.ibis.utils;

/**
 * @author rafter
 */
public class EDIException extends Exception {

    private static final long serialVersionUID = -8003127922873714076L;

    public EDIException() {
        // do nothing
    }

    public EDIException(final String message) {
        super(message);
    }

    public EDIException(final Throwable throwable) {
        super(throwable);
    }

    public EDIException(
            final String message,
            final Throwable throwable) {
        super(message, throwable);
    }

}
