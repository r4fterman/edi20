package com.inubit.ibis.plugins.edi20.rules;

import com.inubit.ibis.utils.EDIException;

public class InvalidRuleException extends EDIException {

    public InvalidRuleException() {
        super("Invalid rule document!");
    }
}
