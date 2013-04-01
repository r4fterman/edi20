package com.inubit.ibis.plugins.edi20.parsers;

import com.inubit.ibis.plugins.edi20.scanners.IToken;
import com.inubit.ibis.utils.InubitException;

/**
 * @author r4fter
 */
public class UnknownDelimiterTokenException extends InubitException {

    public UnknownDelimiterTokenException(IToken token) {
        super("Unknown delimiter token found: " + token.toString());
    }
}
