package com.inubit.ibis.plugins.edi20;

import com.inubit.ibis.utils.InubitException;

/**
 * @author rafter
 * 
 */
public interface IGenericParser {

	/**
	 * @throws InubitException
	 */
	void parse() throws InubitException;
}
