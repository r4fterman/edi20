package com.inubit.ibis.plugins.edi20.rules.tokens;

import com.inubit.ibis.plugins.edi20.rules.interfaces.ElementRuleToken;
import com.inubit.ibis.plugins.edi20.rules.interfaces.RuleToken;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public abstract class EDIRuleCompositeElement extends EDIRuleBaseToken implements ElementRuleToken {

    protected EDIRuleCompositeElement(final Element ruleElement) {
        super(ruleElement);
    }

    @Override
    public String toString() {
        // <CompositeElement id="S009" name="Message Identifier" required="M" xmlTag="MessageIdentifierCE">
        return "(Composite) " + super.toString();
    }

    public List<ElementRuleToken> getElements() {
        final List<ElementRuleToken> elements = new ArrayList<>();
        for (final RuleToken child : getChildren()) {
            if (child instanceof EDIRuleElement) {
                elements.add((ElementRuleToken) child);
            }
        }
        return elements;
    }
}
