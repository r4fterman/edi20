package com.inubit.ibis.plugins.edi20.commons.rules;

import org.dom4j.Document;
import org.dom4j.Element;

import com.inubit.ibis.plugins.edi20.commons.rules.tokens.EDIRuleRoot;
import com.inubit.ibis.plugins.edi20.commons.rules.tokens.EDIRuleTokenFactory;
import com.inubit.ibis.plugins.edi20.commons.rules.tokens.IRuleToken;
import com.inubit.ibis.utils.InubitException;
import com.inubit.ibis.utils.StringUtil;

/**
 * Class handles EDI rule.
 * 
 * @author r4fter
 */
public abstract class AbstractEDIRule {

	private Element fRootElement;
	private IRuleToken fCurrentRuleElement;

	/**
	 * @param ruleDocument
	 *            rule document
	 * @throws InubitException
	 *             if the given rule document is not a valid EDI rule document
	 */
	public AbstractEDIRule(final Document ruleDocument) throws InubitException {
		if (!isValidRuleDocument(ruleDocument)) {
			throw new InubitException("Invalid rule document!");
		}
		fRootElement = ruleDocument.getRootElement();
		setCurrentRuleToken(EDIRuleTokenFactory.getInstance((Element) fRootElement.elements().get(0)));
	}

	protected IRuleToken getCurrentRuleToken() {
		return fCurrentRuleElement;
	}

	protected void setCurrentRuleToken(final IRuleToken currentRuleElement) {
//		System.out.println("AbstractEDIRule.setCurrentRuleToken(): set=" + currentRuleElement);
		fCurrentRuleElement = currentRuleElement;
	}

	private boolean isValidRuleDocument(final Document ruleDocument) {
		if (ruleDocument == null) {
			return false;
		}
		Element rootElement = ruleDocument.getRootElement();
		if (!isSetRootElement(rootElement)) {
			return false;
		}
		if (!isSetCorrectStandardAndLayout(rootElement)) {
			return false;
		}
		return true;
	}

	private boolean isSetRootElement(final Element rootElement) {
		if (rootElement != null) {
			String name = rootElement.getName();
			if (StringUtil.isSet(name)) {
				return name.equals(getRootElementName());
			}
		}
		return false;
	}

	/**
	 * @param rootElement
	 *            rule root element
	 * @return <code>true</code> if standard and layout are set correctly on the given rule root element, <code>false</code> otherwise
	 */
	protected boolean isSetCorrectStandardAndLayout(final Element rootElement) {
		if (rootElement != null) {
			IRuleToken rootToken = EDIRuleTokenFactory.getInstance(rootElement);
			if (rootToken instanceof EDIRuleRoot) {
				EDIRuleRoot root = ((EDIRuleRoot) rootToken);
				return root.getStandard().equals(getStandard()) && root.getLayout().equals(getLayout());
			}
		}
		return false;
	}

	/**
	 * @return root element name
	 */
	protected String getRootElementName() {
		return "Message";
	}

	/**
	 * @return root element
	 */
	protected Element getRootElement() {
		return fRootElement;
	}

	/**
	 * @return agency
	 */
	public String getAgency() {
		IRuleToken rootToken = EDIRuleTokenFactory.getInstance(getRootElement());
		if (rootToken instanceof EDIRuleRoot) {
			return ((EDIRuleRoot) rootToken).getAgency();
		}
		return "";
	}

	/**
	 * @return rule description
	 */
	public String getDescription() {
		IRuleToken rootToken = EDIRuleTokenFactory.getInstance(getRootElement());
		if (rootToken instanceof EDIRuleRoot) {
			return ((EDIRuleRoot) rootToken).getDescription();
		}
		return "";
	}

	/**
	 * @return layout, e.g. HWED
	 */
	public abstract String getLayout();

	/**
	 * @return release, e.g. 96A
	 */
	public String getRelease() {
		IRuleToken rootToken = EDIRuleTokenFactory.getInstance(getRootElement());
		if (rootToken instanceof EDIRuleRoot) {
			return ((EDIRuleRoot) rootToken).getRelease();
		}
		return "";
	}

	public abstract String getStandard();

	/**
	 * @return rule type, e.g. IFCSUM
	 */
	public String getType() {
		IRuleToken rootToken = EDIRuleTokenFactory.getInstance(getRootElement());
		if (rootToken instanceof EDIRuleRoot) {
			return ((EDIRuleRoot) rootToken).getType();
		}
		return "";
	}

	/**
	 * @return rule version, e.g. D
	 */
	public String getVersion() {
		IRuleToken rootToken = EDIRuleTokenFactory.getInstance(getRootElement());
		if (rootToken instanceof EDIRuleRoot) {
			return ((EDIRuleRoot) rootToken).getVersion();
		}
		return "";
	}

	@Override
	public String toString() {
		return getStandard() + "-" + getType() + "-" + getVersion() + "-" + getRelease() + "-" + getAgency();
	}

}
