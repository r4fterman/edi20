package com.inubit.ibis.plugins.edi20.rules;

import com.inubit.ibis.utils.EDIException;

/**
 * @author r4fter
 */
public class RuleViolationException extends EDIException {

    private static final long serialVersionUID = -7709090253868519629L;

    public RuleViolationException(final String message) {
        this(message, null);
    }

    public RuleViolationException(
            final String message,
            final Throwable cause) {
        super(message, cause);
    }

}
