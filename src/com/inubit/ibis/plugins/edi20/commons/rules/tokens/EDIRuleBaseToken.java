package com.inubit.ibis.plugins.edi20.commons.rules.tokens;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.dom4j.Element;

import com.inubit.ibis.utils.StringUtils;

/**
 * @author r4fter
 * 
 */
public class EDIRuleBaseToken implements IRuleToken {

	private static final String ATTRIBUTE_NAME_ID = "id";
	private static final String ATTRIBUTE_NAME_NAME = "name";
	private static final String ATTRIBUTE_NAME_REQUIRED = "required";
	private static final String ATTRIBUTE_NAME_XMLTAG = "xmlTag";

	private static final String REQUIRED_MANDATORY = "M";
	private static final String REQUIRED_CONDITIONAL = "C";

	private static final int STATUS_NEW = 1;
	private static final int STATUS_INPROGRESS = 2;
	private static final int STATUS_CHECKED = 3;

	private Element fRuleElement;
	private Iterator<Element> fChildIterator;
	private int fStatus = STATUS_NEW;

	public EDIRuleBaseToken(final Element ruleElement) {
		fRuleElement = ruleElement;
	}

	public Element getElement() {
		return fRuleElement;
	}

	@Override
	public String getID() {
		return getRuleElement().attributeValue(ATTRIBUTE_NAME_ID, "");
	}

	/**
	 * @return description
	 */
	public String getDescription() {
		return getRuleElement().attributeValue(ATTRIBUTE_NAME_NAME, "");
	}

	/**
	 * @return required
	 */
	private String getRequired() {
		return getRuleElement().attributeValue(ATTRIBUTE_NAME_REQUIRED, "");
	}

	/**
	 * @return <code>true</code> if this rule token is mandatory, <code>false</code> otherwise
	 */
	public boolean isMandatory() {
		return getRequired().equals(REQUIRED_MANDATORY);
	}

	/**
	 * @return <code>true</code> if this rule token is conditional, <code>false</code> otherwise
	 */
	public boolean isConditional() {
		return getRequired().equals(REQUIRED_CONDITIONAL);
	}

	/**
	 * @return XML tag
	 */
	public String getXmlTag() {
		return getRuleElement().attributeValue(ATTRIBUTE_NAME_XMLTAG, "");
	}

	protected Element getRuleElement() {
		return fRuleElement;
	}

	/**
	 * @return if this rule token has child rule tokens
	 */
	public boolean hasChildren() {
		if (getRuleElement() != null) {
			return getRuleElement().elements().size() > 0;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<IRuleToken> getChildrens() {
		List<Element> childElements = getRuleElement().elements();
		List<IRuleToken> children = new Vector<IRuleToken>(childElements.size());
		for (Element childElement : childElements) {
			children.add(EDIRuleTokenFactory.getInstance(childElement));
		}
		return children;
	}

	/**
	 * @return next rule child token or <code>null</code> if no such child exists
	 */
	public IRuleToken nextChildren() {
		if (getChildIterator().hasNext()) {
			return EDIRuleTokenFactory.getInstance(getChildIterator().next());
		}
		return null;
	}

	public void resetChildIterator() {
		fChildIterator = null;
		fStatus = STATUS_NEW;
	}

	public boolean isChecked() {
		System.out.println("EDIRuleBaseToken.isChecked(): status=" + fStatus);
		return fStatus == STATUS_CHECKED;
	}

	public void setChecked() {
		fStatus = STATUS_CHECKED;
	}

	public boolean isInProgress() {
		return fStatus == STATUS_INPROGRESS;
	}

	public void setInProgress() {
		fStatus = STATUS_INPROGRESS;
	}

	@SuppressWarnings("unchecked")
	private Iterator<Element> getChildIterator() {
		if (fChildIterator == null) {
			fChildIterator = getRuleElement().elementIterator();
		}
		return fChildIterator;
	}

	/**
	 * @return parent rule token or <code>null</code> if no parent exists
	 */
	public IRuleToken getParent() {
		if (getRuleElement().getParent() != null) {
			return EDIRuleTokenFactory.getInstance(getRuleElement().getParent());
		}
		return null;
	}

	/**
	 * @return XPath to this rule token
	 */
	public String getRulePath() {
		StringBuilder pathBuilder = new StringBuilder(getID());
		EDIRuleBaseToken parent = (EDIRuleBaseToken) getParent();
		while (parent != null) {
			pathBuilder.insert(0, "/");
			pathBuilder.insert(0, parent.getID());
			parent = (EDIRuleBaseToken) parent.getParent();
		}
		return pathBuilder.toString();
	}

	@Override
	public String toString() {
		return getID() + ", req=" + getRequired() + ", tag=" + getXmlTag() + ", descr=" + getDescription();
	}

	public String getPath() {
		IRuleToken parent = getParent();
		if (parent instanceof EDIRuleBaseToken) {
			return ((EDIRuleBaseToken) parent).getPath() + "/" + getID();
		}
		return getID();
	}

	/**
	 * @param childToken
	 *            child token
	 * @return index or -1 if the given token is not a child of this token
	 */
	public int getIndexOfChild(final EDIRuleBaseToken childToken) {
		List<IRuleToken> children = getChildrens();
		for (int i = 0; i < children.size(); i++) {
			IRuleToken child = children.get(i);
			if (child.getID().equals(childToken.getID())) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fChildIterator == null) ? 0 : fChildIterator.hashCode());
		result = prime * result + ((fRuleElement == null) ? 0 : fRuleElement.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof EDIRuleBaseToken)) {
			return false;
		}
		EDIRuleBaseToken other = (EDIRuleBaseToken) obj;
		if (fChildIterator == null) {
			if (other.fChildIterator != null) {
				return false;
			}
		} else if (!fChildIterator.equals(other.fChildIterator)) {
			return false;
		}
		if (fRuleElement == null) {
			if (other.fRuleElement != null) {
				return false;
			}
		} else if (!fRuleElement.equals(other.fRuleElement)) {
			return false;
		}
		if (StringUtils.isSet(getID())) {
			if (StringUtils.isNotSet(other.getID())) {
				return false;
			}
		} else if (!getID().equals(other.getID())) {
			return false;
		}
		return true;
	}
}
