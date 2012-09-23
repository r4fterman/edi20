package com.inubit.ibis.plugins.edi20.core.parsers.edifact.scanners;

import com.inubit.ibis.plugins.edi20.commons.scanners.Token;
import com.inubit.ibis.plugins.edi20.core.parsers.edifact.delimiters.EDIFACTDelimiters;

/**
 * @author r4fter
 */
public class ComplexElementDelimiterToken extends Token {

    public ComplexElementDelimiterToken(final String tokenString, final int position) {
        super(tokenString, position, EDIFACTDelimiters.DELIMITER_COMPLEXELEMENT);
    }
}
