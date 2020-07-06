package com.inubit.ibis.plugins.edi20.scanners;

import com.inubit.ibis.plugins.edi20.parsers.delimiters.DATANORMDelimiters;
import org.apache.commons.lang.StringUtils;

/**
 * @author r4fter
 */
public class DATANORMTokenFactory {

    private static DATANORMTokenFactory factoryInstance;

    public static DATANORMTokenFactory getInstance(final DATANORMDelimiters datanormDelimiter) {
        if (datanormDelimiter == null) {
            throw new IllegalArgumentException("DATANORM delimiters are not set!");
        }

        if (factoryInstance == null) {
            factoryInstance = new DATANORMTokenFactory(datanormDelimiter);
        }
        return factoryInstance;
    }

    private final DATANORMDelimiters delimiters;

    public DATANORMTokenFactory(final DATANORMDelimiters datanormDelimiters) {
        delimiters = datanormDelimiters;
    }

    public Token getToken(
            final String tokenString,
            final int tokenPosition) {
        if (StringUtils.isEmpty(tokenString)) {
            throw new IllegalArgumentException("Token not set!");
        }
        switch (delimiters.getDelimiterIdentifier(tokenString)) {
            case DATANORMDelimiters.ELEMENT_DELIMITER:
                return new DATANORMElementDelimiterToken(tokenString, tokenPosition);
            case DATANORMDelimiters.SEGMENT_DELIMITER:
                return new DATANORMSegmentDelimiterToken(tokenString, tokenPosition);
            default:
                return new DATANORMUnknownDelimiterToken(tokenString, tokenPosition);
        }
    }
}
