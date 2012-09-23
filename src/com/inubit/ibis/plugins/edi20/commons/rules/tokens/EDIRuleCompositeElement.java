package com.inubit.ibis.plugins.edi20.commons.rules.tokens;

import org.dom4j.Element;

/**
 * @author r4fter
 * 
 */
public class EDIRuleCompositeElement extends EDIRuleBaseToken {

	/**
	 * @param ruleElement
	 */
	public EDIRuleCompositeElement(final Element ruleElement) {
		super(ruleElement);
	}

	@Override
	public String toString() {
		// <CompositeElement id="S009" name="Message Identifier" required="M" xmlTag="MessageIdentifierCE">
		return "(Composite) " + super.toString();
	}
}
