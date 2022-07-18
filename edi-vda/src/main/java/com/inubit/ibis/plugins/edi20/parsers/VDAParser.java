package com.inubit.ibis.plugins.edi20.parsers;

import com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule;
import com.inubit.ibis.plugins.edi20.rules.RuleViolationException;
import com.inubit.ibis.plugins.edi20.rules.VDARule;
import com.inubit.ibis.plugins.edi20.rules.interfaces.ElementRuleToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegment;
import com.inubit.ibis.plugins.edi20.rules.tokens.RuleElementType;
import com.inubit.ibis.plugins.edi20.rules.tokens.hwfpe.HwfpeRuleElement;
import com.inubit.ibis.plugins.edi20.scanners.Identifier;
import com.inubit.ibis.plugins.edi20.scanners.Token;
import com.inubit.ibis.plugins.edi20.scanners.VDALexicalScanner;
import com.inubit.ibis.plugins.edi20.scanners.VDASegmentDelimiterToken;
import com.inubit.ibis.plugins.edi20.scanners.VDAUnknownDelimiterToken;
import com.inubit.ibis.plugins.edi20.validators.InvalidTypeException;
import com.inubit.ibis.plugins.edi20.validators.TypeValidator;
import com.inubit.ibis.plugins.edi20.validators.TypeValidatorFactory;
import com.inubit.ibis.utils.EDIException;
import com.inubit.ibis.utils.StringUtil;

import java.util.List;

public class VDAParser extends HWFPEParser {

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
    protected void parseToken(final Token messageToken) throws EDIException {
        if (messageToken instanceof VDAUnknownDelimiterToken) {
            parseToken((VDAUnknownDelimiterToken) messageToken);
        } else {
            throw new UnknownDelimiterTokenException(messageToken);
        }
    }

    @Override
    protected void parseDelimiter(final Token messageToken) throws EDIException {
        if (!(messageToken instanceof VDASegmentDelimiterToken)) {
            throw new UnknownDelimiterTokenException(messageToken);
        }
    }

    private void parseToken(final VDAUnknownDelimiterToken messageToken) throws RuleViolationException {
        final EDIRuleSegment ruleToken = getRuleToken(messageToken);
        parseTokenAgainstRuleToken(messageToken, ruleToken);
    }

    private void parseTokenAgainstRuleToken(
            final VDAUnknownDelimiterToken messageToken,
            final EDIRuleSegment ruleToken) throws RuleViolationException {
//        logMessage("[" + messageToken + "]=[" + ruleToken + "]");

        // match parts of token with rule token
        final List<ElementRuleToken> elements = ruleToken.getElements();
        for (final ElementRuleToken element : elements) {
            if (element instanceof HwfpeRuleElement) {
                final HwfpeRuleElement ruleElement = (HwfpeRuleElement) element;
                final String part = getValueFromMessageToken(messageToken, ruleElement);
                validateMessagePartAgainstRuleElement(part, ruleElement);
                addMessagePartToModel(part, ruleElement);
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
//        logMessage("[" + messagePart + "]=[" + ruleElement + "]");

        if (ruleElement.isMandatory()) {
            if (messagePart.isEmpty()) {
                final String message = String.format("Mandatory element [%s] has not content in message!", ruleElement);
                throw new RuleViolationException(message);
            }

            final RuleElementType type = RuleElementType.valueOf(ruleElement.getType());
            if (StringUtil.isWhitespacesOnly(messagePart) && type.equals(RuleElementType.N)) {
                // TODO: how to handle this constellation?
                // A mandatory number element but contains only whitespaces
                final String message = String.format("Mandatory element [%s] contains only whitespaces and no numeric value in message!", ruleElement);
                logMessage("WARNING: " + message);
                //                throw new RuleViolationException(message);
            }

            try {
                final TypeValidator typeValidator = TypeValidatorFactory.getInstance(type);
                typeValidator.validate(messagePart);
            } catch (final InvalidTypeException e) {
                final String message = String.format("Mandatory element [%s] has invalid content [%s] - type must be [%s]!", ruleElement, messagePart, type.getTypeValue());
                throw new RuleViolationException(message);
            }
        }
    }

    private void addMessagePartToModel(
            final String messagePart,
            final HwfpeRuleElement ruleElement) {
        // TODO: implement a suitable model
    }

    private String getValueFromMessageToken(
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

    private void logMessage(final String message) {
//        System.out.println(message);
    }

}
