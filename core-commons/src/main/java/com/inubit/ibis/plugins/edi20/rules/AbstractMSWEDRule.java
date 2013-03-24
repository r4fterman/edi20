package com.inubit.ibis.plugins.edi20.rules;

import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleRoot;
import com.inubit.ibis.plugins.edi20.rules.tokens.hwfpe.HwfpeRuleTokenFactory;
import com.inubit.ibis.utils.InubitException;
import org.dom4j.Document;

/**
 * @author r4fter
 */
public abstract class AbstractMSWEDRule extends AbstractHWEDRule {

    /**
     * @param ruleDocument
     *         rule document
     * @throws InubitException
     *         if the given rule document is not a valid EDI rule document
     */
    public AbstractMSWEDRule(Document ruleDocument) throws InubitException {
        super(ruleDocument);
    }

    @Override
    protected EDIRuleRoot createRootElement(Document ruleDocument) {
        return (EDIRuleRoot) HwfpeRuleTokenFactory.getInstance(ruleDocument.getRootElement());
    }

    @Override
    public String getLayout() {
        return "mswed";
    }

}
