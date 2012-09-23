package com.inubit.ibis.plugins.edi20.commons.parsers;

import com.inubit.ibis.plugins.edi20.commons.rules.AbstractEDIRule;
import com.inubit.ibis.plugins.edi20.commons.scanners.IScanner;
import com.inubit.ibis.plugins.edi20.commons.scanners.IToken;
import com.inubit.ibis.utils.InubitException;
import com.inubit.ibis.utils.StringUtils;

/**
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
			if (!StringUtils.isWhitespacesOnly(unparsedPart)) {
				throw new InubitException("Rule parsing complete but message still contains data [" + unparsedPart + "]!");
			}
		}
	}

	protected abstract boolean isEndOfRule();

	protected abstract void parseToken(IToken token) throws InubitException;

	protected abstract void parseDelimiter(IToken token) throws InubitException;

}
