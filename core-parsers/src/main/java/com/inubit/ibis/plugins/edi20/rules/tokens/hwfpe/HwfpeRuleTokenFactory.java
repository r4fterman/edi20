package com.inubit.ibis.plugins.edi20.rules.tokens.hwfpe;

import com.inubit.ibis.plugins.edi20.rules.interfaces.IRuleToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.RuleTokenFactory;
import org.dom4j.Element;

public final class HwfpeRuleTokenFactory implements RuleTokenFactory {

    private static final String NAME_RULESEGMENT = "Segment";
    private static final String NAME_RULEELEMENT = "Element";
    private static final String NAME_RULECOMPOSITEELEMENT = "CompositeElement";
    private static final String NAME_RULESEGMENTGROUP = "SegmentGroup";

    private static final String NAME_MESSAGE = "Message";


    public IRuleToken createInstance(final Element ruleElement) throws IllegalArgumentException {
        if (ruleElement == null) {
            throw new IllegalArgumentException("Element is null!");
        }

        final String ruleElementName = ruleElement.getName();
        if (NAME_MESSAGE.equals(ruleElementName)) {
            return addToCache(ruleElement, new HwfpeRuleRoot(ruleElement));
        }
        if (NAME_RULESEGMENT.equals(ruleElementName)) {
            return addToCache(ruleElement, new HwfpeRuleSegment(ruleElement));
        }
        if (NAME_RULEELEMENT.equals(ruleElementName)) {
            return addToCache(ruleElement, new HwfpeRuleElement(ruleElement));
        }
        if (NAME_RULECOMPOSITEELEMENT.equals(ruleElementName)) {
            return addToCache(ruleElement, new HwfpeRuleCompositeElement(ruleElement));
        }
        if (NAME_RULESEGMENTGROUP.equals(ruleElementName)) {
            return addToCache(ruleElement, new HwfpeRuleSegmentGroup(ruleElement));
        }
        return null;
    }

    private static IRuleToken addToCache(
            final Element ruleElement,
            final IRuleToken token) {
        return token;
    }

}
