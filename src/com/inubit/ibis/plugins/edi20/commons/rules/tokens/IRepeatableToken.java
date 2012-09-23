package com.inubit.ibis.plugins.edi20.commons.rules.tokens;

/**
 * Interface indicates a token that is repeatable.
 * 
 * The amount of repetitions is retrieved by {@link #getLoop()}. Just to check whether a repetition is possible use method {@link #hasLoop()}
 * 
 * @author r4fter
 */
public interface IRepeatableToken {

	static final int NO_LOOP = 1;

	/**
	 * @return loop count or {@link #NO_LOOP}
	 */
	int getLoop();

	/**
	 * @return <code>true</code> if loop count is greater than {@link #NO_LOOP}, <code>false</code> otherwise
	 */
	boolean hasLoop();
}
