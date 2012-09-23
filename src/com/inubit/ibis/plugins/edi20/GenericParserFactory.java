package com.inubit.ibis.plugins.edi20;

import org.dom4j.Document;

import com.inubit.ibis.plugins.edi20.commons.parsers.GenericEDIXMLParser;
import com.inubit.ibis.plugins.edi20.commons.parsers.GenericXMLEDIParser;
import com.inubit.ibis.plugins.edi20.commons.rules.AbstractEDIRule;
import com.inubit.ibis.utils.InubitException;

/**
 * Factory returns instance of parser useable for the given input.
 * 
 * @author rafter
 */
public final class GenericParserFactory {

    /**
     * @param document
     * @return
     * @throws InubitException
     */
    public static IGenericParser getInstance(final Document document) throws InubitException {
        return new GenericXMLEDIParser(document);
    }

    /**
     * @param text
     * @return
     * @throws InubitException
     */
    public static IGenericParser getInstance(final String text) throws InubitException {
        return getInstance(new StringBuffer(text));
    }

    /**
     * @param buffer
     * @return
     * @throws InubitException
     */
    public static IGenericParser getInstance(final StringBuffer buffer) throws InubitException {
        return new GenericEDIXMLParser(buffer);
    }

    /**
     * @param inputText
     * @param rule
     * @return
     * @throws InubitException
     */
    public static IGenericParser getInstance(final StringBuffer inputText, final AbstractEDIRule rule) throws InubitException {
        return new GenericEDIXMLParser(inputText, rule);
    }
}
