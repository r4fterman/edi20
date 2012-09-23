package com.inubit.ibis.plugins.edi20.core.parsers.bemis;

import org.dom4j.Document;

import com.inubit.ibis.plugins.edi20.commons.rules.AbstractHWEDRule;
import com.inubit.ibis.plugins.edi20.commons.scanners.IToken;
import com.inubit.ibis.utils.InubitException;

/**
 * @author r4fter
 */
public class BEMISRule extends AbstractHWEDRule {

	/**
	 * @param bemisRuleDocument
	 *            BEMIS rule document
	 * @throws InubitException
	 *             if the given rule document is not a valid BEMIS rule document
	 */
	public BEMISRule(final Document bemisRuleDocument) throws InubitException {
		super(bemisRuleDocument);
	}

	@Override
	public String getStandard() {
		return "BEMIS";
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
