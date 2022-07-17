package com.inubit.ibis.plugins.edi20.outputs;

import com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule;
import com.inubit.ibis.utils.EDIException;

public final class OutputWriterFactory {

    public static OutputWriter getInstance(final AbstractEDIRule rule) throws EDIException {
        if (rule == null) {
            throw new IllegalArgumentException("Rule is null!");
        }
        throw new EDIException("Unknown rule [" + rule.getClass().getCanonicalName() + "]!");
    }

    private OutputWriterFactory() {
        // do nothing
    }
}
