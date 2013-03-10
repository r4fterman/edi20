package com.inubit.ibis.plugins.edi20.rules.interfaces;

/**
 * Created with IntelliJ IDEA. User: r4fter Date: 28.02.13 Time: 18:07 To change this template use File | Settings |
 * File Templates.
 */
public interface ILengthRuleToken {

    /**
     * @return element min length or -1 if not set
     */
    int getMinLength();

    /**
     * @return element max length or -1 if not set
     */
    int getMaxLength();
}
