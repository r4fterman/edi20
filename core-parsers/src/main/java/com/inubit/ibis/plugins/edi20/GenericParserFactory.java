package com.inubit.ibis.plugins.edi20;

import com.inubit.ibis.plugins.edi20.parsers.GenericEDIXMLParser;
import com.inubit.ibis.plugins.edi20.parsers.GenericXMLEDIParser;
import com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule;
import com.inubit.ibis.utils.EDIException;
import org.dom4j.Document;

/**
 * Factory returns instance of parsers useable for the given input.
 *
 * @author rafter
 */
public final class GenericParserFactory {

    /**
     * @param document
     * @return
     *
     * @throws EDIException
     */
    public static GenericParser getInstance(final Document document) throws EDIException {
        return new GenericXMLEDIParser(document);
    }

    /**
     * @param text
     * @return
     *
     * @throws EDIException
     */
    public static GenericParser getInstance(final String text) throws EDIException {
        return getInstance(new StringBuilder(text));
    }

    /**
     * @param buffer
     * @return
     *
     * @throws EDIException
     */
    public static GenericParser getInstance(final StringBuilder buffer) throws EDIException {
        return new GenericEDIXMLParser(buffer);
    }

    /**
     * @param inputText
     * @param rule
     * @return
     *
     * @throws EDIException
     */
    public static GenericParser getInstance(final StringBuilder inputText, final AbstractEDIRule rule) throws EDIException {
        return new GenericEDIXMLParser(inputText, rule);
    }
}
