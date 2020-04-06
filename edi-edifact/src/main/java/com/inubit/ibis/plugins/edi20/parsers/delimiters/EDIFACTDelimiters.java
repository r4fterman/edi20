package com.inubit.ibis.plugins.edi20.parsers.delimiters;

import com.inubit.ibis.plugins.edi20.delimiters.IDelimiters;
import com.inubit.ibis.utils.StringUtil;

/**
 * @author r4fter
 */
public class EDIFACTDelimiters implements IDelimiters {

    public static final int DELIMITER_ELEMENT = 0;
    public static final int DELIMITER_SEGMENT = 1;
    public static final int DELIMITER_COMPLEX_ELEMENT = 2;
    public static final int DELIMITER_DECIMAL = 3;
    public static final int DELIMITER_ESCAPE = 4;

    private static final String SEGMENT_UNA = "UNA";

    private String complexElementDelimiter = ":";
    private String decimalDelimiter = ".";
    private String elementDelimiter = "+";
    private String segmentDelimiter = "'";
    private String escapeDelimiter = "?";

    /**
     * Constructor.
     */
    public EDIFACTDelimiters() {
        // do nothing
    }

    /**
     * @param beginOfDocument indicates begin of document
     */
    public EDIFACTDelimiters(final String beginOfDocument) {
        if (StringUtil.isNotSet(beginOfDocument)) {
            return;
        }
        // UNA:+.? '
        final int length = SEGMENT_UNA.length();
        if (beginOfDocument.startsWith(SEGMENT_UNA) && beginOfDocument.length() >= length + 6) {
            // complex delimiter
            setComplextElementDelimiter(beginOfDocument.substring(length, length + 1));
            // element delimiter
            setElementDelimiter(beginOfDocument.substring(length + 1, length + 2));
            // decimal delimiter
            setDecimalDelimiter(beginOfDocument.substring(length + 2, length + 3));
            // escape delimiter
            setEscapeDelimiter(beginOfDocument.substring(length + 3, length + 4));
            // not used
            // segment delimiter
            setSegmentDelimiter(beginOfDocument.substring(length + 5, length + 6));
        }
    }

    public EDIFACTDelimiters(
            final String segmentDelimiter,
            final String elementDelimiter) {
        setSegmentDelimiter(segmentDelimiter);
        setElementDelimiter(elementDelimiter);
    }

    @Override
    public boolean containsDelimiter(final String delimiter) {
        if (StringUtil.isNotSet(delimiter)) {
            return false;
        }
        if (isComplexElementDelimiter(delimiter)) {
            return true;
        }
        if (isDecimalDelimiter(delimiter)) {
            return true;
        }
        if (isElementDelimiter(delimiter)) {
            return true;
        }
        if (isEscapeDelimiter(delimiter)) {
            return true;
        }
        return isSegmentDelimiter(delimiter);
    }

    private boolean isSegmentDelimiter(final String delimiter) {
        return getSegmentDelimiter().equals(delimiter);
    }

    private boolean isEscapeDelimiter(final String delimiter) {
        return getEscapeDelimiter().equals(delimiter);
    }

    private boolean isElementDelimiter(final String delimiter) {
        return getElementDelimiter().equals(delimiter);
    }

    private boolean isDecimalDelimiter(final String delimiter) {
        return getDecimalDelimiter().equals(delimiter);
    }

    private boolean isComplexElementDelimiter(final String delimiter) {
        return getComplexElementDelimiter().equals(delimiter);
    }

    public String getComplexElementDelimiter() {
        return getDelimiter(DELIMITER_COMPLEX_ELEMENT);
    }

    public String getDecimalDelimiter() {
        return getDelimiter(DELIMITER_DECIMAL);
    }

    @Override
    public int getDelimiterIdentifier(final String delimiter) {
        if (isComplexElementDelimiter(delimiter)) {
            return DELIMITER_COMPLEX_ELEMENT;
        }
        if (isDecimalDelimiter(delimiter)) {
            return DELIMITER_DECIMAL;
        }
        if (isElementDelimiter(delimiter)) {
            return DELIMITER_ELEMENT;
        }
        if (isEscapeDelimiter(delimiter)) {
            return DELIMITER_ESCAPE;
        }
        if (isSegmentDelimiter(delimiter)) {
            return DELIMITER_SEGMENT;
        }
        return IDelimiters.DELIMITER_UNKNOWN;
    }

    @Override
    public String getDelimiter(final int delimiterIdentifier) {
        switch (delimiterIdentifier) {
            case DELIMITER_ELEMENT:
                return elementDelimiter;
            case DELIMITER_SEGMENT:
                return segmentDelimiter;
            case DELIMITER_COMPLEX_ELEMENT:
                return complexElementDelimiter;
            case DELIMITER_DECIMAL:
                return decimalDelimiter;
            case DELIMITER_ESCAPE:
                return escapeDelimiter;
            case IDelimiters.DELIMITER_UNKNOWN:
            default:
                return "";
        }
    }

    public String getElementDelimiter() {
        return getDelimiter(DELIMITER_ELEMENT);
    }

    public String getEscapeDelimiter() {
        return getDelimiter(getEscapeDelimiterIdentifier());
    }

    @Override
    public int getEscapeDelimiterIdentifier() {
        return DELIMITER_ESCAPE;
    }

    public String getSegmentDelimiter() {
        return getDelimiter(DELIMITER_SEGMENT);
    }

    public void setComplextElementDelimiter(final String complexElementDelimiter) {
        setDelimiter(complexElementDelimiter, DELIMITER_COMPLEX_ELEMENT);
    }

    public void setDecimalDelimiter(final String decimalDelimiter) {
        setDelimiter(decimalDelimiter, DELIMITER_DECIMAL);
    }

    @Override
    public void setDelimiter(
            final String delimiter,
            final int delimiterIdentifier) {
        switch (delimiterIdentifier) {
            case DELIMITER_COMPLEX_ELEMENT:
                complexElementDelimiter = delimiter;
                return;
            case DELIMITER_DECIMAL:
                decimalDelimiter = delimiter;
                return;
            case DELIMITER_ELEMENT:
                elementDelimiter = delimiter;
                return;
            case DELIMITER_ESCAPE:
                escapeDelimiter = delimiter;
                return;
            case DELIMITER_SEGMENT:
                segmentDelimiter = delimiter;
                return;
            default:
        }
    }

    public void setElementDelimiter(final String elementDelimiter) {
        setDelimiter(elementDelimiter, DELIMITER_ELEMENT);
    }

    public void setEscapeDelimiter(final String escapeDelimiter) {
        setDelimiter(escapeDelimiter, DELIMITER_ESCAPE);
    }

    public void setSegmentDelimiter(final String segmentDelimiter) {
        setDelimiter(segmentDelimiter, DELIMITER_SEGMENT);
    }

}
