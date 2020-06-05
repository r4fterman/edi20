package com.inubit.ibis.plugins.edi20.parsers;

import com.inubit.ibis.plugins.edi20.rules.AbstractHWEDRule;
import com.inubit.ibis.plugins.edi20.rules.RuleViolationException;
import com.inubit.ibis.plugins.edi20.rules.tokens.hwed.HwedRuleElement;
import com.inubit.ibis.plugins.edi20.scanners.Scanner;
import com.inubit.ibis.plugins.edi20.scanners.Token;
import com.inubit.ibis.utils.InubitException;
import com.inubit.ibis.utils.StringUtil;
import org.apache.commons.lang.StringUtils;

/**
 * Hierarchical with element delimiter (HWED).
 */
public abstract class HWEDParser extends AbstractEDIParser {

    public HWEDParser(
            final Scanner scanner,
            final AbstractHWEDRule rule) {
        super(scanner, rule);
    }

    @Override
    public void parse() throws InubitException {
        if (getScanner().hasMoreTokens()) {
            parseTokens();
        }
    }

    private void parseTokens() throws InubitException {
        while (getScanner().hasMoreTokens() && !isEndOfRule()) {
            final Token token = getScanner().nextToken();
            if (!StringUtil.isLineBreakOnly(token.getToken())) {
                if (token.isDelimiter()) {
                    parseDelimiter(token);
                } else {
                    parseToken(token);
                }
            }
        }

        if (isEndOfRule() && getScanner().hasMoreTokens()) {
            // empty the scanner
            final StringBuilder unparsedBuilder = new StringBuilder();
            while (getScanner().hasMoreTokens()) {
                unparsedBuilder.append(getScanner().nextToken().getToken());
            }
            final String unparsedPart = unparsedBuilder.toString();
            if (StringUtil.isWhitespacesOnly(unparsedPart)) {
                System.out.println("WARNING: Rule parsing complete message still contains data (but only whitespaces which is ignored).");
            } else {
                throw new InubitException("Rule parsing complete but message still contains data [" + unparsedPart + "]!");
            }
        }
    }

    protected void validateMessagePartAgainstRuleElement(
            final String messagePart,
            final HwedRuleElement ruleElement) throws RuleViolationException {
        if (ruleElement.isMandatory()) {
            if (StringUtils.isEmpty(messagePart)) {
                throw new RuleViolationException("Mandatory element [" + ruleElement + "] has not content in message!");
            }
        }

        if (StringUtils.isNotBlank(messagePart)) {
            final int length = messagePart.length();
            validateMinLength(ruleElement, length);
            validateMaxLength(ruleElement, length);
        }
    }

    private void validateMaxLength(
            final HwedRuleElement ruleElement,
            final int length) throws RuleViolationException {
        final int max = ruleElement.getMaxLength();
        if (length > max) {
            throw new RuleViolationException("Element [" + ruleElement + "] is longer than declared (length:" + length + ",max:" + max + "!");
        }
    }

    private void validateMinLength(
            final HwedRuleElement ruleElement,
            final int length) throws RuleViolationException {
        final int min = ruleElement.getMinLength();
        if (length < min) {
            throw new RuleViolationException("Element [" + ruleElement + "] is shorter than declared (length:" + length + ",min:" + min + "!");
        }
    }

    @Override
    protected abstract boolean isEndOfRule();

    @Override
    protected abstract void parseToken(Token token) throws InubitException;

    @Override
    protected abstract void parseDelimiter(Token delimiterToken) throws InubitException;

}
