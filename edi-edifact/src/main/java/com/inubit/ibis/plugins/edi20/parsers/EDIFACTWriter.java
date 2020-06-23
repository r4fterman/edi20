package com.inubit.ibis.plugins.edi20.parsers;

import com.inubit.ibis.plugins.edi20.outputs.OutputWriter;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.OutputStream;

public class EDIFACTWriter implements OutputWriter {

    private static final String ELEMENT_NAME_INTERCHANGE = "Interchange";

    private static final String ATTRIBUTE_NAME_FORMAT = "format";

    private Element fRootElement;

    public EDIFACTWriter() {
        fRootElement = DocumentHelper.createElement(ELEMENT_NAME_INTERCHANGE);
        fRootElement.addAttribute(ATTRIBUTE_NAME_FORMAT, "EDIFACT");
    }

    @Override
    public void write(final OutputStream stream) {
        //todo: implement
    }

}
