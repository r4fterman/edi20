package com.inubit.ibis.plugins.edi20.core.parsers.x12;

import org.dom4j.Document;

import com.inubit.ibis.plugins.edi20.commons.rules.AbstractHWEDRule;
import com.inubit.ibis.plugins.edi20.commons.scanners.IToken;
import com.inubit.ibis.utils.InubitException;

/**
 * @author r4fter
 */
public class X12Rule extends AbstractHWEDRule {

	/**
	 * @param x12RuleDocument
	 *            X12 rule document
	 * @throws InubitException
	 *             if the given rule document is not a valid X12 rule document
	 */
	public X12Rule(Document x12RuleDocument) throws InubitException {
		super(x12RuleDocument);
	}

	@Override
	public String getStandard() {
		return "X12";
	}

	@Override
	public boolean isEndOfRule() {
		return false;
	}

	@Override
	public void closeCurrentRuleToken(IToken token) {
		// TODO: implement
	}
}
