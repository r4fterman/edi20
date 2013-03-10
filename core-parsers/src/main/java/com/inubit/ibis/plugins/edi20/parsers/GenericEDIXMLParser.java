package com.inubit.ibis.plugins.edi20.parsers;

import com.inubit.ibis.plugins.edi20.IGenericParser;
import com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule;
import com.inubit.ibis.utils.InubitException;

/**
 * @author rafter
 */
public class GenericEDIXMLParser implements IGenericParser {

    private IEDIParser parser = null;

    /**
     * Constructor
     *
     * @param textInputDocument
     *         text input document
     * @throws InubitException
     */
    public GenericEDIXMLParser(final StringBuilder textInputDocument) throws InubitException {
        init(textInputDocument);
    }

    /**
     * @param textInputDocument
     * @param rule
     * @throws InubitException
     */
    public GenericEDIXMLParser(final StringBuilder textInputDocument, final AbstractEDIRule rule) throws InubitException {
        init(textInputDocument, rule);
    }

    private void init(final StringBuilder textInputDocument) throws InubitException {
        parser = EDIParserRegistry.getInstance(textInputDocument).getParser();
    }

    private void init(final StringBuilder textInputDocument, final AbstractEDIRule rule) throws InubitException {
        parser = EDIParserRegistry.getInstance(textInputDocument).getParser(rule);
    }

    @Override
    public void parse() throws InubitException {
        if (parser == null) {
            throw new InubitException("No parser was not instantiated for input document!");
        }
        try {
            parser.parse();
        } catch (Exception e) {
            throw new InubitException("Error while parsing input document!", e);
        }
    }

}
