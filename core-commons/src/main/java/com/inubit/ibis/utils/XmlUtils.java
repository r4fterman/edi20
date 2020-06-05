package com.inubit.ibis.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public final class XmlUtils {

    public static Document getDocument(final File xmlFile) {
        try {
            return getDocumentThrowing(xmlFile);
        } catch (final DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Document getDocumentThrowing(final File xmlFile) throws DocumentException {
        if (xmlFile == null) {
            throw new NullPointerException("File is null!");
        }

        final SAXReader reader = new SAXReader();
        return reader.read(xmlFile);
    }

    public static Document getDocumentThrowing(final InputStream xmlStream) throws DocumentException {
        if (xmlStream == null) {
            throw new NullPointerException("Xml stream is null!");
        }

        final SAXReader reader = new SAXReader();
        return reader.read(xmlStream);
    }

    public static String serializePretty(final Node node) {
        return serializePretty(node, "UTF-8");
    }

    public static String serializePretty(
            final Node node,
            final String encoding) {
        XMLWriter writer = null;
        try {
            final ByteArrayOutputStream bos = new ByteArrayOutputStream();
            final OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding(encoding);

            writer = new XMLWriter(bos, format);
            writer.write(node);
            writer.flush();

            return new String(bos.toByteArray(), encoding);
        } catch (final IOException e) {
            // do nothing
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (final IOException e) {
                    // do nothing
                }
            }
        }
        return "";
    }

}
