package com.inubit.ibis.plugins.edi20.core.parsers.edifact;

import java.io.OutputStream;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.inubit.ibis.plugins.edi20.core.outputs.IOutputWriter;

/**
 * @author r4fter
 */
public class EDIFACTWriter implements IOutputWriter {

	private static final String ELEMENT_NAME_INTERCHANGE = "Interchange";

	private static final String ATTRIBUTE_NAME_FORMAT = "format";

	private Element fRootElement;

	public EDIFACTWriter() {
		fRootElement = DocumentHelper.createElement(ELEMENT_NAME_INTERCHANGE);
		fRootElement.addAttribute(ATTRIBUTE_NAME_FORMAT, "EDIFACT");
	}
	
	@Override
	public void write(final OutputStream stream) {
		// TODO: implement
	}
	
}
