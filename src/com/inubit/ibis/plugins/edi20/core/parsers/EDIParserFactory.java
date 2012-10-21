package com.inubit.ibis.plugins.edi20.core.parsers;

import com.inubit.ibis.plugins.edi20.commons.delimiters.IDelimiters;
import com.inubit.ibis.plugins.edi20.commons.parsers.IEDIParser;
import com.inubit.ibis.plugins.edi20.commons.rules.AbstractEDIRule;
import com.inubit.ibis.plugins.edi20.commons.rules.EDIRuleFactory;
import com.inubit.ibis.plugins.edi20.core.parsers.edifact.EDIFACTParser;
import com.inubit.ibis.plugins.edi20.core.parsers.edifact.delimiters.EDIFACTDelimiters;
import com.inubit.ibis.plugins.edi20.core.parsers.edifact.scanners.EDIFACTLexicalScanner;
import com.inubit.ibis.plugins.edi20.core.parsers.vda.VDAParser;
import com.inubit.ibis.plugins.edi20.core.parsers.vda.delimiters.VDADelimiters;
import com.inubit.ibis.plugins.edi20.core.parsers.vda.scanners.VDALexicalScanner;
import com.inubit.ibis.plugins.edi20.utils.EDIUtil;
import com.inubit.ibis.utils.InubitException;

/**
 * @author r4fter
 * 
 */
public final class EDIParserFactory {

	/**
	 * @param textInputDocument
	 * @return
	 */
	public static EDIParserFactory getInstance(final StringBuffer textInputDocument) {
		return new EDIParserFactory(textInputDocument);
	}

	private StringBuffer fTextInputDocument;

	private EDIParserFactory(final StringBuffer textInputDocument) {
		fTextInputDocument = textInputDocument;
	}

	private IDelimiters getDelimiter(final StringBuffer textInputDocument) {
		return new EDIFACTDelimiters(textInputDocument.substring(0, 10));
	}

	/**
	 * @return
	 * @throws InubitException
	 */
	public IEDIParser getParser() throws InubitException {
		return getParser(getRule(fTextInputDocument));
	}

	/**
	 * @param rule
	 * @return
	 * @throws InubitException
	 */
	public IEDIParser getParser(final AbstractEDIRule rule) throws InubitException {
		if (EDIUtil.isEDIFACT(fTextInputDocument)) {
			EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(fTextInputDocument, (EDIFACTDelimiters) getDelimiter(fTextInputDocument));
			return new EDIFACTParser(scanner, rule);
		} else if (EDIUtil.isVDA(fTextInputDocument)) {
		    VDALexicalScanner scanner = new VDALexicalScanner(fTextInputDocument, (VDADelimiters) getDelimiter(fTextInputDocument));
		    return new VDAParser(scanner, rule);
		}
		throw new InubitException("Unknown format of input document!");
	}

	private AbstractEDIRule getRule(final StringBuffer textInputDocument) throws InubitException {
		AbstractEDIRule rule = EDIRuleFactory.getInstance(textInputDocument, getDelimiter(fTextInputDocument)).getRule();
		System.out.println("EDIParserFactory.getRule(): rule=" + rule);
		return rule;
	}

}
