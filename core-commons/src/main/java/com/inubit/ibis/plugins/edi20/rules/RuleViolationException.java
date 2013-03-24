package com.inubit.ibis.plugins.edi20.rules;

/**
 * Created with IntelliJ IDEA. User: r4fter Date: 24.03.13 Time: 15:51 To change this template use File | Settings |
 * File Templates.
 */
public class RuleViolationException extends Exception {

    public RuleViolationException() {
        super();
    }

    public RuleViolationException(Throwable cause) {
        this("", cause);
    }

    public RuleViolationException(String message) {
        this(message, null);
    }

    public RuleViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuleViolationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
