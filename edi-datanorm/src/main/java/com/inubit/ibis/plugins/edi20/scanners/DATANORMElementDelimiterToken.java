package com.inubit.ibis.plugins.edi20.scanners;

import com.inubit.ibis.plugins.edi20.parsers.delimiters.DATANORMDelimiters;

/**
 * @author r4fter
 */
public class DATANORMElementDelimiterToken implements IToken {

    private final String token;
    private final int position;

    public DATANORMElementDelimiterToken(String tokenString, int tokenPosition) {
        this.token = tokenString;
        this.position = tokenPosition;
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
    public boolean isDelimiter() {
        return true;
    }

    @Override
    public int getDelimiterType() {
        return DATANORMDelimiters.ELEMENT_DELIMITER;
    }
}
