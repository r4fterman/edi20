package com.inubit.ibis.plugins.edi20.rules.tokens;

import java.util.ArrayList;
import java.util.List;

import com.inubit.ibis.plugins.edi20.rules.interfaces.IElementRuleToken;
import com.inubit.ibis.plugins.edi20.rules.interfaces.IRuleToken;
import org.dom4j.Element;

/**
 * @author r4fter
 */
public class EDIRuleCompositeElement extends EDIRuleBaseToken implements IElementRuleToken {

    /**
     * @param ruleElement
     */
    public EDIRuleCompositeElement(final Element ruleElement) {
        super(ruleElement);
    }

    @Override
    public String toString() {
        // <CompositeElement id="S009" name="Message Identifier" required="M" xmlTag="MessageIdentifierCE">
        return "(Composite) " + super.toString();
    }

    public List<IElementRuleToken> getElements() {
        List<IElementRuleToken> elements = new ArrayList<IElementRuleToken>();
        for (IRuleToken child : getChildrens()) {
            if (child instanceof EDIRuleElement) {
                elements.add((IElementRuleToken) child);
            }
        }
        return elements;
    }
}
