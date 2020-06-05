package com.inubit.ibis.plugins.edi20.scanners;

import com.inubit.ibis.plugins.edi20.parsers.delimiters.VDADelimiters;

/**
 * @author r4fter
 */
public class VDALexicalScanner extends EDILexicalScanner {

    /**
     * @param textInputDocument
     *         text input document
     * @param delimiters
     *         VDA delimiters
     */
    public VDALexicalScanner(
            final StringBuilder textInputDocument,
            final VDADelimiters delimiters) {
        super(textInputDocument, delimiters);
    }

    @Override
    protected IToken getNextToken(final int position) {
        int index = getIndexOfNextDelimiter(position);
        if (index == -1) {
            // no next token found
            index = getInputDocument().length();
        }
        final String tokenString = getInputDocument().substring(position, index).replaceAll("\\r\\n?", "\n");
        return createToken(position, tokenString);
    }

    private IToken createToken(
            final int position,
            final String tokenString) {
        return VDATokenFactory.getInstance(getVDADelimiter()).getToken(tokenString, position);
    }

    private int getIndexOfNextDelimiter(final int position) {
        return getIndexOfDelimiter(position, getVDADelimiter().getSegmentDelimiter());
    }

    private VDADelimiters getVDADelimiter() {
        return (VDADelimiters) getDelimiters();
    }

}
