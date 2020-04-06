package com.inubit.ibis.plugins.edi20.scanners;

/**
 * @author r4fter
 */
public class VDAUnknownDelimiterToken extends UnknownDelimiterToken {

    /**
     * @param tokenString
     *         token string
     * @param tokenPosition
     *         token position in document
     */
    public VDAUnknownDelimiterToken(
            final String tokenString,
            final int tokenPosition) {
        super(tokenString, tokenPosition);
    }

    @Override
    public IIdentifier getIdentifier() {
        return new VDASegmentIdentifier(getToken().substring(0, 3));
    }
}
