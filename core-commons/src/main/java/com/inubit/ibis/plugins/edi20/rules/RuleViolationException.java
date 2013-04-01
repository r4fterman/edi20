package com.inubit.ibis.plugins.edi20.rules;

import com.inubit.ibis.utils.InubitException;

/**
 * @author r4fter
 */
public class RuleViolationException extends InubitException {

    public RuleViolationException(String message) {
        this(message, null);
    }

    public RuleViolationException(String message, Throwable cause) {
        super(message, cause);
    }

}
