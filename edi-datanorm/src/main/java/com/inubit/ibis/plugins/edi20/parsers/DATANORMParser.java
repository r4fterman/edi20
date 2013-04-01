package com.inubit.ibis.plugins.edi20.parsers;

import java.util.List;

import com.inubit.ibis.plugins.edi20.parsers.delimiters.DATANORMDelimiters;
import com.inubit.ibis.plugins.edi20.rules.AbstractMSWEDRule;
import com.inubit.ibis.plugins.edi20.rules.RuleViolationException;
import com.inubit.ibis.plugins.edi20.rules.interfaces.IElementRuleToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegment;
import com.inubit.ibis.plugins.edi20.rules.tokens.hwed.HwedRuleElement;
import com.inubit.ibis.plugins.edi20.scanners.DATANORMElementDelimiterToken;
import com.inubit.ibis.plugins.edi20.scanners.DATANORMSegmentDelimiterToken;
import com.inubit.ibis.plugins.edi20.scanners.DATANORMUnknownDelimiterToken;
import com.inubit.ibis.plugins.edi20.scanners.EDILexicalScanner;
import com.inubit.ibis.plugins.edi20.scanners.IIdentifier;
import com.inubit.ibis.plugins.edi20.scanners.IScanner;
import com.inubit.ibis.plugins.edi20.scanners.IToken;
import com.inubit.ibis.utils.InubitException;

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
    public DATANORMParser(IScanner scanner, AbstractMSWEDRule rule) {
        super(scanner, rule);
    }

    @Override
    protected void parseToken(IToken token) throws InubitException {
        if (token instanceof DATANORMUnknownDelimiterToken) {
            parseToken((DATANORMUnknownDelimiterToken) token);
        }
    }

    private void parseToken(DATANORMUnknownDelimiterToken token) throws InubitException {
        try {
            EDIRuleSegment ruleToken = getRuleToken(token);
            parseTokenAgainstRuleToken(token, ruleToken);
        } catch (RuleViolationException e) {
            throw new InubitException("Parser failed at: " + token, e);
        }
    }

    private EDIRuleSegment getRuleToken(DATANORMUnknownDelimiterToken token) throws RuleViolationException {
        // get token identifier (e.g. segment identifier)
        IIdentifier identifier = token.getIdentifier();
        // get corresponding rule node
        return getCorrespondingRuleToken(identifier);
    }

    private EDIRuleSegment getCorrespondingRuleToken(IIdentifier identifier) throws RuleViolationException {
        // walk through rule finding segment by the given identifier
        // check that for rule violation
        List<EDIRuleSegment> segments = getRule().getSegments();
        for (EDIRuleSegment segment : segments) {
            if (identifier.getID().equals(segment.getID())) {
                return segment;
            }
        }
        throw new RuleViolationException("Rule contains no segment for [" + identifier + "]!");
    }

    private void parseTokenAgainstRuleToken(DATANORMUnknownDelimiterToken messageToken, EDIRuleSegment ruleToken) throws RuleViolationException {
        // match parts of token with rule token
        List<IElementRuleToken> elements = ruleToken.getElements();
        for (IElementRuleToken element : elements) {
            if (element instanceof HwedRuleElement) {
                HwedRuleElement ruleElement = (HwedRuleElement) element;
                String part = getMessagePart(messageToken, ruleElement);
                validateMessagePartAgainstRuleElement(part, ruleElement);
            } else {
                throw new RuleViolationException("Unsupported rule token found: " + element.getClass().getCanonicalName() + "!");
            }
        }
    }

    private String getMessagePart(DATANORMUnknownDelimiterToken messageToken, HwedRuleElement ruleElement) throws RuleViolationException {
        // mandatory part of the token
        String elementDelimiter = ((EDILexicalScanner) getScanner()).getDelimiters().getDelimiter(DATANORMDelimiters.ELEMENT_DELIMITER);
        String token = messageToken.getToken();
        if (token.contains(elementDelimiter)) {
            return messageToken.getToken().substring(0, token.indexOf(elementDelimiter));
        }
        throw new RuleViolationException("Rule token [" + ruleElement + "] out of bound (token length:" + token.length() + ")!");
    }

    @Override
    protected void parseDelimiter(IToken token) throws InubitException {
        if (token instanceof DATANORMSegmentDelimiterToken) {
            // this token is not part of the rule
        } else if (token instanceof DATANORMElementDelimiterToken) {
            // this token is not part of the rule
        } else {
            throw new UnknownDelimiterTokenException(token);
        }
    }
}
