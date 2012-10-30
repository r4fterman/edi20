package com.inubit.ibis.plugins.edi20.core.parsers.vda.scanners;

import com.inubit.ibis.plugins.edi20.commons.scanners.EDIDocumentToken;
import com.inubit.ibis.plugins.edi20.core.parsers.vda.delimiters.VDADelimiters;

/**
 * @author r4fter
 */
public class VDASegmentDelimiterToken extends EDIDocumentToken {

    /**
     * @param tokenString
     *            document token string
     * @param position
     *            document position
     */
    public VDASegmentDelimiterToken(final String tokenString, final int position) {
        super(tokenString, position, VDADelimiters.DELIMITER_SEGMENT);
    }

}
