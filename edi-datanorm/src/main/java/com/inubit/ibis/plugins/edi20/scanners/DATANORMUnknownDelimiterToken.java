package com.inubit.ibis.plugins.edi20.scanners;

/**
 * @author r4fter
 */
public class DATANORMUnknownDelimiterToken extends UnknownDelimiterToken {

    /**
     * @param tokenString
     *         token string
     * @param tokenPosition
     *         token position in document
     */
    public DATANORMUnknownDelimiterToken(
            final String tokenString,
            final int tokenPosition) {
        super(tokenString, tokenPosition);
    }

    @Override
    public IIdentifier getIdentifier() {
        final String token = getToken();
        int idx = 1;
        if (token.contains(";")) {
            idx = token.indexOf(";");
        }
        return new DATANORMSegmentIdentifier(getToken().substring(0, idx));
    }
}
