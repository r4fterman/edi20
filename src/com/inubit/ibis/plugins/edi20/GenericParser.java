package com.inubit.ibis.plugins.edi20;

import org.dom4j.Document;

import com.inubit.ibis.plugins.edi20.commons.rules.AbstractEDIRule;
import com.inubit.ibis.utils.InubitException;
import com.inubit.ibis.utils.StringUtil;

/**
 * Generic parser reads input document and detects whether to parse document
 * with EDIParser or XMLParser.
 * 
 * @author rafter
 * 
 */
public class GenericParser {

	private IGenericParser fGenericParser = null;

	/**
	 * @param inputDocument
	 * @throws InubitException
	 */
	public GenericParser(final Document inputDocument) throws InubitException {
		if (inputDocument == null) {
			throw new InubitException("Input document not set correctly!");
		}
		fGenericParser = GenericParserFactory.getInstance(inputDocument);
	}

	/**
	 * @param inputText
	 * @throws InubitException
	 */
	public GenericParser(final String inputText) throws InubitException {
		if (StringUtil.isNotSet(inputText)) {
			throw new InubitException("Input text not set correctly!");
		}
		fGenericParser = GenericParserFactory.getInstance(inputText);
	}

	/**
	 * @param inputText
	 * @throws InubitException
	 */
	public GenericParser(final StringBuffer inputText) throws InubitException {
		if (inputText == null || inputText.length() == 0) {
			throw new InubitException("Input text not set correctly!");
		}
		fGenericParser = GenericParserFactory.getInstance(inputText);
	}

	public GenericParser(final StringBuffer inputText, final AbstractEDIRule rule) throws InubitException {
		if (inputText == null || inputText.length() == 0) {
			throw new InubitException("Input text not set correctly!");
		}
		fGenericParser = GenericParserFactory.getInstance(inputText, rule);
	}

	/**
	 * @throws InubitException
	 */
	public void parse() throws InubitException {
		try {
			fGenericParser.parse();
		} catch (InubitException e) {
			throw e;
		} catch (Exception e) {
			throw new InubitException(e);
		}
	}

}
