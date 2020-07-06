package com.inubit.ibis.plugins.edi20.parsers;

import com.inubit.ibis.plugins.edi20.parsers.delimiters.DATANORMDelimiters;
import com.inubit.ibis.plugins.edi20.rules.AbstractMSWEDRule;
import com.inubit.ibis.plugins.edi20.rules.RuleViolationException;
import com.inubit.ibis.plugins.edi20.rules.interfaces.ElementRuleToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegment;
import com.inubit.ibis.plugins.edi20.rules.tokens.hwed.HwedRuleElement;
import com.inubit.ibis.plugins.edi20.scanners.DATANORMElementDelimiterToken;
import com.inubit.ibis.plugins.edi20.scanners.DATANORMSegmentDelimiterToken;
import com.inubit.ibis.plugins.edi20.scanners.DATANORMUnknownDelimiterToken;
import com.inubit.ibis.plugins.edi20.scanners.EDILexicalScanner;
import com.inubit.ibis.plugins.edi20.scanners.Identifier;
import com.inubit.ibis.plugins.edi20.scanners.Scanner;
import com.inubit.ibis.plugins.edi20.scanners.Token;
import com.inubit.ibis.utils.EDIException;

import java.util.List;

/**
 * @author r4fter
 */
public class DATANORMParser extends MSWEDParser {

    /**
     * @param scanner
     *         lexical scanner
     * @param rule
     *         EDI rule
     */
    public DATANORMParser(
            final Scanner scanner,
            final AbstractMSWEDRule rule) {
        super(scanner, rule);
    }

    @Override
    protected void parseToken(final Token token) throws EDIException {
        if (token instanceof DATANORMUnknownDelimiterToken) {
            parseToken((DATANORMUnknownDelimiterToken) token);
        }
    }

    private void parseToken(final DATANORMUnknownDelimiterToken token) throws EDIException {
        try {
            final EDIRuleSegment ruleToken = getRuleToken(token);
            parseTokenAgainstRuleToken(token, ruleToken);
        } catch (final RuleViolationException e) {
            throw new EDIException("Parser failed at: " + token, e);
        }
    }

    private EDIRuleSegment getRuleToken(final DATANORMUnknownDelimiterToken token) throws RuleViolationException {
        // get token identifier (e.g. segment identifier)
        final Identifier identifier = token.getIdentifier();
        // get corresponding rule node
        return getCorrespondingRuleToken(identifier);
    }

    private EDIRuleSegment getCorrespondingRuleToken(final Identifier identifier) throws RuleViolationException {
        // walk through rule finding segment by the given identifier
        // check that for rule violation
        final List<EDIRuleSegment> segments = getRule().getSegments();
        for (final EDIRuleSegment segment : segments) {
            if (identifier.getID().equals(segment.getID())) {
                return segment;
            }
        }
        throw new RuleViolationException("Rule contains no segment for [" + identifier + "]!");
    }

    private void parseTokenAgainstRuleToken(
            final DATANORMUnknownDelimiterToken messageToken,
            final EDIRuleSegment ruleToken) throws RuleViolationException {
        // match parts of token with rule token
        final List<ElementRuleToken> elements = ruleToken.getElements();
        for (final ElementRuleToken element : elements) {
            if (element instanceof HwedRuleElement) {
                final HwedRuleElement ruleElement = (HwedRuleElement) element;
                final String part = getMessagePart(messageToken, ruleElement);
                validateMessagePartAgainstRuleElement(part, ruleElement);
            } else {
                throw new RuleViolationException("Unsupported rule token found: " + element.getClass().getCanonicalName() + "!");
            }
        }
    }

    private String getMessagePart(
            final DATANORMUnknownDelimiterToken messageToken,
            final HwedRuleElement ruleElement) throws RuleViolationException {
        // mandatory part of the token
        final String elementDelimiter = ((EDILexicalScanner) getScanner()).getDelimiters().getDelimiter(DATANORMDelimiters.ELEMENT_DELIMITER);
        final String token = messageToken.getToken();
        if (token.contains(elementDelimiter)) {
            return messageToken.getToken().substring(0, token.indexOf(elementDelimiter));
        }
        throw new RuleViolationException("Rule token [" + ruleElement + "] out of bound (token length:" + token.length() + ")!");
    }

    @Override
    protected void parseDelimiter(final Token token) throws EDIException {
        if (!(token instanceof DATANORMSegmentDelimiterToken || token instanceof DATANORMElementDelimiterToken)) {
            throw new UnknownDelimiterTokenException(token);
        }
    }
}
