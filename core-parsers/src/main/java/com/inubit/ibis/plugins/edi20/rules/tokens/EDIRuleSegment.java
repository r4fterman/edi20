package com.inubit.ibis.plugins.edi20.rules.tokens;

import com.inubit.ibis.plugins.edi20.rules.interfaces.ElementRuleToken;
import com.inubit.ibis.plugins.edi20.rules.interfaces.RepeatableRuleToken;
import com.inubit.ibis.plugins.edi20.rules.interfaces.RuleToken;
import com.inubit.ibis.utils.InubitException;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public abstract class EDIRuleSegment extends EDIRuleBaseToken implements RepeatableRuleToken {

    private static final String ATTRIBUTE_NAME_LOOP = "loop";
    private static final int NO_LOOP = 1;

    private int currentLoopCount = 0;

    public EDIRuleSegment(final Element ruleElement) {
        super(ruleElement);
    }

    @Override
    public int getLoop() {
        // <Segment id="RFF" loop="1" name="Reference" required="M" xmlTag="Reference">
        try {
            return Integer.parseInt(getRuleElement().attributeValue(ATTRIBUTE_NAME_LOOP, String.valueOf(NO_LOOP)));
        } catch (final NumberFormatException e) {
            return NO_LOOP;
        }
    }

    @Override
    public boolean hasLoop() {
        return getLoop() > NO_LOOP;
    }

    @Override
    public String toString() {
        return "(Segment) " + super.toString() + ", loop=" + getLoop();
    }

    @Override
    public void looped() throws InubitException {
        if (isLoopLimitReached()) {
            throw new InubitException("Segment [" + getID() + "] can only occurs [" + getLoop() + "] times!");
        }
        addLoopCount(1);
    }

    @Override
    public boolean canLoop() {
        return hasLoop() && !isLoopLimitReached();
    }

    public boolean isLoopLimitReached() {
        return currentLoopCount >= getLoop();
    }

    public int getCurrentLoopCount() {
        return currentLoopCount + 1;
    }

    public void addLoopCount(final int loopCount) {
        currentLoopCount += loopCount;
    }

    public List<ElementRuleToken> getElements() {
        final List<ElementRuleToken> elements = new ArrayList<>();
        for (final RuleToken child : getChildren()) {
            if (child instanceof ElementRuleToken) {
                elements.add((ElementRuleToken) child);
            }
        }
        return elements;
    }
}
