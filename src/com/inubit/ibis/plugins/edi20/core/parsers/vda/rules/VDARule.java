package com.inubit.ibis.plugins.edi20.core.parsers.vda.rules;

import org.dom4j.Document;

import com.inubit.ibis.plugins.edi20.commons.rules.AbstractEDIRule;
import com.inubit.ibis.utils.InubitException;

/**
 * @author r4fter
 */
public class VDARule extends AbstractEDIRule {

	/**
	 * @param vdaRuleDocument
	 *            VDA rule document
	 * @throws InubitException
	 *             if the given rule document is not a valid VDA rule document
	 */
	public VDARule(final Document vdaRuleDocument) throws InubitException {
		super(vdaRuleDocument);
	}

	@Override
	public String getLayout() {
		return "hwfpe";
	}

	@Override
	public String getStandard() {
		return "VDA";
	}

}
