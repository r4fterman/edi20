package com.inubit.ibis.plugins.edi20.commons.rules.tokens;

import java.util.Hashtable;

import org.dom4j.Element;

/**
 * @author r4fter
 * 
 */
public class EDIRuleTokenFactory {

	private static final String NAME_RULESEGMENT = "Segment";
	private static final String NAME_RULEELEMENT = "Element";
	private static final String NAME_RULECOMPOSITEELEMENT = "CompositeElement";
	private static final String NAME_RULESEGMENTGROUP = "SegmentGroup";

	private static final String NAME_MESSAGE = "Message";

	private static final Hashtable<Element, IRuleToken> fInstanceCache = new Hashtable<Element, IRuleToken>(100);

	public static IRuleToken getInstance(final Element ruleElement) throws IllegalArgumentException {
		if (ruleElement == null) {
			throw new IllegalArgumentException("Element is null!");
		}
		if (fInstanceCache.containsKey(ruleElement)) {
			IRuleToken token = fInstanceCache.get(ruleElement);
//			if (token instanceof EDIRuleBaseToken) {
//				((EDIRuleBaseToken) token).resetChildIterator();
//			}
			return token;
		}

		String ruleElementName = ruleElement.getName();
		if (NAME_MESSAGE.equals(ruleElementName)) {
			return addToCache(ruleElement, new EDIRuleRoot(ruleElement));
		}
		if (NAME_RULESEGMENT.equals(ruleElementName)) {
			return addToCache(ruleElement, new EDIRuleSegment(ruleElement));
		}
		if (NAME_RULEELEMENT.equals(ruleElementName)) {
			return addToCache(ruleElement, new EDIRuleElement(ruleElement));
		}
		if (NAME_RULECOMPOSITEELEMENT.equals(ruleElementName)) {
			return addToCache(ruleElement, new EDIRuleCompositeElement(ruleElement));
		}
		if (NAME_RULESEGMENTGROUP.equals(ruleElementName)) {
			return addToCache(ruleElement, new EDIRuleSegmentGroup(ruleElement));
		}
		return null;
	}

	private static IRuleToken addToCache(final Element ruleElement, final IRuleToken token) {
		if (ruleElement != null && token != null) {
			fInstanceCache.put(ruleElement, token);
		}
		return token;
	}

}
