package com.inubit.ibis.plugins.edi20.core.parsers.vda;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.junit.Test;

import com.inubit.ibis.plugins.edi20.core.parsers.vda.delimiters.VDADelimiters;
import com.inubit.ibis.plugins.edi20.core.parsers.vda.rules.VDARule;
import com.inubit.ibis.plugins.edi20.core.parsers.vda.scanners.VDALexicalScanner;
import com.inubit.ibis.utils.FileUtils;
import com.inubit.ibis.utils.InubitException;
import com.inubit.ibis.utils.XmlUtils;

/**
 * @author r4fter
 */
public class VDAParserTest {

    @Test
    public void testGenericParserString() {
        try {
            String testFile = "VDA4905_1.txt";
            String ruleFile = "VDA-4905-1--.xml";

            VDALexicalScanner scanner = new VDALexicalScanner(getFileContent(testFile), new VDADelimiters());
            VDARule rule = new VDARule(getDocument(ruleFile));
            VDAParser parser = new VDAParser(scanner, rule);
            parser.parse();
        } catch (InubitException e) {
            e.printStackTrace();
            fail("Error while parsing! " + e.getMessage());
        }
    }

    private Document getDocument(final String testFile) {
        try {
            URL url = VDAParserTest.class.getResource(testFile);
            assertNotNull("File not found: " + testFile, url);
            return XmlUtils.getDocumentThrowing(new File(url.toURI()));
        } catch (DocumentException e) {
            e.printStackTrace();
            fail(e.getMessage());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        return null;
    }

    private StringBuffer getFileContent(final String testFile) {
        try {
            URL url = VDAParserTest.class.getResource(testFile);
            assertNotNull("File not found: " + testFile, url);
            StringBuffer content = FileUtils.getContents(new File(url.toURI()));
            assertNotNull(content);
            return content;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            fail(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        return new StringBuffer(0);
    }

}
