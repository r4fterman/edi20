package com.inubit.ibis.plugins.edi20.scanners;

import com.inubit.ibis.plugins.edi20.delimiters.Delimiters;

public class EDIDocumentToken implements Token {

    private final String token;
    private final int position;
    private final int delimiterType;

    public EDIDocumentToken(
            final String tokenString,
            final int position,
            final int delimiterIdentifier) {
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
        return getDelimiterType() != Delimiters.DELIMITER_UNKNOWN;
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
