package com.inubit.ibis.plugins.edi20.scanners;

import com.inubit.ibis.plugins.edi20.delimiters.IDelimiters;
import com.inubit.ibis.plugins.edi20.utils.EDIUtil;

/**
 * @author r4fter
 */
public abstract class EDILexicalScanner implements IScanner {

    private StringBuilder fInputDocument;
    private IDelimiters fDelimiters;

    private int fCurrentPosition = -1;

    /**
     * @param inputDocument
     *         EDI input document
     * @param delimiters
     *         EDI delimiters
     */
    public EDILexicalScanner(final StringBuilder inputDocument, final IDelimiters delimiters) {
        super();
        init(inputDocument, delimiters);
    }

    private void init(final StringBuilder inputDocument, final IDelimiters delimiters) {
        if (inputDocument == null || inputDocument.length() == 0) {
            throw new IllegalArgumentException("Input document not set!");
        }
        if (delimiters == null) {
            throw new IllegalArgumentException("Delimiter not set!");
        }
        fInputDocument = inputDocument;
        fDelimiters = delimiters;
        fCurrentPosition = 0;
    }

    @Override
    public boolean hasMoreTokens() {
        return !isEndOfDocument(fCurrentPosition);
    }

    /**
     * @param position
     *         document position
     * @return <code>true</code> if the given position is the end of the document, <code>false</code> otherwise
     */
    public boolean isEndOfDocument(final int position) {
        return position == fInputDocument.length();
    }

    /**
     * @return complete input document
     */
    public StringBuilder getInputDocument() {
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

    /**
     * @param position
     *         document position
     * @return next token at the given position in document
     */
    protected abstract IToken getNextToken(int position);

    /**
     * @return delimiters or <code>null</code> if not set
     */
    protected IDelimiters getDelimiters() {
        return fDelimiters;
    }

    /**
     * Method returns the index for the given delimiter starting at the specified position.
     *
     * @param position
     *         document position
     * @param delimiter
     *         delimiter string
     * @return index in the document or -1 if delimiter was not found
     */
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