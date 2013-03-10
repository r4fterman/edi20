package com.inubit.ibis.plugins.edi20.parsers.vda;

import static junit.framework.Assert.assertNotNull;

import java.io.File;
import java.net.URL;

import com.inubit.ibis.plugins.edi20.parsers.vda.delimiters.VDADelimiters;
import com.inubit.ibis.plugins.edi20.parsers.vda.rules.VDARule;
import com.inubit.ibis.plugins.edi20.parsers.vda.scanners.VDALexicalScanner;
import com.inubit.ibis.utils.FileUtils;
import com.inubit.ibis.utils.XmlUtils;
import org.dom4j.Document;
import org.junit.Test;

/**
 * @author r4fter
 */
public class VDAParserTest {

    @Test
    public void testGenericParserString() throws Exception {
        String testFile = "VDA4905_1.txt";
        String ruleFile = "VDA-4905-1--.xml";

        VDALexicalScanner scanner = new VDALexicalScanner(getFileContent(testFile), new VDADelimiters());
        VDARule rule = new VDARule(getDocument(ruleFile));
        VDAParser parser = new VDAParser(scanner, rule);
        parser.parse();
    }

    private Document getDocument(final String testFile) throws Exception {
        URL url = VDAParserTest.class.getResource(testFile);
        assertNotNull("File not found: " + testFile, url);
        return XmlUtils.getDocumentThrowing(new File(url.toURI()));
    }

    private StringBuilder getFileContent(final String testFile) throws Exception {
        URL url = VDAParserTest.class.getResource(testFile);
        assertNotNull("File not found: " + testFile, url);
        StringBuilder content = FileUtils.getContents(new File(url.toURI()));
        assertNotNull(content);
        return content;
    }

}
