package com.inubit.ibis.plugins.edi20.rules.interfaces;

/**
 * @author r4fter
 */
public interface IFixedLengthRuleToken {

    /**
     * @return start position or -1 if not set
     */
    int getFromPosition();

    /**
     * @return end position or -1 if not set
     */
    int getToPosition();
}
