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

    private EDIFACTDelimiters fDelimiter;

    private EDIFACTTokenFactory(final EDIFACTDelimiters delimiter) {
        fDelimiter = delimiter;
    }

    /**
     * @param tokenString
     * @param tokenPosition
     * @return
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

    public static EDIFACTTokenFactory getInstance(final EDIFACTDelimiters edifactDelimiter) {
        if (edifactDelimiter == null) {
            throw new IllegalArgumentException("EDIFACT delimiters are not set!");
        }
        if (fFactoryInstance == null) {
            fFactoryInstance = new EDIFACTTokenFactory(edifactDelimiter);
        }
        return fFactoryInstance;
    }

}
