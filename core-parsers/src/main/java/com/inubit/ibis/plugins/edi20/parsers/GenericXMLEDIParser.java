package com.inubit.ibis.plugins.edi20.parsers;

import com.inubit.ibis.plugins.edi20.IGenericParser;
import com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule;
import com.inubit.ibis.utils.InubitException;
import org.dom4j.Document;

/**
 * @author r4fter
 */
public class GenericXMLEDIParser implements IGenericParser {

    private IEDIParser parser;

    /**
     * @param inputDocument
     *         input document
     */
    public GenericXMLEDIParser(final Document inputDocument) throws InubitException {
        init(inputDocument);
    }

    /**
     * @param inputDocument
     *         input document
     */
    public GenericXMLEDIParser(
            final Document inputDocument,
            final AbstractEDIRule rule) throws InubitException {
        init(inputDocument, rule);
    }

    private void init(final Document xmlInputDocument) {
        //        parsers = EDIParserRegistry.getInstance(xmlInputDocument).getParser();
    }

    private void init(
            final Document xmlInputDocument,
            final AbstractEDIRule rule) {
        //        parsers = EDIParserRegistry.getInstance(xmlInputDocument).getParser(rule);
    }

    @Override
    public void parse() throws InubitException {
        parser.parse();
    }

}
