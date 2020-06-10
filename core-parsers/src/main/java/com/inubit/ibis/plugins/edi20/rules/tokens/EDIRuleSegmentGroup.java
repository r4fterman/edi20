package com.inubit.ibis.plugins.edi20.rules.tokens;

import com.inubit.ibis.plugins.edi20.rules.interfaces.RuleToken;
import org.dom4j.Element;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author r4fter
 */
public abstract class EDIRuleSegmentGroup extends EDIRuleSegment {

    public EDIRuleSegmentGroup(final Element ruleElement) {
        // <SegmentGroup id="Group_1" loop="9"
        // name="A group of segments containing references and constants which apply to the entire message."
        // required="C" xmlTag="SegmentGroup_1">
        super(ruleElement);
    }

    @Override
    public String toString() {
        return "(Group) " + super.toString();
    }

    public List<EDIRuleSegment> getSegments() {
        return getChildren().stream()
                .filter(child -> child instanceof EDIRuleSegment)
                .map(child -> (EDIRuleSegment) child)
                .collect(Collectors.toUnmodifiableList());
    }
}
