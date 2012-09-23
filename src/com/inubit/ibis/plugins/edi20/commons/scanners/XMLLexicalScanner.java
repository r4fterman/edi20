package com.inubit.ibis.plugins.edi20.commons.scanners;

import org.dom4j.Document;

import com.inubit.ibis.plugins.edi20.commons.delimiters.IDelimiters;
import com.inubit.ibis.plugins.edi20.core.parsers.edifact.delimiters.EDIFACTDelimiters;
import com.inubit.ibis.utils.InubitException;

/**
 * Scanner splits input document into tokens. Tokens will be analyzed by parser
 * according to the given rule.
 * 
 * @author rafter
 * 
 */
public class XMLLexicalScanner implements IScanner {

	/**
	 * @param inputDocument
	 *            input document
	 * @throws InubitException
	 */
	public XMLLexicalScanner(final Document inputDocument) {
		// TODO: implement
	}

	public IDelimiters getDelimiters() {
		return new EDIFACTDelimiters();
	}

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
