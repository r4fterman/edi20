package com.inubit.ibis.plugins.edi20.rules;

import java.util.ArrayList;
import java.util.List;

import com.inubit.ibis.plugins.edi20.rules.interfaces.IRuleToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleRoot;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegment;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleTokenFactory;
import com.inubit.ibis.utils.InubitException;
import com.inubit.ibis.utils.StringUtil;
import org.dom4j.Document;
import org.dom4j.Element;

/**
 * Class handles EDI rule.
 *
 * @author r4fter
 */
public abstract class AbstractEDIRule {

    private EDIRuleRoot fRootElement;

    /**
     * @param ruleDocument
     *         rule document
     * @throws InubitException
     *         if the given rule document is not a valid EDI rule document
     */
    public AbstractEDIRule(final Document ruleDocument) throws InubitException {
        if (!isValidRuleDocument(ruleDocument)) {
            throw new InubitException("Invalid rule document!");
        }
        fRootElement = (EDIRuleRoot) EDIRuleTokenFactory.getInstance(ruleDocument.getRootElement());
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
                return name.equals(rootElement.getName());
            }
        }
        return false;
    }

    /**
     * @param rootElement
     * @return <code>true</code> if standard and layout are set correctly on the rule root element, <code>false</code>
     *         otherwise
     */
    private boolean isSetCorrectStandardAndLayout(Element rootElement) {
        if (rootElement != null) {
            IRuleToken ruleToken = EDIRuleTokenFactory.getInstance(rootElement);
            if (ruleToken instanceof EDIRuleRoot) {
                EDIRuleRoot root = (EDIRuleRoot) ruleToken;
                return root.getStandard().equals(getStandard()) && root.getLayout().equals(getLayout());
            }
        }
        return false;
    }

    /**
     * @return root element name
     */
    protected String getRootElementName() {
        return getRootElement().getID();
    }

    /**
     * @return root element
     */
    protected EDIRuleRoot getRootElement() {
        return fRootElement;
    }

    /**
     * @return agency
     */
    public String getAgency() {
        IRuleToken rootToken = getRootElement();
        if (rootToken instanceof EDIRuleRoot) {
            return ((EDIRuleRoot) rootToken).getAgency();
        }
        return "";
    }

    /**
     * @return rule description
     */
    public String getDescription() {
        IRuleToken rootToken = getRootElement();
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
        IRuleToken rootToken = getRootElement();
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
        IRuleToken rootToken = getRootElement();
        if (rootToken instanceof EDIRuleRoot) {
            return ((EDIRuleRoot) rootToken).getType();
        }
        return "";
    }

    /**
     * @return rule version, e.g. D
     */
    public String getVersion() {
        IRuleToken rootToken = getRootElement();
        if (rootToken instanceof EDIRuleRoot) {
            return ((EDIRuleRoot) rootToken).getVersion();
        }
        return "";
    }

    @Override
    public String toString() {
        return getStandard() + "-" + getType() + "-" + getVersion() + "-" + getRelease() + "-" + getAgency();
    }

    public List<EDIRuleSegment> getSegments() {
        List<EDIRuleSegment> segments = new ArrayList<EDIRuleSegment>();

        EDIRuleRoot root = getRootElement();
        for (IRuleToken child : root.getChildrens()) {
            if (child instanceof EDIRuleSegment) {
                segments.add((EDIRuleSegment) child);
            }
        }
        return segments;
    }
}
