package com.inubit.ibis.plugins.edi20.parsers;

import com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule;
import com.inubit.ibis.utils.EDIException;

public interface EDIParser {

    void parse() throws EDIException;

    /**
     * @param rule
     *         edi rule
     * @return true if rule can be parsed, false otherwise
     */
    boolean canParse(AbstractEDIRule rule);

    /**
     * @param inputDocument
     *         input document
     * @return true if input document can be parsed, false otherwise
     */
    boolean canParse(StringBuilder inputDocument);
}
