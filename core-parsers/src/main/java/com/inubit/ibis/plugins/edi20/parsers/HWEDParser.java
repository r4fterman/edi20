package com.inubit.ibis.plugins.edi20.parsers;

import com.inubit.ibis.plugins.edi20.rules.AbstractHWEDRule;
import com.inubit.ibis.plugins.edi20.rules.RuleViolationException;
import com.inubit.ibis.plugins.edi20.rules.tokens.hwed.HwedRuleElement;
import com.inubit.ibis.plugins.edi20.scanners.IScanner;
import com.inubit.ibis.plugins.edi20.scanners.IToken;
import com.inubit.ibis.utils.InubitException;
import com.inubit.ibis.utils.StringUtil;
import org.apache.commons.lang.StringUtils;

/**
 * Hierarchical with element delimiter (HWED)
 *
 * @author r4fter
 */
public abstract class HWEDParser extends AbstractEDIParser {

    /**
     * @param scanner
     *         hwed scanner
     * @param rule
     *         hwed rule
     */
    public HWEDParser(final IScanner scanner, final AbstractHWEDRule rule) {
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
            IToken token = getScanner().nextToken();
            if (token.isDelimiter()) {
                parseDelimiter(token);
            } else {
                parseToken(token);
            }
        }

        if (isEndOfRule() && getScanner().hasMoreTokens()) {
            // empty the scanner
            StringBuilder unparsedBuilder = new StringBuilder("");
            while (getScanner().hasMoreTokens()) {
                unparsedBuilder.append(getScanner().nextToken().getToken());
            }
            String unparsedPart = unparsedBuilder.toString();
            // ignore white spaces at the end of message
            if (!StringUtil.isWhitespacesOnly(unparsedPart)) {
                throw new InubitException("Rule parsing complete but message still contains data [" + unparsedPart + "]!");
            }
        }
    }

    protected void validateMessagePartAgainstRuleElement(String messagePart, HwedRuleElement ruleElement) throws RuleViolationException {
        if (ruleElement.isMandatory()) {
            if (StringUtils.isEmpty(messagePart)) {
                throw new RuleViolationException("Mandatory element [" + ruleElement + "] has not content in message!");
            }
        }

        if (StringUtils.isNotBlank(messagePart)) {
            int length = messagePart.length();
            validateMinLength(ruleElement, length);
            validateMaxLength(ruleElement, length);
        }
    }

    private void validateMaxLength(HwedRuleElement ruleElement, int length) throws RuleViolationException {
        int max = ruleElement.getMaxLength();
        if (length > max) {
            throw new RuleViolationException("Element [" + ruleElement + "] is longer than declared (length:" + length + ",max:" + max + "!");
        }
    }

    private void validateMinLength(HwedRuleElement ruleElement, int length) throws RuleViolationException {
        int min = ruleElement.getMinLength();
        if (length < min) {
            throw new RuleViolationException("Element [" + ruleElement + "] is shorter than declared (length:" + length + ",min:" + min + "!");
        }
    }

    /**
     * @return <code>true</code> if end of rule is reached
     */
    protected abstract boolean isEndOfRule();

    /**
     * Method parses the given token.
     *
     * @param token
     *         token to parse
     * @throws InubitException
     *         if parsing token failed
     */
    protected abstract void parseToken(IToken token) throws InubitException;

    /**
     * Method parses the given delimiter token.
     *
     * @param delimiterToken
     *         delimiter token to parse
     * @throws InubitException
     *         if parsing delimiter token failed
     */
    protected abstract void parseDelimiter(IToken delimiterToken) throws InubitException;

}
