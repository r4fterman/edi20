package com.inubit.ibis.plugins.edi20.scanners;

import com.inubit.ibis.plugins.edi20.parsers.delimiters.DATANORMDelimiters;

/**
 * @author r4fter
 */
public class DATANORMSegmentDelimiterToken implements IToken {

    private final String token;
    private final int position;

    public DATANORMSegmentDelimiterToken(String tokenString, int tokenPosition) {
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
        return DATANORMDelimiters.SEGMENT_DELIMITER;
    }
}
