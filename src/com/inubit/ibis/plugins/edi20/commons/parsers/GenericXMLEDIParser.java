package com.inubit.ibis.plugins.edi20.commons.parsers;

import org.dom4j.Document;

import com.inubit.ibis.plugins.edi20.IGenericParser;
import com.inubit.ibis.plugins.edi20.commons.scanners.XMLLexicalScanner;
import com.inubit.ibis.utils.InubitException;

/**
 * @author r4fter
 * 
 */
public class GenericXMLEDIParser implements IGenericParser {

    private XMLLexicalScanner fScanner;

    /**
     * @param inputDocument
     *            input document
     * @throws InubitException
     */
    public GenericXMLEDIParser(final Document inputDocument) throws InubitException {
        fScanner = new XMLLexicalScanner(inputDocument);
    }

    public void parse() {
        // FIXME: run parse on XMLEDI parser
    }
    
    public XMLLexicalScanner getScanner() {
    	return fScanner;
    }

}
