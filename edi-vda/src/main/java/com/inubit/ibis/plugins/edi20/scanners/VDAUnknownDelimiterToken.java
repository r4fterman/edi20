package com.inubit.ibis.plugins.edi20.scanners;

public class VDAUnknownDelimiterToken extends UnknownDelimiterToken {

    public VDAUnknownDelimiterToken(
            final String tokenString,
            final int tokenPosition) {
        super(tokenString, tokenPosition);
    }

    @Override
    public Identifier getIdentifier() {
        // identifier can be anywhere inside the token
        final int beginIndex = 0;
        final int endIndex = 3;

        final String identifierPart = getToken().substring(beginIndex, endIndex);
        return new VDASegmentIdentifier(identifierPart);
    }
}
