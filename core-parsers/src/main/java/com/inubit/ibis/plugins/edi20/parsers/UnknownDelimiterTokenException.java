package com.inubit.ibis.plugins.edi20.parsers;

import com.inubit.ibis.plugins.edi20.scanners.Token;
import com.inubit.ibis.utils.InubitException;

/**
 * @author r4fter
 */
public class UnknownDelimiterTokenException extends InubitException {

    public UnknownDelimiterTokenException(final Token token) {
        super("Unknown delimiter token found: " + token.toString());
    }
}
