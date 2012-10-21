package com.inubit.ibis.plugins.edi20.core.parsers.vda;

import com.inubit.ibis.plugins.edi20.commons.parsers.HWFPEParser;
import com.inubit.ibis.plugins.edi20.commons.rules.AbstractEDIRule;
import com.inubit.ibis.plugins.edi20.core.parsers.vda.scanners.VDALexicalScanner;
import com.inubit.ibis.utils.InubitException;

/**
 * @author r4fter
 */
public class VDAParser extends HWFPEParser {

    /**
     * @param scanner
     * @param rule
     */
    public VDAParser(VDALexicalScanner scanner, AbstractEDIRule rule) {
        super(scanner, rule);
    }

    @Override
    public void parse() throws InubitException {
    }

}
