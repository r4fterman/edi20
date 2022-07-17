package com.inubit.ibis.plugins.edi20;

import org.dom4j.Document;

import com.inubit.ibis.plugins.edi20.parsers.GenericEDIXMLParser;
import com.inubit.ibis.plugins.edi20.parsers.GenericXMLEDIParser;
import com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule;
import com.inubit.ibis.utils.EDIException;

/**
 * Factory returns instance of parsers usable for the given input.
 *
 * @author rafter
 */
public final class GenericParserFactory {

    /**
     * @param document XML message
     * @return parse for XML message
     * @throws EDIException if XML message format is not supported
     */
    public static GenericParser getInstance(final Document document) throws EDIException {
        return new GenericXMLEDIParser(document);
    }

    /**
     * @param text EDI message
     * @return parser for EDI message
     * @throws EDIException if EDI message format is not supported
     */
    public static GenericParser getInstance(final String text) throws EDIException {
        return getInstance(new StringBuilder(text));
    }

    /**
     * @param builder EDI message
     * @return parser for EDI message
     * @throws EDIException if EDI message format is not supported
     */
    public static GenericParser getInstance(final StringBuilder builder) throws EDIException {
        return new GenericEDIXMLParser(builder);
    }

    /**
     * @param inputText EDI message
     * @param rule      EDI rule
     * @return parse for EDI message
     * @throws EDIException if EDI message format is not supported
     */
    public static GenericParser getInstance(
            final StringBuilder inputText,
            final AbstractEDIRule rule) throws EDIException {
        return new GenericEDIXMLParser(inputText, rule);
    }

    private GenericParserFactory() {
        // do nothing
    }
}
