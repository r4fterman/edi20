package com.inubit.ibis.plugins.edi20.scanners;

/**
 * Created with IntelliJ IDEA. User: r4fter Date: 24.03.13 Time: 15:45 To change this template use File | Settings |
 * File Templates.
 */
public class VDAUnknownDelimiterToken extends UnknownDelimiterToken {

    /**
     * @param tokenString
     *         token string
     * @param tokenPosition
     *         token position in document
     */
    public VDAUnknownDelimiterToken(String tokenString, int tokenPosition) {
        super(tokenString, tokenPosition);
    }

    @Override
    public VDASegmentIdentifier getIdentifier() {
        return new VDASegmentIdentifier(getToken().substring(0, 3));
    }
}
