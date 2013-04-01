package com.inubit.ibis.plugins.edi20.scanners;

/**
 * @author r4fter
 */
public class EDIFACTUnknownDelimiterToken extends UnknownDelimiterToken {

    /**
     * @param tokenString
     *         token string
     * @param tokenPosition
     *         token position in document
     */
    public EDIFACTUnknownDelimiterToken(String tokenString, int tokenPosition) {
        super(tokenString, tokenPosition);
    }

    @Override
    public IIdentifier getIdentifier() {
        return new EDIFACTSegmentIdentifier(getToken().substring(0, 3));
    }
}
