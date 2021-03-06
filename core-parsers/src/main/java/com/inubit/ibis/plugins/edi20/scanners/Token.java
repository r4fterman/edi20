package com.inubit.ibis.plugins.edi20.scanners;

public interface Token {

    /**
     * @return token position in document or -1 if not set
     */
    int getPosition();

    /**
     * @return the token
     */
    String getToken();

    /**
     * @return <code>true</code> if this token is a delimiter, <code>false</code> otherwise
     */
    boolean isDelimiter();

    /**
     * @return delimiter type or -1 if this token is no delimiter
     */
    int getDelimiterType();

}
