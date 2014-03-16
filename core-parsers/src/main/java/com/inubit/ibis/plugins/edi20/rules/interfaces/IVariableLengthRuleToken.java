package com.inubit.ibis.plugins.edi20.rules.interfaces;

/**
 * @author r4fter
 */
public interface IVariableLengthRuleToken {

    /**
     * @return element min length or -1 if not set
     */
    int getMinLength();

    /**
     * @return element max length or -1 if not set
     */
    int getMaxLength();
}
