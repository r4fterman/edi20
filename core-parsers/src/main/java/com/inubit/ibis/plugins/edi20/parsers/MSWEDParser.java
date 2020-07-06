package com.inubit.ibis.plugins.edi20.parsers;

import com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule;
import com.inubit.ibis.plugins.edi20.rules.AbstractMSWEDRule;
import com.inubit.ibis.plugins.edi20.scanners.Scanner;

/**
 * @author r4fter
 */
public abstract class MSWEDParser extends HWEDParser {

    /**
     * @param scanner
     *         lexical scanner
     * @param rule
     *         EDI rule
     */
    public MSWEDParser(
            final Scanner scanner,
            final AbstractMSWEDRule rule) {
        super(scanner, rule);
    }

    @Override
    protected boolean isEndOfRule() {
        return false;
    }

    @Override
    public boolean canParse(final AbstractEDIRule rule) {
        return true;
    }

    @Override
    public boolean canParse(final StringBuilder inputDocument) {
        return true;
    }
}
