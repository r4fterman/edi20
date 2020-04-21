package com.inubit.ibis.plugins.edi20.rules.tokens;

import com.inubit.ibis.plugins.edi20.rules.interfaces.IElementRuleToken;
import com.inubit.ibis.plugins.edi20.rules.interfaces.IRuleToken;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * @author r4fter
 */
public abstract class EDIRuleCompositeElement extends EDIRuleBaseToken implements IElementRuleToken {

    public EDIRuleCompositeElement(final Element ruleElement) {
        super(ruleElement);
    }

    @Override
    public String toString() {
        // <CompositeElement id="S009" name="Message Identifier" required="M" xmlTag="MessageIdentifierCE">
        return "(Composite) " + super.toString();
    }

    public List<IElementRuleToken> getElements() {
        final List<IElementRuleToken> elements = new ArrayList<>();
        for (final IRuleToken child : getChildren()) {
            if (child instanceof EDIRuleElement) {
                elements.add((IElementRuleToken) child);
            }
        }
        return elements;
    }
}
