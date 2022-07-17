package com.inubit.ibis.plugins.edi20.scanners;

import com.inubit.ibis.plugins.edi20.delimiters.Delimiters;

public abstract class UnknownDelimiterToken extends EDIDocumentToken {

    /**
     * @param tokenString
     *         token string
     * @param tokenPosition
     *         token position in document
     */
    protected UnknownDelimiterToken(final String tokenString, final int tokenPosition) {
        super(tokenString, tokenPosition, Delimiters.DELIMITER_UNKNOWN);
    }

    /**
     * @return token identifier
     */
    public abstract Identifier getIdentifier();
}
