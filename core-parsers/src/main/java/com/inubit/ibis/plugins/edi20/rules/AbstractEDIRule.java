package com.inubit.ibis.plugins.edi20.rules;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.dom4j.Document;
import org.dom4j.Element;

import com.inubit.ibis.plugins.edi20.rules.interfaces.RuleToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleBaseToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleRoot;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegment;
import com.inubit.ibis.utils.EDIException;
import com.inubit.ibis.utils.StringUtil;

public abstract class AbstractEDIRule {

    private final EDIRuleRoot ruleElement;
    private final RuleTreeTraverser ruleTreeTraverser;

    private RuleToken currentRuleToken;

    /**
     * @param ruleDocument
     *         rule document
     * @throws EDIException
     *         if the given rule document is not a valid EDI rule document
     */
    public AbstractEDIRule(final Document ruleDocument) throws EDIException {
        if (!isValidRuleDocument(ruleDocument)) {
            throw new InvalidRuleException();
        }
        ruleElement = createRootElement(ruleDocument);
        ruleTreeTraverser = new RuleTreeTraverser();
        setCurrentRuleToken(ruleElement);
    }

    private EDIRuleRoot createRootElement(final Document ruleDocument) {
        return (EDIRuleRoot) getRuleToken(ruleDocument.getRootElement());
    }

    private boolean isValidRuleDocument(final Document ruleDocument) {
        if (ruleDocument == null) {
            return false;
        }

        final Element rootElement = ruleDocument.getRootElement();
        if (!isSetRootElement(rootElement)) {
            return false;
        }

        return isSetCorrectStandardAndLayout(rootElement);
    }

    protected void setCurrentRuleToken(final RuleToken ruleToken) {
        currentRuleToken = ruleToken;
    }

    protected RuleToken getCurrentRuleToken() {
        return currentRuleToken;
    }

    private boolean isSetRootElement(final Element rootElement) {
        if (rootElement != null) {
            final String name = rootElement.getName();
            return StringUtil.isSet(name);
        }
        return false;
    }

    /**
     * @return <code>true</code> if standard and layout are set correctly on the
     * rule root element, <code>false</code> otherwise
     */
    private boolean isSetCorrectStandardAndLayout(final Element rootElement) {
        if (rootElement != null) {
            final RuleToken ruleToken = getRuleToken(rootElement);
            if (ruleToken instanceof EDIRuleRoot) {
                final EDIRuleRoot root = (EDIRuleRoot) ruleToken;
                return isSetCorrectStandard(root) && isSetCorrectLayout(root);
            }
        }
        return false;
    }

    protected abstract RuleToken getRuleToken(Element element);

    private boolean isSetCorrectLayout(final EDIRuleRoot root) {
        final String layout = root.getLayout();
        final String ruleLayout = getLayout();
        return layout.equals(ruleLayout);
    }

    private boolean isSetCorrectStandard(final EDIRuleRoot root) {
        final String standard = root.getStandard();
        final String ruleStandard = getStandard();
        return standard.equals(ruleStandard);
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
        return ruleElement;
    }

    /**
     * @return agency
     */
    public String getAgency() {
        final EDIRuleRoot rootToken = getRootElement();
        if (rootToken != null) {
            return rootToken.getAgency();
        }
        return "";
    }

    /**
     * @return rule description
     */
    public String getDescription() {
        final EDIRuleRoot rootToken = getRootElement();
        if (rootToken != null) {
            return rootToken.getDescription();
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
        final EDIRuleRoot rootToken = getRootElement();
        if (rootToken != null) {
            return rootToken.getRelease();
        }
        return "";
    }

    public abstract String getStandard();

    /**
     * @return rule type, e.g. IFCSUM
     */
    public String getType() {
        final EDIRuleRoot rootToken = getRootElement();
        if (rootToken != null) {
            return rootToken.getType();
        }
        return "";
    }

    /**
     * @return rule version, e.g. D
     */
    public String getVersion() {
        final EDIRuleRoot rootToken = getRootElement();
        if (rootToken != null) {
            return rootToken.getVersion();
        }
        return "";
    }

    @Override
    public String toString() {
        return getStandard() + "-" + getType() + "-" + getVersion() + "-" + getRelease() + "-" + getAgency();
    }

    public List<EDIRuleSegment> getSegments() {
        final EDIRuleRoot root = getRootElement();
        return getSegments(root);
    }

    private List<EDIRuleSegment> getSegments(final EDIRuleBaseToken token) {
        return token.getChildren().stream()
                .filter(child -> child instanceof EDIRuleSegment)
                .map(child -> (EDIRuleSegment) child)
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Method returns the next segment for the given ID.
     *
     * @param segmentID
     *         segment ID
     * @return segment or <code>empty</code> if no segment was found for the
     * given ID
     */
    public Optional<EDIRuleSegment> nextSegment(final String segmentID) {
        final EDIRuleBaseToken ruleToken = (EDIRuleBaseToken) getCurrentRuleToken();
        return ruleTreeTraverser.findNextSegment(ruleToken, segmentID);
    }

}
