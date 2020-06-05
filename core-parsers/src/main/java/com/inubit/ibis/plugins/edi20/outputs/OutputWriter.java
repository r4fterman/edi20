package com.inubit.ibis.plugins.edi20.outputs;

import java.io.OutputStream;

public interface OutputWriter {

    /**
     * Method writes result to the given output stream.
     *
     * @param stream
     *         output stream
     */
    void write(OutputStream stream);

}
