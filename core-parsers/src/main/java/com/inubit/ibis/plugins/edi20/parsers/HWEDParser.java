package com.inubit.ibis.plugins.edi20.parsers;

import com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule;
import com.inubit.ibis.plugins.edi20.scanners.IScanner;
import com.inubit.ibis.plugins.edi20.scanners.IToken;
import com.inubit.ibis.utils.InubitException;
import com.inubit.ibis.utils.StringUtil;

/**
 * Hierarchical with element delimiter (HWED)
 *
 * @author r4fter
 */
public abstract class HWEDParser extends AbstractEDIParser {

    /**
     * @param scanner
     * @param rule
     */
    public HWEDParser(final IScanner scanner, final AbstractEDIRule rule) {
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
