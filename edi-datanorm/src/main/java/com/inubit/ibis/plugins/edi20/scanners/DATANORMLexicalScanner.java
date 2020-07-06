package com.inubit.ibis.plugins.edi20.scanners;

import com.inubit.ibis.plugins.edi20.parsers.delimiters.DATANORMDelimiters;

/**
 * @author r4fter
 */
public class DATANORMLexicalScanner extends EDILexicalScanner {

    /**
     * @param inputDocument
     *         EDI input document
     * @param delimiters
     *         EDI delimiters
     */
    public DATANORMLexicalScanner(
            final StringBuilder inputDocument,
            final DATANORMDelimiters delimiters) {
        super(inputDocument, delimiters);
    }

    @Override
    protected Token getNextToken(final int position) {
        int index = getIndexOfNextDelimiter(position);
        if (index == -1) {
            // no next token found
            index = getInputDocument().length();
        }
        final String tokenString = getInputDocument().substring(position, index).replaceAll("\\r\\n?", "\n");
        return createToken(position, tokenString);
    }

    private Token createToken(
            final int position,
            final String tokenString) {
        return DATANORMTokenFactory.getInstance(getDATANORMDelimiter()).getToken(tokenString, position);
    }

    private int getIndexOfNextDelimiter(final int position) {
        int index = -1;

        int idx = getIndexOfDelimiter(position, getDATANORMDelimiter().getSegmentDelimiter());
        if (idx != -1) {
            index = idx;
        }

        idx = getIndexOfDelimiter(position, getDATANORMDelimiter().getElementDelimiter());
        if (idx != -1) {
            index = idx;
        }

        return index;
    }

    private DATANORMDelimiters getDATANORMDelimiter() {
        return (DATANORMDelimiters) getDelimiters();
    }
}
