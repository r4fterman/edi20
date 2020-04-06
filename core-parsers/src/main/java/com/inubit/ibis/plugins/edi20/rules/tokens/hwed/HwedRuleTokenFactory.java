package com.inubit.ibis.plugins.edi20.rules.tokens.hwed;

import com.inubit.ibis.plugins.edi20.rules.interfaces.IRuleToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleCompositeElement;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleRoot;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegment;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegmentGroup;
import org.dom4j.Element;

import java.util.Hashtable;

/**
 * @author r4fter
 */
public final class HwedRuleTokenFactory {

    private static final String NAME_RULE_SEGMENT = "Segment";
    private static final String NAME_RULE_ELEMENT = "Element";
    private static final String NAME_RULE_COMPOSITE_ELEMENT = "CompositeElement";
    private static final String NAME_RULE_SEGMENT_GROUP = "SegmentGroup";

    private static final String NAME_MESSAGE = "Message";
    private static final String NAME_ENVELOPER = "Enveloper";

    private static final Hashtable<Element, IRuleToken> INSTANCE_CACHE = new Hashtable<>(100);

    public static IRuleToken getInstance(final Element ruleElement) throws IllegalArgumentException {
        if (ruleElement == null) {
            throw new IllegalArgumentException("Element is null!");
        }
        if (INSTANCE_CACHE.containsKey(ruleElement)) {
            return INSTANCE_CACHE.get(ruleElement);
        }

        final String ruleElementName = ruleElement.getName();
        if (isRootElement(ruleElementName)) {
            return addToCache(ruleElement, new EDIRuleRoot(ruleElement));
        }
        if (isEveloperRootElement(ruleElementName)) {
            return addToCache(ruleElement, new EDIEnveloperRuleRoot(ruleElement));
        }
        if (isSegment(ruleElementName)) {
            return addToCache(ruleElement, new EDIRuleSegment(ruleElement));
        }
        if (isElement(ruleElementName)) {
            return addToCache(ruleElement, new HwedRuleElement(ruleElement));
        }
        if (isCompositeElement(ruleElementName)) {
            return addToCache(ruleElement, new EDIRuleCompositeElement(ruleElement));
        }
        if (isSegmentGroup(ruleElementName)) {
            return addToCache(ruleElement, new EDIRuleSegmentGroup(ruleElement));
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

    private static IRuleToken addToCache(
            final Element ruleElement,
            final IRuleToken token) {
        if (ruleElement != null && token != null) {
            INSTANCE_CACHE.put(ruleElement, token);
        }
        return token;
    }

}
