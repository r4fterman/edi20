package com.inubit.ibis.plugins.edi20.rules.tokens;

import java.util.ArrayList;
import java.util.List;

import com.inubit.ibis.plugins.edi20.rules.interfaces.IRuleToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.hwfpe.HwfpeRuleTokenFactory;
import org.dom4j.Element;

/**
 * @author r4fter
 */
public class EDIRuleSegmentGroup extends EDIRuleSegment {

    public EDIRuleSegmentGroup(final Element ruleElement) {
        // <SegmentGroup id="Group_1" loop="9"
        // name="A group of segments containing references and constants which apply to the entire message."
        // required="C" xmlTag="SegmentGroup_1">
        super(ruleElement);
    }

    @Override
    public String toString() {
        return "(Group) " + super.toString();
    }

    /**
     * @return first segment or <code>null</code> if child segment exists in this group
     */
    public IRuleToken getFirstSegment() {
        Element firstChild = getFirstChildElement();
        if (firstChild != null) {
            return HwfpeRuleTokenFactory.getInstance(firstChild);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public Element getFirstChildElement() {
        List<Element> childElements = getRuleElement().elements();
        if (childElements.size() > 0) {
            return childElements.get(0);
        }
        return null;
    }

    public boolean hasSegments() {
        if (hasChildren()) {
            return getFirstSegment() != null;
        }
        return false;
    }

    public List<EDIRuleSegment> getSegments() {
        List<EDIRuleSegment> segments = new ArrayList<EDIRuleSegment>();
        for (IRuleToken child : getChildrens()) {
            if (child instanceof EDIRuleSegment) {
                segments.add((EDIRuleSegment) child);
            }
        }
        return segments;
    }
}
