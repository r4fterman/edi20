package com.inubit.ibis.plugins.edi20;

import com.inubit.ibis.plugins.edi20.parsers.GenericEDIXMLParser;
import com.inubit.ibis.plugins.edi20.parsers.GenericXMLEDIParser;
import com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule;
import com.inubit.ibis.utils.InubitException;
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
     * @throws InubitException
     */
    public static GenericParser getInstance(final Document document) throws InubitException {
        return new GenericXMLEDIParser(document);
    }

    /**
     * @param text
     * @return
     *
     * @throws InubitException
     */
    public static GenericParser getInstance(final String text) throws InubitException {
        return getInstance(new StringBuilder(text));
    }

    /**
     * @param buffer
     * @return
     *
     * @throws InubitException
     */
    public static GenericParser getInstance(final StringBuilder buffer) throws InubitException {
        return new GenericEDIXMLParser(buffer);
    }

    /**
     * @param inputText
     * @param rule
     * @return
     *
     * @throws InubitException
     */
    public static GenericParser getInstance(final StringBuilder inputText, final AbstractEDIRule rule) throws InubitException {
        return new GenericEDIXMLParser(inputText, rule);
    }
}
