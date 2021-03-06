package com.inubit.ibis.plugins.edi20.scanners;

import com.inubit.ibis.plugins.edi20.parsers.delimiters.EDIFACTDelimiters;

/**
 * Scanner splits input document into tokens. Tokens will be analyzed by parsers
 * according to the given rule.
 *
 * @author rafter
 */
public class EDIFACTLexicalScanner extends EDILexicalScanner {

    /**
     * Constructor creates new EDI lexical scanner.
     *
     * @param inputDocument input document
     * @param delimiter delimiter
     */
    public EDIFACTLexicalScanner(
            final StringBuilder inputDocument,
            final EDIFACTDelimiters delimiter) {
        super(inputDocument, delimiter);
    }

    private EDIFACTDelimiters getEDIFACTDelimiter() {
        return (EDIFACTDelimiters) getDelimiters();
    }

    @Override
    protected Token getNextToken(final int position) {
        int index = getIndexOfNextDelimiter(position);
        if (index == -1) {
            // no next token found
            index = getInputDocument().length();
        }
        final String tokenString = getInputDocument().substring(position, index).replaceAll("\\r\\n?", "\n");
        return EDIFACTTokenFactory.getInstance(getEDIFACTDelimiter()).getToken(tokenString, position);
    }

    private int getIndexOfNextDelimiter(final int position) {
        int index = -1;

        int idx = getIndexOfDelimiter(position, getEDIFACTDelimiter().getSegmentDelimiter());
        if (idx != -1) {
            index = idx;
        }

        idx = getIndexOfDelimiter(position, getEDIFACTDelimiter().getComplexElementDelimiter());
        if (idx != -1 && idx < index) {
            index = idx;
        }

        idx = getIndexOfDelimiter(position, getEDIFACTDelimiter().getElementDelimiter());
        if (idx != -1 && idx < index) {
            index = idx;
        }
        return index;
    }

}
