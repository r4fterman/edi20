package com.inubit.ibis.plugins.edi20.rules.interfaces;

public interface FixedLengthRuleToken {

    /**
     * @return start position or -1 if not set
     */
    int getFromPosition();

    /**
     * @return end position or -1 if not set
     */
    int getToPosition();
}
