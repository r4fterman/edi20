package com.inubit.ibis.plugins.edi20.commons.scanners;

import com.inubit.ibis.plugins.edi20.commons.delimiters.IDelimiters;

/**
 * @author r4fter
 */
public class UnknownDelimiterToken extends Token {

    /**
     * @param tokenString
     *            token string
     * @param tokenPosition
     *            token position in document
     */
    public UnknownDelimiterToken(final String tokenString, final int tokenPosition) {
        super(tokenString, tokenPosition, IDelimiters.DELIMITER_UNKNOWN);
    }

}
