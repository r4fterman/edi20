/**
 * 
 */
package com.inubit.ibis.plugins.edi20.commons.scanners;

/**
 * @author r4fter
 * 
 */
public interface IToken {

	/**
	 * @return token position in document or -1 if not set
	 */
	int getPosition();

	/**
	 * @return the token
	 */
	String getToken();

	/**
	 * @return <code>true</code> if this token is a delimiter, <code>false</code> otherwise
	 */
	boolean isDelimiter();

	/**
	 * @return delimiter type or -1 if this token is no delimiter
	 */
	int getDelimiterType();

	/**
	 * Method closes this token. E.g. it can be reset for further use.
	 */
	void close();
}
