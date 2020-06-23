package com.inubit.ibis.plugins.edi20.scanners;

import com.inubit.ibis.plugins.edi20.parsers.delimiters.VDADelimiters;
import com.inubit.ibis.utils.StringUtil;

public final class VDATokenFactory {

    private static VDATokenFactory factoryInstance;

    /**
     * Initialized factory instance.
     *
     * @param vdaDelimiters
     *         VDA delimiters
     * @return factory instance
     */
    public static VDATokenFactory getInstance(final VDADelimiters vdaDelimiters) {
        if (vdaDelimiters == null) {
            throw new IllegalArgumentException("VDA delimiters are not set!");
        }
        if (factoryInstance == null) {
            factoryInstance = new VDATokenFactory(vdaDelimiters);
        }
        return factoryInstance;
    }

    private final VDADelimiters vdaDelimiters;

    private VDATokenFactory(final VDADelimiters vdaDelimiters) {
        this.vdaDelimiters = vdaDelimiters;
    }

    /**
     * @param tokenString
     *         token string
     * @param tokenPosition
     *         token position in document
     * @return token instance initialized with the given information or an
     * {@link UnknownDelimiterToken} if the given #tokenString is unknown
     */
    public Token getToken(
            final String tokenString,
            final int tokenPosition) {
        if (StringUtil.isNotSet(tokenString)) {
            throw new IllegalArgumentException("Token not set!");
        }
        if (vdaDelimiters.getDelimiterIdentifier(tokenString) == VDADelimiters.DELIMITER_SEGMENT) {
            return new VDASegmentDelimiterToken(tokenString, tokenPosition);
        }
        return new VDAUnknownDelimiterToken(tokenString, tokenPosition);
    }

}
