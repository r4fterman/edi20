package com.inubit.ibis.plugins.edi20.scanners;

import com.inubit.ibis.plugins.edi20.parsers.delimiters.EDIFACTDelimiters;

public class ElementDelimiterToken extends EDIDocumentToken {

    /**
     * @param tokenString token string
     * @param tokenPosition token position in document
     */
    public ElementDelimiterToken(
            final String tokenString,
            final int tokenPosition) {
        super(tokenString, tokenPosition, EDIFACTDelimiters.DELIMITER_ELEMENT);
    }

}
