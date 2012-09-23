/**
 * 
 */
package com.inubit.ibis.plugins.edi20.core.parsers.edifact.delimiters;

import com.inubit.ibis.plugins.edi20.commons.delimiters.IDelimiters;
import com.inubit.ibis.utils.StringUtils;

/**
 * @author r4fter
 */
public class EDIFACTDelimiters implements IDelimiters {

	public static final int DELIMITER_ELEMENT = 0;
	public static final int DELIMITER_SEGMENT = 1;
	public static final int DELIMITER_COMPLEXELEMENT = 2;
	public static final int DELIMITER_DECIMAL = 3;
	public static final int DELIMITER_ESCAPE = 4;

	private static final String SEGMENT_UNA = "UNA";

	private String fComplexElementDelimiter = ":";
	private String fDecimalDelimiter = ".";
	private String fElementDelimiter = "+";
	private String fSegmentDelimiter = "'";
	private String fEscapeDelimiter = "?";

	/**
	 * Constructor.
	 */
	public EDIFACTDelimiters() {
		// do nothing
	}

	/**
	 * @param beginOfDocument
	 */
	public EDIFACTDelimiters(final String beginOfDocument) {
		if (StringUtils.isNotSet(beginOfDocument)) {
			return;
		}
		// UNA:+.? '
		int length = SEGMENT_UNA.length();
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

	public EDIFACTDelimiters(final String segmentDelimiter, final String elementDelimiter) {
		setSegmentDelimiter(segmentDelimiter);
		setElementDelimiter(elementDelimiter);
	}

	@Override
	public boolean containsDelimiter(final String delimiter) {
		if (StringUtils.isNotSet(delimiter)) {
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
		if (isSegmentDelimiter(delimiter)) {
			return true;
		}
		return false;
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
		return getDelimiter(DELIMITER_COMPLEXELEMENT);
	}

	public String getDecimalDelimiter() {
		return getDelimiter(DELIMITER_DECIMAL);
	}

	@Override
	public int getDelimiterIdentifier(final String delimiter) {
		if (isComplexElementDelimiter(delimiter)) {
			return DELIMITER_COMPLEXELEMENT;
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
		return DELIMITER_UNKNOWN;
	}

	@Override
	public String getDelimiter(final int delimiterIdentifier) {
		switch (delimiterIdentifier) {
		case DELIMITER_ELEMENT:
			return fElementDelimiter;
		case DELIMITER_SEGMENT:
			return fSegmentDelimiter;
		case DELIMITER_COMPLEXELEMENT:
			return fComplexElementDelimiter;
		case DELIMITER_DECIMAL:
			return fDecimalDelimiter;
		case DELIMITER_ESCAPE:
			return fEscapeDelimiter;
		case DELIMITER_UNKNOWN:
		default:
			return "";
		}
	}

	public String getElementDelimiter() {
		return getDelimiter(DELIMITER_ELEMENT);
	}

	public String getEscapeDelimiter() {
		return getDelimiter(getEscapeDelimiterIndentifier());
	}

	@Override
	public int getEscapeDelimiterIndentifier() {
		return DELIMITER_ESCAPE;
	}

	public String getSegmentDelimiter() {
		return getDelimiter(DELIMITER_SEGMENT);
	}

	public void setComplextElementDelimiter(final String complexElementDelimiter) {
		setDelimiter(complexElementDelimiter, DELIMITER_COMPLEXELEMENT);
	}

	public void setDecimalDelimiter(final String decimalDelimiter) {
		setDelimiter(decimalDelimiter, DELIMITER_DECIMAL);
	}

	@Override
	public void setDelimiter(final String delimiter, final int delimiterIdentifier) {
		switch (delimiterIdentifier) {
		case DELIMITER_COMPLEXELEMENT:
			fComplexElementDelimiter = delimiter;
			return;
		case DELIMITER_DECIMAL:
			fDecimalDelimiter = delimiter;
			return;
		case DELIMITER_ELEMENT:
			fElementDelimiter = delimiter;
			return;
		case DELIMITER_ESCAPE:
			fEscapeDelimiter = delimiter;
			return;
		case DELIMITER_SEGMENT:
			fSegmentDelimiter = delimiter;
			return;
		default:
			return;
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
