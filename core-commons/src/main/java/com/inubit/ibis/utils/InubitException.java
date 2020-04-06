package com.inubit.ibis.utils;

/**
 * @author rafter
 */
public class InubitException extends Exception {

    private static final long serialVersionUID = -8003127922873714076L;

    public InubitException() {
        // do nothing
    }

    public InubitException(final String message) {
        super(message);
    }

    public InubitException(final Throwable throwable) {
        super(throwable);
    }

    public InubitException(
            final String message,
            final Throwable throwable) {
        super(message, throwable);
    }

}
