package com.inubit.ibis.plugins.edi20.core.parsers.edifact.rules;

import org.dom4j.Document;

import com.inubit.ibis.utils.InubitException;

/**
 * @author r4fter
 */
public class EDIGASRule extends EDIFACTRule {

	public EDIGASRule(final Document edigasRuleDocument) throws InubitException {
		super(edigasRuleDocument);
	}
	
	@Override
	public String getStandard() {
		return "EDIGAS";
	}

}
