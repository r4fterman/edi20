package com.inubit.ibis.plugins.edi20.parsers;

import com.inubit.ibis.plugins.edi20.GenericParser;
import com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule;
import com.inubit.ibis.utils.EDIException;

/**
 * @author rafter
 */
public class GenericEDIXMLParser implements GenericParser {

    private EDIParser parser = null;

    public GenericEDIXMLParser(final StringBuilder textInputDocument) throws EDIException {
        init(textInputDocument);
    }

    public GenericEDIXMLParser(
            final StringBuilder textInputDocument,
            final AbstractEDIRule rule) throws EDIException {
        init(textInputDocument, rule);
    }

    private void init(final StringBuilder textInputDocument) throws EDIException {
        parser = EDIParserRegistry.getInstance(textInputDocument).getParser();
    }

    private void init(
            final StringBuilder textInputDocument,
            final AbstractEDIRule rule) throws EDIException {
        parser = EDIParserRegistry.getInstance(textInputDocument).getParser(rule);
    }

    @Override
    public void parse() throws EDIException {
        if (parser == null) {
            throw new EDIException("No parsers was not instantiated for input document!");
        }
        try {
            parser.parse();
        } catch (final Exception e) {
            throw new EDIException("Error while parsing input document!", e);
        }
    }

}
