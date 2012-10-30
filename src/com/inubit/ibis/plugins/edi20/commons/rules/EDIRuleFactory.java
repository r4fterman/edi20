package com.inubit.ibis.plugins.edi20.commons.rules;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;

import com.inubit.ibis.plugins.edi20.commons.delimiters.IDelimiters;
import com.inubit.ibis.plugins.edi20.commons.scanners.IToken;
import com.inubit.ibis.plugins.edi20.core.parsers.edifact.delimiters.EDIFACTDelimiters;
import com.inubit.ibis.plugins.edi20.core.parsers.edifact.rules.EDIFACTRule;
import com.inubit.ibis.plugins.edi20.core.parsers.edifact.scanners.EDIFACTLexicalScanner;
import com.inubit.ibis.plugins.edi20.utils.EDIUtil;
import com.inubit.ibis.utils.InubitException;
import com.inubit.ibis.utils.StringUtil;
import com.inubit.ibis.utils.XmlFileFilter;
import com.inubit.ibis.utils.XmlUtils;

/**
 * @author r4fter
 * 
 */
public final class EDIRuleFactory {

	private static final String DELIMITER_RULE_FILE_PART = "-";

	private static EDIRuleFactory fInstance;

	public static EDIRuleFactory getInstance(final StringBuffer textInputDocument, final IDelimiters delimiter) {
		if (fInstance == null) {
			fInstance = new EDIRuleFactory(textInputDocument, delimiter);
		}
		return fInstance;
	}

	private StringBuffer fTextInputDocument;
	private IDelimiters fDelimiter;

	private EDIRuleFactory(final StringBuffer textInputDocument, final IDelimiters delimiter) {
		fTextInputDocument = textInputDocument;
		fDelimiter = delimiter;
	}

	private AbstractEDIRule getEDIFACTRule(final StringBuffer textInputDocument, final boolean detectStandardRuleFileName) throws InubitException {
		int idx = textInputDocument.indexOf("UNH");
		if (idx == -1) {
			throw new InubitException("Unable to auto detect rule file. No version information found in EDIFACT message!");
		}
		StringBuffer autoDetectPart = new StringBuffer(textInputDocument.substring(idx));
		EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(autoDetectPart, (EDIFACTDelimiters) fDelimiter);
		String ruleFileName = getEDIFACTRuleFileName(scanner, detectStandardRuleFileName);
		if (ruleFileName.endsWith(DELIMITER_RULE_FILE_PART)) {
			ruleFileName = ruleFileName.substring(0, ruleFileName.length() - 1);
		}

		String edifactRuleFileName = ruleFileName + XmlFileFilter.FILE_EXTENSION_XML;
		File edifactRuleFile = new File(EDIUtil.RULEFILE_FOLDER, edifactRuleFileName);
		if (!edifactRuleFile.exists()) {
			throw new InubitException("EDI rule file not found [" + edifactRuleFile.getAbsolutePath() + "]!");
		}
		try {
			Document edifactRuleDocument = XmlUtils.getDocumentThrowing(edifactRuleFile);
			return new EDIFACTRule(edifactRuleDocument);
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new InubitException("Unable to load rule file: " + edifactRuleFileName, e);
		}
	}

	private String getEDIFACTRuleFileName(final EDIFACTLexicalScanner scanner, final boolean detectStandardRuleFileName) {
		try {
			if (scanner.hasMoreTokens()) {
				// UNH
				scanner.nextToken();
				// delimiter
				scanner.nextToken();
				// S009
				scanner.nextToken();
				// delimiter
				scanner.nextToken();
			}
			StringBuilder builder = new StringBuilder("EDIFACT-");

			// message type
			appendNextTokenToBuilder(scanner, builder, true);
			scanner.nextToken();

			// version number
			appendNextTokenToBuilder(scanner, builder, true);
			scanner.nextToken();

			// release number
			appendNextTokenToBuilder(scanner, builder, true);
			scanner.nextToken();

			if (!detectStandardRuleFileName) {
				// controlling agency
				appendNextTokenToBuilder(scanner, builder, false);
				scanner.nextToken();

				// association assigned
				appendNextTokenToBuilder(scanner, builder, false);
				scanner.nextToken();

				// codelist directory version number
				appendNextTokenToBuilder(scanner, builder, false);
				scanner.nextToken();

				// sub function identification
				appendNextTokenToBuilder(scanner, builder, false);
				scanner.nextToken();
			}
			return builder.toString();
		} catch (InubitException e) {
			return "";
		}
	}

	private void appendNextTokenToBuilder(EDIFACTLexicalScanner scanner, StringBuilder builder, boolean mandatory) throws InubitException {
		IToken messageTypeToken = scanner.nextToken();
		if (StringUtil.isSet(messageTypeToken.getToken())) {
			builder.append(messageTypeToken.getToken());
			builder.append(DELIMITER_RULE_FILE_PART);
		} else {
			if (mandatory) {
				throw new InubitException("Mandatory rule file part not found!");
			}
		}
	}

	public AbstractEDIRule getRule() throws InubitException {
		return getRule(true);
	}

	public AbstractEDIRule getExtendedRule() throws InubitException {
		return getRule(false);
	}

	private AbstractEDIRule getRule(final boolean detectStandardRuleFileName) throws InubitException {
		if (StringUtil.isNotSet(fTextInputDocument)) {
			throw new InubitException("Unable to auto detect rule file an empty input document!");
		}
		if (EDIUtil.isEDIFACT(fTextInputDocument)) {
			return getEDIFACTRule(fTextInputDocument, detectStandardRuleFileName);
		}
		throw new InubitException("Rule file auto detection is not supported for this message!");
	}
}
