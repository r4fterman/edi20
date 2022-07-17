package com.inubit.ibis.plugins.edi20.rules;

import com.inubit.ibis.plugins.edi20.rules.interfaces.RepeatableRuleToken;
import com.inubit.ibis.plugins.edi20.rules.interfaces.RuleToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleBaseToken;

public final class RuleUtil {

    /**
     * @param ruleToken
     *         rule token
     * @return following sibling or <code>null</code> if no such sibling was found
     */
    public static RuleToken getFollowingSibling(final RuleToken ruleToken) {
        if (ruleToken == null) {
            throw new IllegalArgumentException("Rule token is NULL!");
        }
        if (ruleToken instanceof EDIRuleBaseToken) {
            EDIRuleBaseToken ruleBaseToken = ((EDIRuleBaseToken) ruleToken);
            RuleToken parentToken = ruleBaseToken.getParent();
            if (parentToken instanceof EDIRuleBaseToken) {
                EDIRuleBaseToken parentBaseToken = ((EDIRuleBaseToken) parentToken);
                int idx = parentBaseToken.getIndexOfChild(ruleBaseToken) + 1;
                if (isLastChild(parentBaseToken, idx)) {
                    if (parentToken instanceof RepeatableRuleToken) {
                        if (((RepeatableRuleToken) parentToken).hasLoop()) {
                            return parentToken;
                        }
                    }
                    return getFollowingSibling(parentToken);
                }
                return parentBaseToken.getChildren().get(idx);
            }
        }
        return null;
    }

    private static boolean isLastChild(final EDIRuleBaseToken parentBaseToken, final int index) {
        return index == parentBaseToken.getChildren().size();
    }

    /**
     * @param ruleToken
     *         rule token
     * @return child or following sibling or <code>null</code> if no such sibling was found
     */
    public static RuleToken getChildOrFollowingSibling(final RuleToken ruleToken) {
        if (ruleToken == null) {
            throw new IllegalArgumentException("Rule token is NULL!");
        }
        if (ruleToken instanceof EDIRuleBaseToken) {
            EDIRuleBaseToken ruleBaseToken = (EDIRuleBaseToken) ruleToken;
            if (ruleBaseToken.hasChildren()) {
                return ruleBaseToken.getChildren().get(0);
            }
            return getFollowingSibling(ruleToken);
        }
        return null;
    }

    /**
     * @param ruleToken
     *         rule token
     * @return parent following sibling or <code>null</code> if no such sibling was found
     */
    public static RuleToken getParentFollowingSibling(final RuleToken ruleToken) {
        if (ruleToken == null) {
            throw new IllegalArgumentException("Rule token is NULL!");
        }
        if (ruleToken instanceof EDIRuleBaseToken) {
            EDIRuleBaseToken ruleBaseToken = (EDIRuleBaseToken) ruleToken;
            return getFollowingSibling(ruleBaseToken.getParent());
        }
        return null;
    }

}
