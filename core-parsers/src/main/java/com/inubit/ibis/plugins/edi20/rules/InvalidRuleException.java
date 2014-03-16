package com.inubit.ibis.plugins.edi20.rules;

import com.inubit.ibis.utils.InubitException;

/**
 * @r4fter
 */
public class InvalidRuleException extends InubitException {

    public InvalidRuleException() {
        super("Invalid rule document!");
    }
}
