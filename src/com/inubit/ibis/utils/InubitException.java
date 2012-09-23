package com.inubit.ibis.utils;

/**
 * @author rafter
 * 
 */
public class InubitException extends Exception {

    /**
	 * 
	 */
    public InubitException() {
        // do nothing
    }

    /**
     * @param arg0
     */
    public InubitException(String arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     */
    public InubitException(Throwable arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     * @param arg1
     */
    public InubitException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

}
