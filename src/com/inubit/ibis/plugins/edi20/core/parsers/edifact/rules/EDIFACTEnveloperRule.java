package com.inubit.ibis.plugins.edi20.core.parsers.edifact.rules;

import org.dom4j.Document;
import org.dom4j.Element;

import com.inubit.ibis.plugins.edi20.commons.rules.tokens.IRuleToken;
import com.inubit.ibis.utils.InubitException;

/**
 * @author r4fter
 * 
 */
public class EDIFACTEnveloperRule extends EDIFACTRule {

	@Override
	public String getStandard() {
		return "ENVELOPER";
	}

	/**
	 * @param enveloperDocument
	 * @throws InubitException
	 */
	public EDIFACTEnveloperRule(final Document enveloperDocument) throws InubitException {
		super(enveloperDocument);
	}

	@Override
	protected String getRootElementName() {
		return "Enveloper";
	}

	@Override
	protected boolean isSetCorrectStandardAndLayout(final Element rootElement) {
		return true;
	}

	@Override
	protected void setCurrentRuleToken(IRuleToken currentRuleElement) {
//		System.out.println("EDIFACTEnveloperRule.setCurrentRuleToken(): " + currentRuleElement);
		super.setCurrentRuleToken(currentRuleElement);
	}
}
