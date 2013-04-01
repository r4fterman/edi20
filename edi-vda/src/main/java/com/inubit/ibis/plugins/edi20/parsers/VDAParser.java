package com.inubit.ibis.plugins.edi20.parsers;

import java.util.List;

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
    public VDAParser(final VDALexicalScanner scanner, final VDARule rule) {
        super(scanner, rule);
    }

    @Override
    public boolean canParse(AbstractEDIRule rule) {
        return true;
    }

    @Override
    public boolean canParse(StringBuilder inputDocument) {
        return true;
    }

    @Override
    protected boolean isEndOfRule() {
        return false;
    }

    @Override
    protected void parseToken(IToken token) throws InubitException {
        if (token instanceof VDAUnknownDelimiterToken) {
            parseToken((VDAUnknownDelimiterToken) token);
        } else {
            throw new UnknownDelimiterTokenException(token);
        }
    }

    @Override
    protected void parseDelimiter(IToken token) throws InubitException {
        if (token instanceof VDASegmentDelimiterToken) {
            // this token is not part of the rule
        } else {
            throw new UnknownDelimiterTokenException(token);
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
            return messageToken.getToken().substring(from, to);
        }
        throw new RuleViolationException("Rule token [" + ruleElement + "(from:" + from + ", to:" + to + ")] out of bound (token length:" + token.length() + ")!");
    }

    private EDIRuleSegment getRuleToken(VDAUnknownDelimiterToken token) throws RuleViolationException {
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

}