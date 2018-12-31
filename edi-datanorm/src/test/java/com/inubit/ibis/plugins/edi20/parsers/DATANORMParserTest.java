package com.inubit.ibis.plugins.edi20.parsers;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import org.dom4j.Document;
import org.junit.Ignore;

import com.inubit.ibis.plugins.edi20.parsers.delimiters.DATANORMDelimiters;
import com.inubit.ibis.plugins.edi20.rules.DATANORMRule;
import com.inubit.ibis.plugins.edi20.scanners.DATANORMLexicalScanner;
import com.inubit.ibis.utils.FileUtils;
import com.inubit.ibis.utils.XmlUtils;

/**
 * @author r4fter
 */
public class DATANORMParserTest {

    @Ignore
    public void testGenericParserString() throws Exception {
        String testFile = "datanorm4.001.txt";
        String ruleFile = "DATANORM-All-4.0--.xml";

        StringBuilder content = getFileContent(testFile);
        DATANORMParser parser = getDatanormParser(ruleFile, content);
        parser.parse();
    }

    @Ignore
    public void testParseString() throws Exception {
        String testString = "C;N;BAU;F;MKF;ABK;0700E.341280000;;030101;;;;1S;;;E;1000;60;33;1980;;ST;;;;FZ-DEUT.;700-3412~;;";
        String ruleFile = "DATANORM-All-4.0--.xml";

        StringBuilder content = new StringBuilder(testString);
        DATANORMParser parser = getDatanormParser(ruleFile, content);
        parser.parse();
    }

    private DATANORMParser getDatanormParser(String ruleFile, StringBuilder content) throws Exception {
        DATANORMLexicalScanner scanner = new DATANORMLexicalScanner(content, new DATANORMDelimiters());
        DATANORMRule rule = new DATANORMRule(getDocument(ruleFile));
        return new DATANORMParser(scanner, rule);
    }

    private Document getDocument(final String testFile) throws Exception {
        File file = getFile(testFile);
        return XmlUtils.getDocumentThrowing(file);
    }

    private StringBuilder getFileContent(final String testFile) throws Exception {
        File file = getFile(testFile);
        StringBuilder content = FileUtils.getContents(file);
        assertNotNull(content);
        return content;
    }

    private File getFile(String testFile) throws URISyntaxException {
        URL url = DATANORMParserTest.class.getResource(testFile);
        assertNotNull("File not found: " + testFile, url);
        return new File(url.toURI());
    }
}
