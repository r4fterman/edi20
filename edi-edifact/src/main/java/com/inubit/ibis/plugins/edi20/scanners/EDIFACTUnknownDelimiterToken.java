package com.inubit.ibis.plugins.edi20.scanners;

/**
 * @author r4fter
 */
public class EDIFACTUnknownDelimiterToken extends UnknownDelimiterToken {

    /**
     * @param tokenString token string
     * @param tokenPosition token position in document
     */
    public EDIFACTUnknownDelimiterToken(
            final String tokenString,
            final int tokenPosition) {
        super(tokenString, tokenPosition);
    }

    @Override
    public Identifier getIdentifier() {
        return new EDIFACTSegmentIdentifier(getToken().substring(0, 3));
    }
}
