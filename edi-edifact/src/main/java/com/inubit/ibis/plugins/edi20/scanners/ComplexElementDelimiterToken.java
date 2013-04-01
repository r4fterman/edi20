package com.inubit.ibis.plugins.edi20.scanners;

import com.inubit.ibis.plugins.edi20.parsers.delimiters.EDIFACTDelimiters;

/**
 * @author r4fter
 */
public class ComplexElementDelimiterToken extends EDIDocumentToken {

    public ComplexElementDelimiterToken(final String tokenString, final int position) {
        super(tokenString, position, EDIFACTDelimiters.DELIMITER_COMPLEX_ELEMENT);
    }
}
