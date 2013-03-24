package com.inubit.ibis.plugins.edi20.parsers;

import java.util.List;

import com.inubit.ibis.plugins.edi20.rules.RuleViolationException;
import com.inubit.ibis.plugins.edi20.rules.VDARule;
import com.inubit.ibis.plugins.edi20.rules.interfaces.IElementRuleToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegment;
import com.inubit.ibis.plugins.edi20.rules.tokens.hwfpe.HwfpeRuleElement;
import com.inubit.ibis.plugins.edi20.scanners.*;
import org.apache.commons.lang.StringUtils;

/**
 * @author r4fter
 */
public class VDAParser {

    private final VDALexicalScanner scanner;
    private final VDARule rule;

    /**
     * @param scanner
     *         VDA lexical scanner
     * @param rule
     *         VDA rule
     */
    public VDAParser(final VDALexicalScanner scanner, final VDARule rule) {
        this.scanner = scanner;
        this.rule = rule;
    }

    public void parse() throws Exception {
        while (scanner.hasMoreTokens()) {
            IToken token = scanner.nextToken();
            if (token.isDelimiter()) {
                if (token instanceof VDASegmentDelimiterToken) {
                    // this token is not part of the rule
                } else {
                    throw new UnknownDelimiterTokenException(token);
                }
            } else {
                if (token instanceof VDAUnknownDelimiterToken) {
                    parseToken((VDAUnknownDelimiterToken) token);
                } else {
                    throw new UnknownDelimiterTokenException(token);
                }
            }
        }
    }

    private void parseToken(VDAUnknownDelimiterToken token) throws RuleViolationException {
        EDIRuleSegment ruleToken = getRuleToken(token);
        parseTokenAgainstRuleToken(token, ruleToken);
    }

    private void parseTokenAgainstRuleToken(VDAUnknownDelimiterToken messageToken, EDIRuleSegment ruleToken) throws RuleViolationException {
        // match parts of token with rule token
        List<IElementRuleToken> elements = ruleToken.getElements();
        for (IElementRuleToken element : elements) {
            if (element instanceof HwfpeRuleElement) {
                HwfpeRuleElement ruleElement = (HwfpeRuleElement) element;
                String part = getMessagePart(messageToken, ruleElement);
                validateMessagePartAgainstRuleElement(part, ruleElement);
            } else {
                throw new RuleViolationException("Unsupported rule token found: " + element.getClass().getCanonicalName() + "!");
            }
        }
    }

    private void validateMessagePartAgainstRuleElement(String messagePart, HwfpeRuleElement ruleElement) throws RuleViolationException {
        if (ruleElement.isMandatory()) {
            if (StringUtils.isEmpty(messagePart)) {
                throw new RuleViolationException("Mandatory element [" + ruleElement + "] has not content in message!");
            }
        }
    }

    private String getMessagePart(VDAUnknownDelimiterToken messageToken, HwfpeRuleElement ruleElement) throws RuleViolationException {
        // mandatory part of the token
        int from = ruleElement.getFromPosition() - 1;
        int to = ruleElement.getToPosition();
        String token = messageToken.getToken();
        if (to < token.length()) {
            String part = messageToken.getToken().substring(from, to);
            return part;
        }
        throw new RuleViolationException("Rule token [" + ruleElement + "(from:" + from + ", to:" + to + ")] out of bound (token length:" + token.length() + ")!");
    }

    private EDIRuleSegment getRuleToken(VDAUnknownDelimiterToken token) throws RuleViolationException {
        // get token identifier (e.g. segment identifier)
        VDASegmentIdentifier identifier = token.getIdentifier();
        // get corresponding rule node
        return getCorrespondingRuleToken(identifier);
    }

    private EDIRuleSegment getCorrespondingRuleToken(VDASegmentIdentifier identifier) throws RuleViolationException {
        // walk through rule finding segment by the given identifier
        // check that for rule violation
        List<EDIRuleSegment> segments = rule.getSegments();
        for (EDIRuleSegment segment : segments) {
            if (identifier.getID().equals(segment.getID())) {
                return segment;
            }
        }
        throw new RuleViolationException("Rule contains no segment for [" + identifier + "]!");
    }

}