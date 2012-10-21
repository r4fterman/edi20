package com.inubit.ibis.plugins.edi20.core.parsers.vda.delimiters;

import com.inubit.ibis.plugins.edi20.commons.delimiters.IDelimiters;
import com.inubit.ibis.utils.StringUtils;

/**
 * Class contains all VDA delimiters.
 * 
 * @author r4fter
 */
public class VDADelimiters implements IDelimiters {

    public static final int DELIMITER_SEGMENT = 1;

    private String fSegmentDelimiter = "\n";

    @Override
    public String getDelimiter(final int delimiterIdentifier) {
        switch (delimiterIdentifier) {
        case DELIMITER_SEGMENT:
            return fSegmentDelimiter;
        default:
            return "";
        }
    }

    @Override
    public void setDelimiter(final String delimiter, final int delimiterIdentifier) {
        switch (delimiterIdentifier) {
        case DELIMITER_SEGMENT:
            fSegmentDelimiter = delimiter;
        }
    }

    @Override
    public int getEscapeDelimiterIndentifier() {
        return DELIMITER_UNKNOWN;
    }

    @Override
    public boolean containsDelimiter(final String delimiter) {
        if (StringUtils.isSet(delimiter)) {
            if (isSegmentDelimiter(delimiter)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSegmentDelimiter(final String delimiter) {
        return fSegmentDelimiter.equals(delimiter);
    }

    @Override
    public int getDelimiterIdentifier(final String delimiter) {
        if (StringUtils.isSet(delimiter)) {
            if (isSegmentDelimiter(delimiter)) {
                return DELIMITER_SEGMENT;
            }
        }
        return DELIMITER_UNKNOWN;
    }

    /**
     * @return segment delimiter
     */
    public String getSegmentDelimiter() {
        return fSegmentDelimiter;
    }

}
