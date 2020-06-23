package com.inubit.ibis.plugins.edi20.scanners;

import com.inubit.ibis.plugins.edi20.parsers.delimiters.EDIFACTDelimiters;

public class SegmentDelimiterToken extends EDIDocumentToken {

    /**
     * @param tokenString token string
     * @param tokenPosition token position
     */
    public SegmentDelimiterToken(
            final String tokenString,
            final int tokenPosition) {
        super(tokenString, tokenPosition, EDIFACTDelimiters.DELIMITER_SEGMENT);
    }

}
