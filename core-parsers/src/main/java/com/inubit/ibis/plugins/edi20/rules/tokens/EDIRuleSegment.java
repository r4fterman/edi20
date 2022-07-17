package com.inubit.ibis.plugins.edi20.rules.tokens;

import java.util.List;
import java.util.stream.Collectors;

import org.dom4j.Element;

import com.inubit.ibis.plugins.edi20.rules.interfaces.ElementRuleToken;
import com.inubit.ibis.plugins.edi20.rules.interfaces.RepeatableRuleToken;

public abstract class EDIRuleSegment extends EDIRuleBaseToken implements RepeatableRuleToken {

    private static final String ATTRIBUTE_NAME_LOOP = "loop";
    private static final int NO_LOOP = 1;

    private final Loop maxLoop;

    private int loopCount = 0;

    protected EDIRuleSegment(final Element ruleElement) {
        // <Segment id="RFF" loop="1" name="Reference" required="M" xmlTag="Reference">
        super(ruleElement);
        maxLoop = new Loop(getRuleElement().attributeValue(ATTRIBUTE_NAME_LOOP, String.valueOf(NO_LOOP)));
    }

    @Override
    public Loop getLoop() {
        return maxLoop;
    }

    @Override
    public boolean hasLoop() {
        if (maxLoop.isInfinite()) {
            return true;
        }
        return maxLoop.getValueAsInteger() > NO_LOOP;
    }

    @Override
    public String toString() {
        return "(Segment) " + super.toString() + ", loop=" + getLoop();
    }

    @Override
    public void looped() {
        increaseLoopCount();
    }

    @Override
    public boolean canLoop() {
        return !isLoopLimitReached();
    }

    public boolean isLoopLimitReached() {
        if (maxLoop.isInfinite()) {
            return false;
        }
        return loopCount >= maxLoop.getValueAsInteger();
    }

    public int getCurrentLoopCount() {
        return loopCount;
    }

    private void increaseLoopCount() {
        this.loopCount += 1;
    }

    public List<ElementRuleToken> getElements() {
        return getChildren().stream()
                .filter(ElementRuleToken.class::isInstance)
                .map(ElementRuleToken.class::cast)
                .collect(Collectors.toUnmodifiableList());
    }
}
