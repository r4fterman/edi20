package com.inubit.ibis.plugins.edi20.core.parsers.edifact.scanners;

import com.inubit.ibis.plugins.edi20.commons.scanners.EDIDocumentToken;
import com.inubit.ibis.plugins.edi20.core.parsers.edifact.delimiters.EDIFACTDelimiters;

/**
 * @author r4fter
 */
public class ElementDelimiterToken extends EDIDocumentToken {

    /**
     * @param tokenString
     *            token string
     * @param tokenPosition
     *            token position in document
     */
    public ElementDelimiterToken(final String tokenString, final int tokenPosition) {
        super(tokenString, tokenPosition, EDIFACTDelimiters.DELIMITER_ELEMENT);
    }

}
