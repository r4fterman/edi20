package com.inubit.ibis.plugins.edi20.parsers;

import com.inubit.ibis.plugins.edi20.parsers.delimiters.EDIFACTDelimiters;
import com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule;
import com.inubit.ibis.plugins.edi20.rules.EDIFACTEnveloperRule;
import com.inubit.ibis.plugins.edi20.rules.EDIFACTRule;
import com.inubit.ibis.plugins.edi20.rules.interfaces.IRuleToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleBaseToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleCompositeElement;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleElement;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegment;
import com.inubit.ibis.plugins.edi20.rules.tokens.hwed.HwedRuleElement;
import com.inubit.ibis.plugins.edi20.scanners.EDIFACTLexicalScanner;
import com.inubit.ibis.plugins.edi20.scanners.IToken;
import com.inubit.ibis.plugins.edi20.utils.EDIUtil;
import com.inubit.ibis.utils.InubitException;
import com.inubit.ibis.utils.StringUtil;
import com.inubit.ibis.utils.XmlUtils;
import org.dom4j.Document;

import java.io.File;

public class EDIFACTParser extends HWEDParser {

    private static final String ENVELOPER_RULE_FILE_NAME = "EDIFACT-ENVELOPER.xml";

    private static final int SEGMENT_OR_SEGMENT_GROUP = 1;
    private static final int ELEMENT_OR_COMPLEX_ELEMENT = 2;

    private int fState = SEGMENT_OR_SEGMENT_GROUP;

    private EDIFACTEnveloperRule enveloperRule;
    private EDIFACTRule currentRule;

    public EDIFACTParser(
            final EDIFACTLexicalScanner scanner,
            final EDIFACTRule rule) throws InubitException {
        super(scanner, rule);
        initializeEnveloperFile();
    }

    private void initializeEnveloperFile() throws InubitException {
        try {
            final File enveloperRuleFile = ensureEnveloperFileExists();
            final Document document = XmlUtils.getDocumentThrowing(enveloperRuleFile);
            enveloperRule = new EDIFACTEnveloperRule(document);
        } catch (final Exception e) {
            throw new InubitException("Error parsing enveloperRule rule file!", e);
        }
    }

    private File ensureEnveloperFileExists() throws InubitException {
        if (!EDIUtil.RULE_FILE_FOLDER.exists()) {
            throw new InubitException("Rule file folder is missing (" + EDIUtil.RULE_FILE_FOLDER + ")!");
        }
        final File enveloperRuleFile = new File(EDIUtil.RULE_FILE_FOLDER, ENVELOPER_RULE_FILE_NAME);
        if (!enveloperRuleFile.exists()) {
            throw new InubitException("Enveloper rule file is missing (" + enveloperRuleFile + ")!");
        }
        return enveloperRuleFile;
    }

    public EDIFACTEnveloperRule getEnveloper() {
        return enveloperRule;
    }

    @Override
    protected boolean isEndOfRule() {
        return getEDIFACTRule().isEndOfRule();
    }

    @Override
    protected void parseDelimiter(final IToken token) throws InubitException {
        // System.out.println("EDIFACTParser.parseDelimiter(" + fState + "): " + token.getToken());
        getEDIFACTRule().closeCurrentRuleToken(token);
        if (EDIFACTDelimiters.DELIMITER_SEGMENT == token.getDelimiterType()) {
            // segment finished
            fState = SEGMENT_OR_SEGMENT_GROUP;
        } else if (EDIFACTDelimiters.DELIMITER_COMPLEX_ELEMENT == token.getDelimiterType()) {
            // complex element finished
            fState = ELEMENT_OR_COMPLEX_ELEMENT;
        } else if (EDIFACTDelimiters.DELIMITER_ELEMENT == token.getDelimiterType()) {
            // element finished
            fState = ELEMENT_OR_COMPLEX_ELEMENT;
        } else {
            throw new InubitException("Unknown delimiter [" + token.getToken() + "] found!");
        }
    }

    @Override
    protected void parseToken(final IToken token) throws InubitException {
        // System.out.println("EDIFACTParser.parseToken(" + fState + "): " + token.getToken());
        switch (fState) {
            case SEGMENT_OR_SEGMENT_GROUP:
                parseSegment(token.getToken());
                fState = ELEMENT_OR_COMPLEX_ELEMENT;
                break;
            case ELEMENT_OR_COMPLEX_ELEMENT:
                parseElementOrComplexElement(token.getToken());
                break;
            default:
                throw new InubitException("unexpected state [" + fState + "]");
        }
    }

    private void parseSegment(final String segmentID) throws InubitException {
        parseSegment(segmentID, getEDIFACTRule(segmentID));
    }

    private void parseSegment(
            final String segmentID,
            final EDIFACTRule rule) throws InubitException {
        final EDIRuleSegment segment = rule.nextSegment(segmentID)
                .orElseThrow(() -> new InubitException("Segment [" + segmentID + "] not found in rule!"));
        setCurrentRule(rule);
        validRuleToken(segmentID, segment);

        System.out.println("EDIFACTParser.parseSegment(" + fState + "): [S:" + segmentID + " (" + segment.getLoop() + ", "
                + segment.getCurrentLoopCount() + ")]");

    }

    private void setCurrentRule(final EDIFACTRule edifactRule) {
        currentRule = edifactRule;
    }

    private void validRuleToken(
            final String token,
            final EDIRuleBaseToken ruleToken) throws InubitException {
        if (!assertTokenIsSet(token, ruleToken.isMandatory())) {
            throw new InubitException("Invalid token [" + token + "]! Expected mandatory rule token [" + ruleToken + "].");
        }
        if (ruleToken instanceof HwedRuleElement) {
            final HwedRuleElement elementToken = (HwedRuleElement) ruleToken;
            final int min = elementToken.getMinLength();
            final int max = elementToken.getMaxLength();
            // TODO: check token length
            //if (!assertTokenHasValidLength(token, min, max)) {
            //    throw new InubitException("Invalid token [" + token + "] with size " + token.length() + "! Does not match rule token size [" + ruleToken + "].");
            //}
        }
    }

    private boolean assertTokenHasValidLength(
            final String token,
            final int min,
            final int max) {
        int length = 0;
        if (StringUtil.isSet(token)) {
            length = token.length();
        }
        return min <= length && max >= length;
    }

    private boolean assertTokenIsSet(
            final String token,
            final boolean shouldBeSet) {
        return !shouldBeSet || StringUtil.isSet(token);
    }

    private void parseElementOrComplexElement(final String element) throws InubitException {
        final EDIFACTRule rule = getEDIFACTRule();
        parseElementOrComplexElement(element, rule);
    }

    private void parseElementOrComplexElement(
            final String element,
            final EDIFACTRule rule) throws InubitException {
        final IRuleToken nextChild = rule.nextElement();
        if (nextChild != null) {
            // next children found
            if (nextChild instanceof EDIRuleCompositeElement) {
                System.out.println("EDIFACTParser.parseElementOrComplexElement(" + fState + "): [CE:" + nextChild.getID() + "]");
                parseElementOrComplexElement(element, rule);
                return;
            }
            if (nextChild instanceof EDIRuleElement) {
                final EDIRuleElement ruleElement = (EDIRuleElement) nextChild;
                validRuleToken(element, ruleElement);
                System.out.println("EDIFACTParser.parseElementOrComplexElement(" + fState + "): [E:" + ruleElement.getID() + "]=[" + element + "]");
                return;
            }
            throw new InubitException("Unexpected rule token found [" + nextChild.getClass().getCanonicalName() + "]!");
        }
        throw new InubitException("No further rule tokens found!");
    }

    private EDIFACTRule getEDIFACTRule() {
        if (currentRule == null) {
            currentRule = (EDIFACTRule) getRule();
        }
        return currentRule;
    }

    private EDIFACTRule getEDIFACTRule(final String segmentID) {
        if (isEnveloperSegment(segmentID)) {
            return getEnveloperRule();
        }
        return (EDIFACTRule) getRule();
    }

    private EDIFACTRule getEnveloperRule() {
        return enveloperRule;
    }

    private boolean isEnveloperSegment(final String segmentID) {
        return getEnveloper().containsSegment(segmentID);
    }

    @Override
    public boolean canParse(final AbstractEDIRule rule) {
        return rule instanceof EDIFACTRule;
    }

    @Override
    public boolean canParse(final StringBuilder inputDocument) {
        final String detectorToken = inputDocument.substring(0, 3);
        if (detectorToken.equals("UNA")) {
            return true;
        }
        return detectorToken.equals("UNB");
    }

}
