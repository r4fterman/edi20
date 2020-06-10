package com.inubit.ibis.plugins.edi20.rules.interfaces;

import com.inubit.ibis.plugins.edi20.rules.tokens.Loop;

/**
 * Interface indicates a token that is repeatable. <p/> The amount of
 * repetitions is retrieved by {@link #getLoop()}. Just to check whether a
 * repetition is possible use method {@link #hasLoop()}
 */
public interface RepeatableRuleToken {

    int NO_LOOP = 1;

    /**
     * @return loop count or {@link #NO_LOOP}
     */
    Loop getLoop();

    /**
     * @return <code>true</code> if loop count is greater than {@link #NO_LOOP},
     * <code>false</code> otherwise
     */
    boolean hasLoop();

    /**
     * @return true if looping is possible and looping was not reached yet,
     * false otherwise
     */
    boolean canLoop();

    /**
     * Increases the loop counter. Call method #canLoop() before loop to avoid
     * exception.
     */
    void looped();
}
