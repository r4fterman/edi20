package com.inubit.ibis.plugins.edi20.parsers;

import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;

import com.inubit.ibis.plugins.edi20.parsers.delimiters.EDIFACTDelimiters;
import com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule;
import com.inubit.ibis.plugins.edi20.rules.EDIFACTEnveloperRule;
import com.inubit.ibis.plugins.edi20.rules.EDIFACTRule;
import com.inubit.ibis.plugins.edi20.rules.RuleViolationException;
import com.inubit.ibis.plugins.edi20.rules.interfaces.RuleToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleBaseToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleCompositeElement;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleElement;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegment;
import com.inubit.ibis.plugins.edi20.rules.tokens.hwed.HwedRuleElement;
import com.inubit.ibis.plugins.edi20.scanners.EDIFACTLexicalScanner;
import com.inubit.ibis.plugins.edi20.scanners.EDIFACTUnknownDelimiterToken;
import com.inubit.ibis.plugins.edi20.scanners.Token;
import com.inubit.ibis.utils.EDIException;
import com.inubit.ibis.utils.StringUtil;
import com.inubit.ibis.utils.XmlUtils;

public class EDIFACTParser extends HWEDParser {

    private static final String ENVELOPER_RULE_FILE_NAME = "EDIFACT-ENVELOPER.xml";

    private static final int SEGMENT_OR_SEGMENT_GROUP = 1;
    private static final int ELEMENT_OR_COMPLEX_ELEMENT = 2;

    private int state = SEGMENT_OR_SEGMENT_GROUP;

    private EDIFACTEnveloperRule enveloperRule;
    private EDIFACTRule edifactRule;

    public EDIFACTParser(
            final EDIFACTLexicalScanner scanner,
            final EDIFACTRule rule) throws EDIException {
        super(scanner, rule);
        initializeEnveloperFile();
    }

    private void initializeEnveloperFile() throws EDIException {
        try {
            final Document document = loadEnveloperFile();
            this.enveloperRule = new EDIFACTEnveloperRule(document);
        } catch (final DocumentException e) {
            throw new EDIException("Error parsing enveloperRule rule file!", e);
        }
    }

    @Override
    public boolean canParse(final AbstractEDIRule rule) {
        return rule instanceof EDIFACTRule;
    }

    @Override
    public boolean canParse(final StringBuilder inputDocument) {
        final String detectorToken = inputDocument.substring(0, 3);
        return List.of("UNA", "UNB").contains(detectorToken);
    }

    @Override
    protected boolean isEndOfRule() {
        return getEDIFACTRule().isEndOfRule();
    }

    @Override
    protected void parseToken(final Token messageToken) throws EDIException {
        if (messageToken instanceof EDIFACTUnknownDelimiterToken) {
            parseToken((EDIFACTUnknownDelimiterToken) messageToken);
        } else {
            throw new UnknownDelimiterTokenException(messageToken);
        }
    }

    @Override
    protected void parseDelimiter(final Token token) throws EDIException {
//        logMessage(String.format("EDIFACTParser.parseDelimiter(%d): %s", state, token.getToken()));

        getEDIFACTRule().closeCurrentRuleToken(token);
        if (EDIFACTDelimiters.DELIMITER_SEGMENT == token.getDelimiterType()) {
            // segment finished
            this.state = SEGMENT_OR_SEGMENT_GROUP;
        } else if (EDIFACTDelimiters.DELIMITER_COMPLEX_ELEMENT == token.getDelimiterType()) {
            // complex element finished
            this.state = ELEMENT_OR_COMPLEX_ELEMENT;
        } else if (EDIFACTDelimiters.DELIMITER_ELEMENT == token.getDelimiterType()) {
            // element finished
            this.state = ELEMENT_OR_COMPLEX_ELEMENT;
        } else {
            final String message = String.format("Unknown delimiter [%s] found!", token.getToken());
            throw new RuleViolationException(message);
        }
    }

    private void parseToken(final EDIFACTUnknownDelimiterToken messageToken) throws RuleViolationException {
//        logMessage(String.format("EDIFACTParser.parseToken(%d): %s", state, messageToken.getToken()));

        switch (state) {
            case SEGMENT_OR_SEGMENT_GROUP:
                parseSegment(messageToken.getToken());
                state = ELEMENT_OR_COMPLEX_ELEMENT;
                break;
            case ELEMENT_OR_COMPLEX_ELEMENT:
                parseElementOrComplexElement(messageToken.getToken());
                break;
            default:
                final String message = String.format("Unexpected state [%s]!", state);
                throw new RuleViolationException(message);
        }
    }

    private void parseSegment(final String segmentID) throws RuleViolationException {
        parseSegment(segmentID, getEDIFACTRule(segmentID));
    }

    private void parseSegment(
            final String segmentID,
            final EDIFACTRule rule) throws RuleViolationException {
        final EDIRuleSegment segment = rule.nextSegment(segmentID)
                .orElseThrow(() -> new RuleViolationException(String.format("Segment [%s] not found in rule!", segmentID)));

        setCurrentRule(rule);
        validRuleToken(segmentID, segment);

//        logMessage(String.format("EDIFACTParser.parseSegment(%d): [S: %s (%s)]", state, segmentID, segment));
    }

    private void parseElementOrComplexElement(final String element) throws RuleViolationException {
        parseElementOrComplexElement(element, getEDIFACTRule());
    }

    private void parseElementOrComplexElement(
            final String element,
            final EDIFACTRule rule) throws RuleViolationException {
        final RuleToken nextChild = rule.nextElement();
        if (nextChild != null) {
            // next children found
            if (nextChild instanceof EDIRuleCompositeElement) {
//                logMessage(String.format("EDIFACTParser.parseElementOrComplexElement(%d): [CE: %s]", state, nextChild.getID()));

                parseElementOrComplexElement(element, rule);
                return;
            }

            if (nextChild instanceof EDIRuleElement) {
                final EDIRuleElement ruleElement = (EDIRuleElement) nextChild;
                validRuleToken(element, ruleElement);

//                logMessage(String.format("EDIFACTParser.parseElementOrComplexElement(%d): [E: %s]=[%s]", state, ruleElement.getID(), element));

                return;
            }

            final String message = String.format("Unexpected rule token found [%s]!", nextChild.getClass().getCanonicalName());
            throw new RuleViolationException(message);
        }
        throw new RuleViolationException("No further rule tokens found!");
    }

    private void validRuleToken(
            final String token,
            final EDIRuleBaseToken ruleToken) throws RuleViolationException {
        if (!assertTokenIsSet(token, ruleToken.isMandatory())) {
            final String message = String.format("Invalid token [%s]! Expected mandatory rule token [%s].", token, ruleToken);
            throw new RuleViolationException(message);
        }

        if (ruleToken instanceof HwedRuleElement) {
            final HwedRuleElement elementToken = (HwedRuleElement) ruleToken;
            final int min = elementToken.getMinLength();
            final int max = elementToken.getMaxLength();

            if (!assertTokenHasValidLength(token, min, max)) {
                final String message = String.format("Invalid token [%s] with size %d! Does not match rule token size [%s].", token, token.length(), ruleToken);
                logMessage("WARNING: " + message);
                //throw new RuleViolationException(message);
            }
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

    private EDIFACTRule getEDIFACTRule() {
        if (edifactRule == null) {
            edifactRule = (EDIFACTRule) getRule();
        }
        return edifactRule;
    }

    private EDIFACTRule getEDIFACTRule(final String segmentID) {
        if (isEnveloperSegment(segmentID)) {
//            logMessage("Use enveloper rule for segment " + segmentID);
            return getEnveloperRule();
        }
//        logMessage("Use rule for segment " + segmentID);
        return (EDIFACTRule) getRule();
    }

    private boolean assertTokenIsSet(
            final String token,
            final boolean shouldBeSet) {
        return !shouldBeSet || StringUtil.isSet(token);
    }

    private boolean isEnveloperSegment(final String segmentID) {
        return getEnveloper().containsSegment(segmentID);
    }

    private void setCurrentRule(final EDIFACTRule edifactRule) {
        this.edifactRule = edifactRule;
    }

    private EDIFACTRule getEnveloperRule() {
        return enveloperRule;
    }

    public EDIFACTEnveloperRule getEnveloper() {
        return enveloperRule;
    }

    private Document loadEnveloperFile() throws DocumentException {
        final InputStream stream = EDIFACTParser.class.getResourceAsStream("/" + ENVELOPER_RULE_FILE_NAME);
        return XmlUtils.getDocumentThrowing(stream);
    }

    private void logMessage(final String message) {
        System.out.println(message);
    }

}
