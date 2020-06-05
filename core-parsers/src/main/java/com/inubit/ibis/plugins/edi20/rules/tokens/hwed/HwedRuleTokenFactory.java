package com.inubit.ibis.plugins.edi20.rules.tokens.hwed;

import com.inubit.ibis.plugins.edi20.rules.interfaces.RuleToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.RuleTokenFactory;
import org.dom4j.Element;

public final class HwedRuleTokenFactory implements RuleTokenFactory {

    private static final String NAME_RULE_SEGMENT = "Segment";
    private static final String NAME_RULE_ELEMENT = "Element";
    private static final String NAME_RULE_COMPOSITE_ELEMENT = "CompositeElement";
    private static final String NAME_RULE_SEGMENT_GROUP = "SegmentGroup";

    private static final String NAME_MESSAGE = "Message";
    private static final String NAME_ENVELOPER = "Enveloper";

    public RuleToken createInstance(final Element ruleElement) throws IllegalArgumentException {
        if (ruleElement == null) {
            throw new IllegalArgumentException("Element is null!");
        }

        final String ruleElementName = ruleElement.getName();
        if (isRootElement(ruleElementName)) {
            return addToCache(ruleElement, new HwedRuleRoot(ruleElement));
        }
        if (isEveloperRootElement(ruleElementName)) {
            return addToCache(ruleElement, new EDIEnveloperRuleRoot(ruleElement));
        }
        if (isSegment(ruleElementName)) {
            return addToCache(ruleElement, new HwedRuleSegment(ruleElement));
        }
        if (isElement(ruleElementName)) {
            return addToCache(ruleElement, new HwedRuleElement(ruleElement));
        }
        if (isCompositeElement(ruleElementName)) {
            return addToCache(ruleElement, new HwedRuleCompositeElement(ruleElement));
        }
        if (isSegmentGroup(ruleElementName)) {
            return addToCache(ruleElement, new HwedRuleSegmentGroup(ruleElement));
        }
        return null;
    }

    private static boolean isEveloperRootElement(final String ruleElementName) {
        return NAME_ENVELOPER.equals(ruleElementName);
    }

    private static boolean isSegmentGroup(final String ruleElementName) {
        return NAME_RULE_SEGMENT_GROUP.equals(ruleElementName);
    }

    private static boolean isCompositeElement(final String ruleElementName) {
        return NAME_RULE_COMPOSITE_ELEMENT.equals(ruleElementName);
    }

    private static boolean isElement(final String ruleElementName) {
        return NAME_RULE_ELEMENT.equals(ruleElementName);
    }

    private static boolean isSegment(final String ruleElementName) {
        return NAME_RULE_SEGMENT.equals(ruleElementName);
    }

    private static boolean isRootElement(final String ruleElementName) {
        return NAME_MESSAGE.equals(ruleElementName);
    }

    private static RuleToken addToCache(
            final Element ruleElement,
            final RuleToken token) {
        return token;
    }

}
