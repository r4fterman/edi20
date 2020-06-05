package com.inubit.ibis.plugins.edi20.rules.tokens;

import com.inubit.ibis.plugins.edi20.rules.interfaces.RuleToken;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @author r4fter
 */
public abstract class EDIRuleBaseToken implements RuleToken {

    private static final String ATTRIBUTE_NAME_ID = "id";
    private static final String ATTRIBUTE_NAME_NAME = "name";
    private static final String ATTRIBUTE_NAME_REQUIRED = "required";
    private static final String ATTRIBUTE_NAME_XML_TAG = "xmlTag";

    private static final String REQUIRED_MANDATORY = "M";
    private static final String REQUIRED_CONDITIONAL = "C";

    private static final int STATUS_NEW = 1;
    private static final int STATUS_IN_PROGRESS = 2;
    private static final int STATUS_CHECKED = 3;

    private final Element ruleElement;
    private Iterator<Element> childIterator;
    private int status = STATUS_NEW;

    public EDIRuleBaseToken(final Element ruleElement) {
        this.ruleElement = ruleElement;
    }

    public Element getElement() {
        return ruleElement;
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
     * @return <code>true</code> if this rule token is mandatory,
     * <code>false</code> otherwise
     */
    public boolean isMandatory() {
        return getRequired().equals(REQUIRED_MANDATORY);
    }

    /**
     * @return <code>true</code> if this rule token is conditional,
     * <code>false</code> otherwise
     */
    public boolean isConditional() {
        return getRequired().equals(REQUIRED_CONDITIONAL);
    }

    public String getXmlTag() {
        return getRuleElement().attributeValue(ATTRIBUTE_NAME_XML_TAG, "");
    }

    protected Element getRuleElement() {
        return ruleElement;
    }

    /**
     * @return if this rule token has child rule tokens
     */
    public boolean hasChildren() {
        return getRuleElement() != null && !getRuleElement().elements().isEmpty();
    }

    public List<RuleToken> getChildren() {
        final List<Element> childElements = getRuleElement().elements();
        final List<RuleToken> children = new ArrayList<>(childElements.size());
        for (final Element childElement : childElements) {
            children.add(createElementInstance(childElement));
        }
        return children;
    }

    protected RuleToken createElementInstance(final Element element) {
        return getFactory().createInstance(element);
    }

    /**
     * @return next rule child token or <code>null</code> if no such child
     * exists
     */
    public RuleToken nextChildren() {
        final Iterator<Element> childIterator = getChildIterator();
        if (childIterator.hasNext()) {
            return createElementInstance(childIterator.next());
        }
        return null;
    }

    public boolean isChecked() {
        return status == STATUS_CHECKED;
    }

    public void setChecked() {
        status = STATUS_CHECKED;
    }

    public boolean isInProgress() {
        return status == STATUS_IN_PROGRESS;
    }

    public void setInProgress() {
        status = STATUS_IN_PROGRESS;
    }

    private Iterator<Element> getChildIterator() {
        if (childIterator == null) {
            childIterator = getRuleElement().elementIterator();
        }
        return childIterator;
    }

    /**
     * @return parent rule token or <code>null</code> if no parent exists
     */
    public RuleToken getParent() {
        if (getRuleElement().getParent() != null) {
            return getFactory().createInstance(getRuleElement().getParent());
        }
        return null;
    }

    protected abstract RuleTokenFactory getFactory();

    /**
     * @return path to this rule token (in xpath notation)
     */
    public String getRulePath() {
        final StringBuilder pathBuilder = new StringBuilder(getID());
        EDIRuleBaseToken parent = (EDIRuleBaseToken) getParent();
        while (parent != null) {
            pathBuilder.insert(0, "/");
            pathBuilder.insert(0, parent.getID());
            parent = (EDIRuleBaseToken) parent.getParent();
        }
        pathBuilder.insert(0, "/");
        return pathBuilder.toString();
    }

    @Override
    public String toString() {
        return getID() + ", req=" + getRequired() + ", tag=" + getXmlTag() + ", descr=" + getDescription();
    }

    /**
     * @return XPath to this dom4j node
     */
    public String getXPath() {
        final RuleToken parent = getParent();
        if (parent instanceof EDIRuleBaseToken) {
            return ((EDIRuleBaseToken) parent).getXPath() + getPathString();
        }
        return getPathString();
    }

    protected String getPathString() {
        return "/" + getElement().getName() + "[@id='" + getID() + "']";
    }

    /**
     * @param childToken
     *         child token
     * @return index or -1 if the given token is not a child of this token
     */
    public int getIndexOfChild(final EDIRuleBaseToken childToken) {
        final List<RuleToken> children = getChildren();
        for (int i = 0; i < children.size(); i++) {
            final RuleToken child = children.get(i);
            if (child.getID().equals(childToken.getID())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean equals(final Object other) {
        if (other instanceof EDIRuleBaseToken) {
            final EDIRuleBaseToken that = (EDIRuleBaseToken) other;
            return status == that.status
                    && Objects.equals(ruleElement, that.ruleElement)
                    && Objects.equals(childIterator, that.childIterator);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ruleElement, childIterator, status);
    }
}
