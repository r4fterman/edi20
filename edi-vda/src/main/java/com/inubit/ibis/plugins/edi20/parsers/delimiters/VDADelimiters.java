package com.inubit.ibis.plugins.edi20.parsers.delimiters;

import com.inubit.ibis.plugins.edi20.delimiters.Delimiters;

/**
 * Class contains all VDA delimiters.
 *
 * @author r4fter
 */
public class VDADelimiters implements Delimiters {

    public static final int DELIMITER_SEGMENT = 1;

    private String segmentDelimiter = "\n";

    @Override
    public String getDelimiter(final int delimiterIdentifier) {
        if (delimiterIdentifier == DELIMITER_SEGMENT) {
            return segmentDelimiter;
        }
        return "";
    }

    @Override
    public void setDelimiter(
            final String delimiter,
            final int delimiterIdentifier) {
        if (delimiterIdentifier == DELIMITER_SEGMENT) {
            segmentDelimiter = delimiter;
        } else {
            throw new IllegalStateException("Unexpected value: " + delimiterIdentifier);
        }
    }

    @Override
    public int getEscapeDelimiterIdentifier() {
        return DELIMITER_UNKNOWN;
    }

    @Override
    public boolean containsDelimiter(final String delimiter) {
        return isSegmentDelimiter(delimiter);
    }

    private boolean isSegmentDelimiter(final String delimiter) {
        return segmentDelimiter.equals(delimiter);
    }

    @Override
    public int getDelimiterIdentifier(final String delimiter) {
        if (isSegmentDelimiter(delimiter)) {
            return DELIMITER_SEGMENT;
        }
        return DELIMITER_UNKNOWN;
    }

    /**
     * @return segment delimiter
     */
    public String getSegmentDelimiter() {
        return getDelimiter(DELIMITER_SEGMENT);
    }

}
