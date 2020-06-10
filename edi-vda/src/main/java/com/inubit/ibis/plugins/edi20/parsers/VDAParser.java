package com.inubit.ibis.plugins.edi20.parsers;

import com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule;
import com.inubit.ibis.plugins.edi20.rules.RuleViolationException;
import com.inubit.ibis.plugins.edi20.rules.VDARule;
import com.inubit.ibis.plugins.edi20.rules.interfaces.ElementRuleToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegment;
import com.inubit.ibis.plugins.edi20.rules.tokens.hwfpe.HwfpeRuleElement;
import com.inubit.ibis.plugins.edi20.scanners.Identifier;
import com.inubit.ibis.plugins.edi20.scanners.Token;
import com.inubit.ibis.plugins.edi20.scanners.VDALexicalScanner;
import com.inubit.ibis.plugins.edi20.scanners.VDASegmentDelimiterToken;
import com.inubit.ibis.plugins.edi20.scanners.VDAUnknownDelimiterToken;
import com.inubit.ibis.plugins.edi20.validators.InvalidTypeException;
import com.inubit.ibis.plugins.edi20.validators.TypeValidatorFactory;
import com.inubit.ibis.utils.EDIException;
import com.inubit.ibis.utils.StringUtil;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

public class VDAParser extends HWFPEParser {

    private final Map<String, String> types= Map.of(
            "N", "numeric",
            "AN", "alphanumeric"
    );

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
    protected void parseToken(final Token token) throws EDIException {
        if (token instanceof VDAUnknownDelimiterToken) {
            parseToken((VDAUnknownDelimiterToken) token);
        } else {
            throw new UnknownDelimiterTokenException(token);
        }
    }

    @Override
    protected void parseDelimiter(final Token token) throws EDIException {
        if (!(token instanceof VDASegmentDelimiterToken)) {
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
        System.out.println("[" + messageToken + "]=[" + ruleToken + "]");

        // match parts of token with rule token
        final List<ElementRuleToken> elements = ruleToken.getElements();
        for (final ElementRuleToken element : elements) {
            if (element instanceof HwfpeRuleElement) {
                final HwfpeRuleElement ruleElement = (HwfpeRuleElement) element;
                final String part = getMessagePart(messageToken, ruleElement);
                validateMessagePartAgainstRuleElement(part, ruleElement);
            } else {
                final String message = String.format("Unsupported rule token found: Expected %s but found: %s", HwfpeRuleElement.class.getCanonicalName(), element.getClass().getCanonicalName());
                throw new RuleViolationException(message);
            }
        }
        ruleToken.looped();
    }

    private void validateMessagePartAgainstRuleElement(
            final String messagePart,
            final HwfpeRuleElement ruleElement) throws RuleViolationException {
        System.out.println("[" + messagePart + "]=[" + ruleElement + "]");

        if (ruleElement.isMandatory()) {
            if (StringUtils.isEmpty(messagePart)) {
                final String message = String.format("Mandatory element [%s] has not content in message!", ruleElement);
                throw new RuleViolationException(message);
            }

            final String type = ruleElement.getType();
            if (StringUtil.isWhitespacesOnly(messagePart) && type.equals("N")) {
                // TODO: how to handle this constellation?
                // A mandatory number element but contains only whitespaces
                final String message = String.format("Mandatory element [%s] contains only whitespaces and no numeric value in message!", ruleElement);
                System.out.println("WARNING: " + message);
                //                throw new RuleViolationException(message);
            }

            try {
                isOfType(messagePart, type);
            } catch (final InvalidTypeException e) {
                final String message = String.format("Mandatory element [%s] has invalid content [%s] - type must be [%s]!", ruleElement, messagePart, types.getOrDefault(type, type));
                throw new RuleViolationException(message);
            }
        }
    }

    private void isOfType(
            final String messagePart,
            final String type) throws InvalidTypeException {
        TypeValidatorFactory.getInstance(type).validate(messagePart);
    }

    private String getMessagePart(
            final VDAUnknownDelimiterToken messageToken,
            final HwfpeRuleElement ruleElement) throws RuleViolationException {
        // mandatory part of the token
        final int from = ruleElement.getFromPosition() - 1;
        final int to = ruleElement.getToPosition();
        final String token = messageToken.getToken();
        if (to <= token.length()) {
            return messageToken.getToken().substring(from, to);
        }
        final String message = String.format("Rule token [%s (from: %d, to: %d)] out of bound (token length: %d)!", ruleElement, from, to, token.length());
        throw new RuleViolationException(message);
    }

    private EDIRuleSegment getRuleToken(final VDAUnknownDelimiterToken token) throws RuleViolationException {
        // get token identifier (e.g. segment identifier)
        final Identifier identifier = token.getIdentifier();

        // walk through rule finding segment by the given identifier
        // check for rule violation
        return getRule().nextSegment(identifier.getID())
                .orElseThrow(() -> new RuleViolationException(String.format("Rule contains no segment for [%s]!", identifier)));
    }

}
