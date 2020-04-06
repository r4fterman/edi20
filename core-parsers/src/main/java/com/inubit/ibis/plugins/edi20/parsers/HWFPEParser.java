package com.inubit.ibis.plugins.edi20.parsers;

import com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule;
import com.inubit.ibis.plugins.edi20.scanners.IScanner;

/**
 * Hierarchical with fixed parameter element (HWFPE).
 *
 * @author r4fter
 */
public abstract class HWFPEParser extends AbstractEDIParser {

    public HWFPEParser(
            final IScanner scanner,
            final AbstractEDIRule rule) {
        super(scanner, rule);
    }

}
