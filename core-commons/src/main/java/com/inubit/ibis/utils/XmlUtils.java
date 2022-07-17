package com.inubit.ibis.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.SAXException;

public final class XmlUtils {

    public static Document getDocumentThrowing(final File xmlFile) throws DocumentException {
        if (xmlFile == null) {
            throw new DocumentException("File is null!");
        }

        final SAXReader reader = createSaxReader();
        return reader.read(xmlFile);
    }

    public static Document getDocumentThrowing(final InputStream xmlStream) throws DocumentException {
        if (xmlStream == null) {
            throw new DocumentException("Xml stream is null!");
        }

        final SAXReader reader = createSaxReader();
        return reader.read(xmlStream);
    }

    public static String serializePretty(final Node node) {
        return serializePretty(node, StandardCharsets.UTF_8);
    }

    public static String serializePretty(
            final Node node,
            final Charset encoding) {
        XMLWriter writer = null;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            final OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding(encoding.name());

            writer = new XMLWriter(bos, format);
            writer.write(node);
            writer.flush();

            return bos.toString(encoding);
        } catch (final IOException e) {
            // do nothing
        } finally {
            closeQuietly(writer);
        }
        return "";
    }

    private static SAXReader createSaxReader() throws DocumentException {
        try {
            final SAXReader reader = new SAXReader();
            reader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            reader.setFeature("http://xml.org/sax/features/external-general-entities", false);
            reader.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            return reader;
        } catch (final SAXException e) {
            throw new DocumentException("SAX reader creation failed.", e);
        }
    }

    private static void closeQuietly(XMLWriter writer) {
        if (writer == null) {
            return;
        }

        try {
            writer.close();
        } catch (final IOException e) {
            // do nothing
        }
    }

}
