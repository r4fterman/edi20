package com.inubit.ibis.plugins.edi20.commons.rules.tokens;

import org.dom4j.Element;

/**
 * @author r4fter
 * 
 */
public class EDIRuleSegment extends EDIRuleBaseToken implements IRepeatableToken {

	private static final String ATTRIBUTE_NAME_LOOP = "loop";
	private static final int NO_LOOP = 1;

	private int fCurrentLoopCount = 0;

	public EDIRuleSegment(final Element ruleElement) {
		super(ruleElement);
	}

	@Override
	public int getLoop() {
		// <Segment id="RFF" loop="1" name="Reference" required="M" xmlTag="Reference">
		try {
			return Integer.parseInt(getRuleElement().attributeValue(ATTRIBUTE_NAME_LOOP, String.valueOf(NO_LOOP)));
		} catch (NumberFormatException e) {
			return NO_LOOP;
		}
	}

	@Override
	public boolean hasLoop() {
		return getLoop() > NO_LOOP;
	}

	public boolean isGroup() {
		return false;
	}

	@Override
	public String toString() {
		return "(Segment) " + super.toString() + ", loop=" + getLoop();
	}

	public void looped() {
		fCurrentLoopCount++;
	}

	public boolean isLoopLimitReached() {
		return fCurrentLoopCount >= getLoop();
	}

	public int getCurrentLoopCount() {
		return fCurrentLoopCount;
	}

	public void addLoopCount(int loopCount) {
		fCurrentLoopCount += loopCount;
	}
}
