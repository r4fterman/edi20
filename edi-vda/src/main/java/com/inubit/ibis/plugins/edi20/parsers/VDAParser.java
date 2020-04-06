package com.inubit.ibis.plugins.edi20.parsers;

import com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule;
import com.inubit.ibis.plugins.edi20.rules.RuleViolationException;
import com.inubit.ibis.plugins.edi20.rules.VDARule;
import com.inubit.ibis.plugins.edi20.rules.interfaces.IElementRuleToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegment;
import com.inubit.ibis.plugins.edi20.rules.tokens.hwfpe.HwfpeRuleElement;
import com.inubit.ibis.plugins.edi20.scanners.IIdentifier;
import com.inubit.ibis.plugins.edi20.scanners.IToken;
import com.inubit.ibis.plugins.edi20.scanners.VDALexicalScanner;
import com.inubit.ibis.plugins.edi20.scanners.VDASegmentDelimiterToken;
import com.inubit.ibis.plugins.edi20.scanners.VDAUnknownDelimiterToken;
import com.inubit.ibis.utils.InubitException;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * @author r4fter
 */
public class VDAParser extends HWFPEParser {

    /**
     * @param scanner
     *         VDA lexical scanner
     * @param rule
     *         VDA rule
     */
    public VDAParser(
            final VDALexicalScanner scanner,
            final VDARule rule) {
        super(scanner, rule);
    }

    @Override
    public boolean canParse(final AbstractEDIRule rule) {
        return true;
    }

    @Override
    public boolean canParse(final StringBuilder inputDocument) {
        return true;
    }

    @Override
    protected boolean isEndOfRule() {
        return false;
    }

    @Override
    protected void parseToken(final IToken token) throws InubitException {
        if (token instanceof VDAUnknownDelimiterToken) {
            parseToken((VDAUnknownDelimiterToken) token);
        } else {
            throw new UnknownDelimiterTokenException(token);
        }
    }

    @Override
    protected void parseDelimiter(final IToken token) throws InubitException {
        if (token instanceof VDASegmentDelimiterToken) {
            // this token is not part of the rule
        } else {
            throw new UnknownDelimiterTokenException(token);
        }
    }

    private void parseToken(final VDAUnknownDelimiterToken token) throws RuleViolationException {
        final EDIRuleSegment ruleToken = getRuleToken(token);
        parseTokenAgainstRuleToken(token, ruleToken);
    }

    private void parseTokenAgainstRuleToken(
            final VDAUnknownDelimiterToken messageToken,
            final EDIRuleSegment ruleToken) throws RuleViolationException {
        // match parts of token with rule token
        final List<IElementRuleToken> elements = ruleToken.getElements();
        for (final IElementRuleToken element : elements) {
            if (element instanceof HwfpeRuleElement) {
                final HwfpeRuleElement ruleElement = (HwfpeRuleElement) element;
                final String part = getMessagePart(messageToken, ruleElement);
                validateMessagePartAgainstRuleElement(part, ruleElement);
            } else {
                throw new RuleViolationException("Unsupported rule token found: " + element.getClass().getCanonicalName() + "!");
            }
        }
    }

    private void validateMessagePartAgainstRuleElement(
            final String messagePart,
            final HwfpeRuleElement ruleElement) throws RuleViolationException {
        if (ruleElement.isMandatory()) {
            if (StringUtils.isEmpty(messagePart)) {
                throw new RuleViolationException("Mandatory element [" + ruleElement + "] has not content in message!");
            }
        }
    }

    private String getMessagePart(
            final VDAUnknownDelimiterToken messageToken,
            final HwfpeRuleElement ruleElement) throws RuleViolationException {
        // mandatory part of the token
        final int from = ruleElement.getFromPosition() - 1;
        final int to = ruleElement.getToPosition();
        final String token = messageToken.getToken();
        if (to < token.length()) {
            return messageToken.getToken().substring(from, to);
        }
        throw new RuleViolationException("Rule token [" + ruleElement + "(from:" + from + ", to:" + to + ")] out of bound (token length:" + token.length() + ")!");
    }

    private EDIRuleSegment getRuleToken(final VDAUnknownDelimiterToken token) throws RuleViolationException {
        // get token identifier (e.g. segment identifier)
        final IIdentifier identifier = token.getIdentifier();
        // get corresponding rule node
        return getCorrespondingRuleToken(identifier);
    }

    private EDIRuleSegment getCorrespondingRuleToken(final IIdentifier identifier) throws RuleViolationException {
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

}
