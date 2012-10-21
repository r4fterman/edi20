package com.inubit.ibis.plugins.edi20.commons.parsers;

import com.inubit.ibis.plugins.edi20.commons.rules.AbstractEDIRule;
import com.inubit.ibis.plugins.edi20.commons.scanners.IScanner;

/**
 * Hierarchical with fixed parameter element (HWFPE)
 * 
 * @author r4fter
 */
public abstract class HWFPEParser extends AbstractEDIParser {

    public HWFPEParser(IScanner scanner, AbstractEDIRule rule) {
        super(scanner, rule);
    }

}
