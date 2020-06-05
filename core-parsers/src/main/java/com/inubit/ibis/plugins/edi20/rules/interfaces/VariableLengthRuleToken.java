package com.inubit.ibis.plugins.edi20.rules.interfaces;

public interface VariableLengthRuleToken {

    /**
     * @return element min length or -1 if not set
     */
    int getMinLength();

    /**
     * @return element max length or -1 if not set
     */
    int getMaxLength();
}
