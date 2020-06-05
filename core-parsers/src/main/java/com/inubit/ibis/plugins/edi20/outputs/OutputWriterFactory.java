package com.inubit.ibis.plugins.edi20.outputs;

import com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule;
import com.inubit.ibis.utils.InubitException;

/**
 * @author r4fter
 */
public final class OutputWriterFactory {

    public static OutputWriter getInstance(final AbstractEDIRule rule) throws InubitException {
        if (rule == null) {
            throw new IllegalArgumentException("Rule is null!");
        }
        throw new InubitException("Unknown rule [" + rule.getClass().getCanonicalName() + "]!");
    }

}
