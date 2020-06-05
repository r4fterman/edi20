package com.inubit.ibis.plugins.edi20.parsers;

import com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule;
import com.inubit.ibis.utils.InubitException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author r4fter
 */
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

    private StringBuilder fTextInputDocument;

    private EDIParserRegistry(final StringBuilder textInputDocument) {
        fTextInputDocument = textInputDocument;
    }

    /**
     * @return parsers instance
     * @throws InubitException
     *         if retrieving parsers instance failed
     */
    public EDIParser getParser() throws InubitException {
        for (final EDIParser parser : REGISTERED_PARSER) {
            if (parser.canParse(fTextInputDocument)) {
                return parser;
            }
        }
        throw new InubitException("Unknown format of input document!");
    }

    /**
     * @param rule
     *         EDI rule
     * @return parsers instance for the given rule
     * @throws InubitException
     *         if no parsers was found for the given rule
     */
    public EDIParser getParser(final AbstractEDIRule rule) throws InubitException {
        for (final EDIParser parser : REGISTERED_PARSER) {
            if (parser.canParse(rule)) {
                return parser;
            }
        }
        throw new InubitException("Unknown format of input document!");
    }

}
