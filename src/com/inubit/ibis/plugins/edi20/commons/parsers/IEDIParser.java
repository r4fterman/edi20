package com.inubit.ibis.plugins.edi20.commons.parsers;

import com.inubit.ibis.utils.InubitException;

/**
 * @author r4fter
 * 
 */
public interface IEDIParser {

	/**
	 * @throws InubitException
	 */
	void parse() throws InubitException;
}
