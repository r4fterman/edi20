package com.inubit.ibis.plugins.edi20.scanners;

import com.inubit.ibis.plugins.edi20.delimiters.Delimiters;
import org.dom4j.Document;

/**
 * Scanner splits input document into tokens. Tokens will be analyzed by parsers
 * according to the given rule.
 *
 * @author rafter
 */
public abstract class XMLLexicalScanner implements Scanner {

    /**
     * @param inputDocument
     *         input document
     */
    public XMLLexicalScanner(final Document inputDocument) {
        //todo: implement
    }

    public abstract Delimiters getDelimiters();

    @Override
    public boolean hasMoreTokens() {
        return false;
    }

    @Override
    public Token nextToken() {
        return null;
    }

    @Override
    public boolean isDelimiter(final Token token) {
        return getDelimiters().containsDelimiter(token.getToken());
    }
}
