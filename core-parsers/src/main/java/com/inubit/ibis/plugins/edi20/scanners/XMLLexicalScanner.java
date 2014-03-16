package com.inubit.ibis.plugins.edi20.scanners;

import com.inubit.ibis.plugins.edi20.delimiters.IDelimiters;
import com.inubit.ibis.utils.InubitException;
import org.dom4j.Document;

/**
 * Scanner splits input document into tokens. Tokens will be analyzed by parsers according to the given rule.
 *
 * @author rafter
 */
public abstract class XMLLexicalScanner implements IScanner {

    /**
     * @param inputDocument
     *         input document
     * @throws InubitException
     */
    public XMLLexicalScanner(final Document inputDocument) {
        // TODO: implement
    }

    public abstract IDelimiters getDelimiters();

    @Override
    public boolean hasMoreTokens() {
        return false;
    }

    @Override
    public IToken nextToken() {
        return null;
    }

    @Override
    public boolean isDelimiter(final IToken token) {
        return getDelimiters().containsDelimiter(token.getToken());
    }
}
