package com.inubit.ibis.plugins.edi20.parsers;

import com.inubit.ibis.plugins.edi20.scanners.IToken;

/**
 * Created with IntelliJ IDEA. User: r4fter Date: 24.03.13 Time: 15:20 To change this template use File | Settings |
 * File Templates.
 */
public class UnknownDelimiterTokenException extends Exception {

    public UnknownDelimiterTokenException(IToken token) {
        super("Unknown delimiter token found: " + token.toString());
    }
}
