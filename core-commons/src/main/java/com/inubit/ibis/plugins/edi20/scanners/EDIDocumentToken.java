package com.inubit.ibis.plugins.edi20.scanners;

import com.inubit.ibis.plugins.edi20.delimiters.IDelimiters;

/**
 * @author r4fter
 */
public class EDIDocumentToken implements IToken {

    private String token;
    private int position;
    private int delimiterType = IDelimiters.DELIMITER_UNKNOWN;

    /**
     * @param tokenString
     *         token string
     * @param position
     *         position in document
     * @param delimiterIdentifier
     *         delimiter identifier
     */
    public EDIDocumentToken(final String tokenString, final int position, final int delimiterIdentifier) {
        this.token = tokenString;
        this.position = position;
        this.delimiterType = delimiterIdentifier;
    }

    @Override
    public int getDelimiterType() {
        return delimiterType;
    }

    @Override
    public boolean isDelimiter() {
        return getDelimiterType() != IDelimiters.DELIMITER_UNKNOWN;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return getToken() + " (start:" + getPosition() + ", length:" + getToken().length() + ")";
    }

}
