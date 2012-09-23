package com.inubit.ibis.plugins.edi20.core.outputs;

import java.io.OutputStream;

/**
 * @author r4fter
 */
public interface IOutputWriter {

	/**
	 * Method writes result to the given output stream.
	 * 
	 * @param stream
	 *            output stream
	 */
	void write(OutputStream stream);

}
