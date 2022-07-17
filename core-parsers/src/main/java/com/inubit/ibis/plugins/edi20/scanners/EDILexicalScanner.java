package com.inubit.ibis.plugins.edi20.scanners;

import com.inubit.ibis.plugins.edi20.delimiters.Delimiters;
import com.inubit.ibis.plugins.edi20.utils.EDIUtil;

public abstract class EDILexicalScanner implements Scanner {

    private StringBuilder inputDocument;
    private Delimiters delimiters;

    private int currentPosition = -1;

    /**
     * @param inputDocument
     *         EDI input document
     * @param delimiters
     *         EDI delimiters
     */
    public EDILexicalScanner(
            final StringBuilder inputDocument,
            final Delimiters delimiters) {
        super();
        init(inputDocument, delimiters);
    }

    private void init(
            final StringBuilder inputDocument,
            final Delimiters delimiters) {
        if (inputDocument == null || inputDocument.length() == 0) {
            throw new IllegalArgumentException("Input document not set!");
        }
        if (delimiters == null) {
            throw new IllegalArgumentException("Delimiter not set!");
        }
        this.inputDocument = inputDocument;
        this.delimiters = delimiters;
        currentPosition = 0;
    }

    @Override
    public boolean hasMoreTokens() {
        return !isEndOfDocument(currentPosition);
    }

    /**
     * @param position
     *         document position
     * @return <code>true</code> if the given position is the end of the
     * document, <code>false</code> otherwise
     */
    public boolean isEndOfDocument(final int position) {
        return position == inputDocument.length();
    }

    /**
     * @return complete input document
     */
    public StringBuilder getInputDocument() {
        return inputDocument;
    }

    @Override
    public Token nextToken() {
        if (!isEndOfDocument(currentPosition)) {
            final Token token = getNextToken(currentPosition);
            currentPosition += token.getToken().length();
            return token;
        }
        return null;
    }

    /**
     * @param position
     *         document position
     * @return next token at the given position in document
     */
    protected abstract Token getNextToken(int position);

    /**
     * @return delimiters or <code>null</code> if not set
     */
    public Delimiters getDelimiters() {
        return delimiters;
    }

    /**
     * Method returns the index for the given delimiter starting at the
     * specified position.
     *
     * @param position
     *         document position
     * @param delimiter
     *         delimiter string
     * @return index in the document or -1 if delimiter was not found
     */
    protected int getIndexOfDelimiter(
            final int position,
            final String delimiter) {
        int currentPosition = position;
        while (!isEndOfDocument(currentPosition)) {
            final int currentIndex = getInputDocument().indexOf(delimiter, currentPosition);
            // no delimiter found
            if (currentIndex == -1) {
                return -1;
            }

            final String tokenWithDelimiter = getInputDocument().substring(currentPosition, currentIndex + delimiter.length());
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
    public boolean isDelimiter(final Token token) {
        return getDelimiters().containsDelimiter(token.getToken());
    }

    private String getEscapeDelimiter() {
        return getDelimiters().getDelimiter(getDelimiters().getEscapeDelimiterIdentifier());
    }
}
