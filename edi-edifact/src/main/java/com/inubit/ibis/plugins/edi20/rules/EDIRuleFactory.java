package com.inubit.ibis.plugins.edi20.rules;

import com.inubit.ibis.plugins.edi20.delimiters.Delimiters;
import com.inubit.ibis.plugins.edi20.parsers.delimiters.EDIFACTDelimiters;
import com.inubit.ibis.plugins.edi20.scanners.EDIFACTLexicalScanner;
import com.inubit.ibis.plugins.edi20.scanners.Token;
import com.inubit.ibis.utils.EDIException;
import com.inubit.ibis.utils.StringUtil;
import com.inubit.ibis.utils.XmlFileFilter;
import com.inubit.ibis.utils.XmlUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;

import java.io.File;

public final class EDIRuleFactory {

    private static final String DELIMITER_RULE_FILE_PART = "-";

    private static EDIRuleFactory fInstance;

    public static EDIRuleFactory getInstance(
            final StringBuilder textInputDocument,
            final Delimiters delimiter) {
        if (fInstance == null) {
            fInstance = new EDIRuleFactory(textInputDocument, delimiter);
        }
        return fInstance;
    }

    private final StringBuilder textInputDocument;
    private final Delimiters delimiter;

    private EDIRuleFactory(
            final StringBuilder textInputDocument,
            final Delimiters delimiter) {
        this.textInputDocument = textInputDocument;
        this.delimiter = delimiter;
    }

    private AbstractEDIRule getEDIFACTRule(
            final StringBuilder textInputDocument,
            final boolean detectStandardRuleFileName) throws EDIException {
        final int idx = textInputDocument.indexOf("UNH");
        if (idx == -1) {
            throw new EDIException("Unable to auto detect rule file. No version information found in EDIFACT message!");
        }
        final StringBuilder autoDetectPart = new StringBuilder(textInputDocument.substring(idx));
        final EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(autoDetectPart, (EDIFACTDelimiters) delimiter);
        String ruleFileName = getEDIFACTRuleFileName(scanner, detectStandardRuleFileName);
        if (ruleFileName.endsWith(DELIMITER_RULE_FILE_PART)) {
            ruleFileName = ruleFileName.substring(0, ruleFileName.length() - 1);
        }

        final String edifactRuleFileName = ruleFileName + XmlFileFilter.FILE_EXTENSION_XML;
        final File edifactRuleFile = new File("./", edifactRuleFileName);
        if (!edifactRuleFile.exists()) {
            throw new EDIException("EDI rule file not found [" + edifactRuleFile.getAbsolutePath() + "]!");
        }
        try {
            final Document edifactRuleDocument = XmlUtils.getDocumentThrowing(edifactRuleFile);
            return new EDIFACTRule(edifactRuleDocument);
        } catch (final DocumentException e) {
            throw new EDIException("Unable to load rule file: " + edifactRuleFileName, e);
        }
    }

    private String getEDIFACTRuleFileName(
            final EDIFACTLexicalScanner scanner,
            final boolean detectStandardRuleFileName) {
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
            final StringBuilder builder = new StringBuilder("EDIFACT-");

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
        } catch (final EDIException e) {
            return "";
        }
    }

    private void appendNextTokenToBuilder(
            final EDIFACTLexicalScanner scanner,
            final StringBuilder builder,
            final boolean mandatory) throws EDIException {
        final Token messageTypeToken = scanner.nextToken();
        if (StringUtil.isSet(messageTypeToken.getToken())) {
            builder.append(messageTypeToken.getToken());
            builder.append(DELIMITER_RULE_FILE_PART);
        } else {
            if (mandatory) {
                throw new EDIException("Mandatory rule file part not found!");
            }
        }
    }

    public AbstractEDIRule getRule() throws EDIException {
        return getRule(true);
    }

    public AbstractEDIRule getExtendedRule() throws EDIException {
        return getRule(false);
    }

    private AbstractEDIRule getRule(final boolean detectStandardRuleFileName) throws EDIException {
        if (StringUtil.isNotSet(textInputDocument)) {
            throw new EDIException("Unable to auto detect rule file an empty input document!");
        }
        throw new EDIException("Rule file auto detection is not supported for this message!");
    }
}
