package com.inubit.ibis.plugins.edi20.core.parsers.edifact.scanners;

import com.inubit.ibis.plugins.edi20.commons.scanners.IToken;
import com.inubit.ibis.plugins.edi20.commons.scanners.UnknownDelimiterToken;
import com.inubit.ibis.plugins.edi20.core.parsers.edifact.delimiters.EDIFACTDelimiters;
import com.inubit.ibis.utils.StringUtils;

/**
 * @author r4fter
 */
public final class EDIFACTTokenFactory {

    private static EDIFACTTokenFactory fFactoryInstance;

    /**
     * @param edifactDelimiters
     *            EDIFACT delimiters
     * @return initialized factory instance
     */
    public static EDIFACTTokenFactory getInstance(final EDIFACTDelimiters edifactDelimiters) {
        if (edifactDelimiters == null) {
            throw new IllegalArgumentException("EDIFACT delimiters are not set!");
        }
        if (fFactoryInstance == null) {
            fFactoryInstance = new EDIFACTTokenFactory(edifactDelimiters);
        }
        return fFactoryInstance;
    }

    private EDIFACTDelimiters fDelimiter;

    private EDIFACTTokenFactory(final EDIFACTDelimiters delimiter) {
        fDelimiter = delimiter;
    }

    /**
     * @param tokenString
     *            token string
     * @param tokenPosition
     *            token position in document
     * @return token instance initialized with the given information or an {@link UnknownDelimiterToken} if the given #tokenString is unknown
     */
    public IToken getToken(final String tokenString, final int tokenPosition) {
        if (StringUtils.isNotSet(tokenString)) {
            throw new IllegalArgumentException("Token not set!");
        }
        switch (fDelimiter.getDelimiterIdentifier(tokenString)) {
        case EDIFACTDelimiters.DELIMITER_COMPLEXELEMENT:
            return new ComplexElementDelimiterToken(tokenString, tokenPosition);
        case EDIFACTDelimiters.DELIMITER_ELEMENT:
            return new ElementDelimiterToken(tokenString, tokenPosition);
        case EDIFACTDelimiters.DELIMITER_SEGMENT:
            return new SegmentDelimiterToken(tokenString, tokenPosition);
        default:
            return new UnknownDelimiterToken(tokenString, tokenPosition);
        }
    }

}
