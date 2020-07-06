package com.inubit.ibis.plugins.edi20.scanners;

import com.inubit.ibis.plugins.edi20.parsers.delimiters.DATANORMDelimiters;

/**
 * @author r4fter
 */
public class DATANORMSegmentDelimiterToken implements Token {

    private final String token;
    private final int position;

    public DATANORMSegmentDelimiterToken(
            final String tokenString,
            final int tokenPosition) {
        token = tokenString;
        position = tokenPosition;
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
