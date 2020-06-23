package com.inubit.ibis.plugins.edi20.parsers;

import com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule;
import com.inubit.ibis.utils.EDIException;

import java.util.ArrayList;
import java.util.List;

public final class EDIParserRegistry {

    private static final List<EDIParser> REGISTERED_PARSER = new ArrayList<>();

    public static void register(final EDIParser parser) {
        REGISTERED_PARSER.add(parser);
    }

    public static void deregister(final EDIParser parser) {
        REGISTERED_PARSER.remove(parser);
    }

    /**
     * @param textInputDocument
     *         text input document
     * @return EDI parsers factory instance
     */
    public static EDIParserRegistry getInstance(final StringBuilder textInputDocument) {
        return new EDIParserRegistry(textInputDocument);
    }

    private final StringBuilder textInputDocument;

    private EDIParserRegistry(final StringBuilder textInputDocument) {
        this.textInputDocument = textInputDocument;
    }

    /**
     * @return parsers instance
     * @throws EDIException
     *         if retrieving parsers instance failed
     */
    public EDIParser getParser() throws EDIException {
        for (final EDIParser parser : REGISTERED_PARSER) {
            if (parser.canParse(textInputDocument)) {
                return parser;
            }
        }
        throw new EDIException("Unknown format of input document!");
    }

    /**
     * @param rule
     *         EDI rule
     * @return parsers instance for the given rule
     * @throws EDIException
     *         if no parsers was found for the given rule
     */
    public EDIParser getParser(final AbstractEDIRule rule) throws EDIException {
        for (final EDIParser parser : REGISTERED_PARSER) {
            if (parser.canParse(rule)) {
                return parser;
            }
        }
        throw new EDIException("Unknown format of input document!");
    }

}
