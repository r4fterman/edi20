package com.inubit.ibis.plugins.edi20.parsers.vda.scanners;

import com.inubit.ibis.plugins.edi20.parsers.vda.delimiters.VDADelimiters;
import com.inubit.ibis.plugins.edi20.scanners.EDIDocumentToken;

/**
 * @author r4fter
 */
public class VDASegmentDelimiterToken extends EDIDocumentToken {

    /**
     * @param tokenString
     *         document token string
     * @param position
     *         document position
     */
    public VDASegmentDelimiterToken(final String tokenString, final int position) {
        super(tokenString, position, VDADelimiters.DELIMITER_SEGMENT);
    }

}
