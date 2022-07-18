package com.inubit.ibis.plugins.edi20.parsers;

import com.inubit.ibis.plugins.edi20.rules.AbstractHWEDRule;
import com.inubit.ibis.plugins.edi20.rules.RuleViolationException;
import com.inubit.ibis.plugins.edi20.rules.tokens.hwed.HwedRuleElement;
import com.inubit.ibis.plugins.edi20.scanners.Scanner;

/**
 * Hierarchical with element delimiter (HWED).
 */
public abstract class HWEDParser extends AbstractEDIParser {

    protected HWEDParser(
            final Scanner scanner,
            final AbstractHWEDRule rule) {
        super(scanner, rule);
    }

    //    @Override
    //    public void parse() throws EDIException {
    //        if (getScanner().hasMoreTokens()) {
    //            parseTokens();
    //        }
    //    }
    //
    //    private void parseTokens() throws EDIException {
    //        while (getScanner().hasMoreTokens() && !isEndOfRule()) {
    //            final Token token = getScanner().nextToken();
    //            if (!StringUtil.isLineBreakOnly(token.getToken())) {
    //                if (token.isDelimiter()) {
    //                    parseDelimiter(token);
    //                } else {
    //                    parseToken(token);
    //                }
    //            }
    //        }
    //
    //        if (isEndOfRule() && getScanner().hasMoreTokens()) {
    //            // empty the scanner
    //            final StringBuilder unparsedBuilder = new StringBuilder();
    //            while (getScanner().hasMoreTokens()) {
    //                unparsedBuilder.append(getScanner().nextToken().getToken());
    //            }
    //            final String unparsedPart = unparsedBuilder.toString();
    //            if (StringUtil.isWhitespacesOnly(unparsedPart)) {
    //                System.out.println("WARNING: Rule parsing complete message still contains data (but only whitespaces which is ignored).");
    //            } else {
    //                throw new EDIException("Rule parsing complete but message still contains data [" + unparsedPart + "]!");
    //            }
    //        }
    //    }

    protected void validateMessagePartAgainstRuleElement(
            final String messagePart,
            final HwedRuleElement ruleElement) throws RuleViolationException {

        String message = String.format("[%s]=[%s]%n", messagePart, ruleElement.getID());
        logMessage(message);

        if (ruleElement.isMandatory()
                && messagePart.isEmpty()) {
            message = String.format("Mandatory element [%s] has not content in message!", ruleElement);
            throw new RuleViolationException(message);
        }

        if (!messagePart.isBlank()) {
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
            final String message =
                    String.format("Element [%s] is longer than declared (length: %d, max: %d)!",
                            ruleElement,
                            length,
                            max);
            throw new RuleViolationException(message);
        }
    }

    private void validateMinLength(
            final HwedRuleElement ruleElement,
            final int length) throws RuleViolationException {
        final int min = ruleElement.getMinLength();
        if (length < min) {
            throw new RuleViolationException(
                    "Element [" + ruleElement + "] is shorter than declared (length:" + length + ",min:" + min + "!");
        }
    }

    private void logMessage(final String message) {
//        System.out.println(message);
    }

}
