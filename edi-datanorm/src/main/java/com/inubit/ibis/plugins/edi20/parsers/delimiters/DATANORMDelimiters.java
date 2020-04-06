package com.inubit.ibis.plugins.edi20.parsers.delimiters;

import com.inubit.ibis.plugins.edi20.delimiters.IDelimiters;

/**
 * @author r4fter
 */
public class DATANORMDelimiters implements IDelimiters {

    public static final int SEGMENT_DELIMITER = 1;
    public static final int ELEMENT_DELIMITER = 2;

    private String segmentDelimiter = "\n";
    private String elementDelimiter = ";";

    @Override
    public String getDelimiter(final int delimiterIdentifier) {
        switch (delimiterIdentifier) {
            case SEGMENT_DELIMITER:
                return segmentDelimiter;
            case ELEMENT_DELIMITER:
                return elementDelimiter;
            default:
                return "";
        }
    }

    @Override
    public void setDelimiter(
            final String delimiter,
            final int delimiterIdentifier) {
        switch (delimiterIdentifier) {
            case SEGMENT_DELIMITER:
                segmentDelimiter = delimiter;
                break;
            case ELEMENT_DELIMITER:
                elementDelimiter = delimiter;
                break;
            default:
                break;
        }
    }

    @Override
    public int getEscapeDelimiterIdentifier() {
        return DELIMITER_UNKNOWN;
    }

    @Override
    public boolean containsDelimiter(final String delimiter) {
        return isSegmentDelimiter(delimiter) || isElementDelimiter(delimiter);
    }

    private boolean isElementDelimiter(final String delimiter) {
        return elementDelimiter.equals(delimiter);
    }

    private boolean isSegmentDelimiter(final String delimiter) {
        return segmentDelimiter.equals(delimiter);
    }

    @Override
    public int getDelimiterIdentifier(final String delimiter) {
        if (isSegmentDelimiter(delimiter)) {
            return SEGMENT_DELIMITER;
        }
        if (isElementDelimiter(delimiter)) {
            return ELEMENT_DELIMITER;
        }
        return DELIMITER_UNKNOWN;
    }

    public String getSegmentDelimiter() {
        return getDelimiter(SEGMENT_DELIMITER);
    }

    public String getElementDelimiter() {
        return getDelimiter(ELEMENT_DELIMITER);
    }
}
