package com.inubit.ibis.plugins.edi20.commons.scanners;

import com.inubit.ibis.plugins.edi20.commons.delimiters.IDelimiters;

/**
 * @author r4fter
 */
public class Token implements IToken {

    private String fToken;
    private int fPosition;
    private int fDelimiterType = IDelimiters.DELIMITER_UNKNOWN;

    /**
     * @param tokenString
     *            token string
     * @param position
     *            position in document
     */
    public Token(final String tokenString, final int position) {
        fToken = tokenString;
        fPosition = position;
    }

    /**
     * @param tokenString
     *            token string
     * @param position
     *            position in document
     * @param delimiterIdentifier
     *            delimiter identifier
     */
    public Token(final String tokenString, final int position, final int delimiterIdentifier) {
        fToken = tokenString;
        fPosition = position;
        fDelimiterType = delimiterIdentifier;
    }

    @Override
    public int getDelimiterType() {
        return fDelimiterType;
    }

    @Override
    public boolean isDelimiter() {
        return fDelimiterType != IDelimiters.DELIMITER_UNKNOWN;
    }

    @Override
    public int getPosition() {
        return fPosition;
    }

    @Override
    public String getToken() {
        return fToken;
    }

    @Override
    public String toString() {
        return getToken() + " (start:" + getPosition() + ", length:" + getToken().length() + ")";
    }

    @Override
    public void close() {
        // do nothing
    }
}
