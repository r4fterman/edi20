package com.inubit.ibis.plugins.edi20.parsers;

import com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule;
import com.inubit.ibis.plugins.edi20.scanners.Scanner;

/**
 * Hierarchical with fixed parameter element (HWFPE).
 */
public abstract class HWFPEParser extends AbstractEDIParser {

    public HWFPEParser(
            final Scanner scanner,
            final AbstractEDIRule rule) {
        super(scanner, rule);
    }

}
