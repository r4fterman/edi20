package com.inubit.ibis.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * @author r4fter
 */
public final class XmlUtils {

    public static Document getDocument(final File xmlFile) {
        try {
            return getDocumentThrowing(xmlFile);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Document getDocumentThrowing(final File xmlFile) throws DocumentException {
        if (xmlFile == null) {
            throw new NullPointerException("File is null!");
        }

        SAXReader reader = new SAXReader();
        return reader.read(xmlFile);
    }

    public static String serializePretty(final Node node) {
        return serializePretty(node, "UTF-8");
    }

    public static String serializePretty(Node node, String encoding) {
        XMLWriter writer = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding(encoding);

            writer = new XMLWriter(bos, format);
            writer.write(node);
            writer.flush();

            return new String(bos.toByteArray(), encoding);
        } catch (UnsupportedEncodingException e) {
            // do nothing
        } catch (IOException e) {
            // do nothing
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }
        return "";
    }

}
