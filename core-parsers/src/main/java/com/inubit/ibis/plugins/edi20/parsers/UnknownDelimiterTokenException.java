package com.inubit.ibis.plugins.edi20.parsers;

import com.inubit.ibis.plugins.edi20.scanners.Token;
import com.inubit.ibis.utils.EDIException;

public class UnknownDelimiterTokenException extends EDIException {

    public UnknownDelimiterTokenException(final Token token) {
        super(String.format("Unknown delimiter token found: %s", token.toString()));
    }
}
