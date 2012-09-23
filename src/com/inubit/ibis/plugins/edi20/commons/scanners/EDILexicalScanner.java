package com.inubit.ibis.plugins.edi20.commons.scanners;

import com.inubit.ibis.plugins.edi20.commons.delimiters.IDelimiters;
import com.inubit.ibis.plugins.edi20.utils.EDIUtil;

/**
 * @author r4fter
 */
public abstract class EDILexicalScanner implements IScanner {

	private StringBuffer fInputDocument;
	private IDelimiters fDelimiter;

	private int fCurrentPosition = -1;

	public EDILexicalScanner(final StringBuffer inputDocument, final IDelimiters delimiter) {
		super();
		init(inputDocument, delimiter);
	}

	private void init(final StringBuffer inputDocument, final IDelimiters delimiter) {
		if (inputDocument == null || inputDocument.length() == 0) {
			throw new IllegalArgumentException("Input document not set!");
		}
		if (delimiter == null) {
			throw new IllegalArgumentException("Delimiter not set!");
		}
		fInputDocument = inputDocument;
		fDelimiter = delimiter;
		fCurrentPosition = 0;
	}

	@Override
	public boolean hasMoreTokens() {
		return !isEndOfDocument(fCurrentPosition);
	}

	public boolean isEndOfDocument(final int position) {
		return position == fInputDocument.length();
	}

	public StringBuffer getInputDocument() {
		return fInputDocument;
	}

	@Override
	public IToken nextToken() {
		if (!isEndOfDocument(fCurrentPosition)) {
			IToken token = getNextToken(fCurrentPosition);
			fCurrentPosition += token.getToken().length();
			return token;
		}
		return null;
	}

	protected abstract IToken getNextToken(int position);

	protected IDelimiters getDelimiters() {
		return fDelimiter;
	}

	protected int getIndexOfDelimiter(final int position, final String delimiter) {
		int currentPosition = position;
		while (!isEndOfDocument(currentPosition)) {
			int currentIndex = getInputDocument().indexOf(delimiter, currentPosition);
			// no delimiter found
			if (currentIndex == -1) {
				return -1;
			}

			String tokenWithDelimiter = getInputDocument().substring(currentPosition, currentIndex + delimiter.length());
			if (!EDIUtil.isEscaped(tokenWithDelimiter, delimiter, getEscapeDelimiter())) {
				// token with unescaped delimiter found
				if (currentIndex == currentPosition) {
					// delimiter found
					return currentIndex + delimiter.length();
				}
				return currentIndex;
			}
			// search for next delimiter
			currentPosition = currentIndex + delimiter.length();
		}
		// end of document reached
		return currentPosition;
	}

	@Override
	public boolean isDelimiter(final IToken token) {
		return getDelimiters().containsDelimiter(token.getToken());
	}
	
	private String getEscapeDelimiter() {
		return getDelimiters().getDelimiter(getDelimiters().getEscapeDelimiterIndentifier());
	}
}