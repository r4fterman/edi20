/**
 *
 */
package com.inubit.ibis.plugins.edi20.scanners;

/**
 * @author r4fter
 */
public interface IScanner {

    /**
     * @return <code>true</code> if this scanner has more tokens, <code>false</code> otherwise
     */
    boolean hasMoreTokens();

    /**
     * @return next token or <code>null</code> if no such token exists
     */
    IToken nextToken();

    /**
     * @param token
     *         token to check
     * @return <code>true</code> if the given token is a delimiter, <code>false</code> otherwise
     */
    boolean isDelimiter(IToken token);

}
