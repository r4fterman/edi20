package com.inubit.ibis.plugins.edi20.parsers;

import com.inubit.ibis.plugins.edi20.parsers.delimiters.DATANORMDelimiters;
import com.inubit.ibis.plugins.edi20.rules.DATANORMRule;
import com.inubit.ibis.plugins.edi20.scanners.DATANORMLexicalScanner;
import com.inubit.ibis.utils.FileUtils;
import com.inubit.ibis.utils.XmlUtils;
import org.dom4j.Document;
import org.junit.jupiter.api.Disabled;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.junit.MatcherAssert.assertThat;

/**
 * @author r4fter
 */
class DATANORMParserTest {

    @Disabled
    void testGenericParserString() throws Exception {
        String testFile = "datanorm4.001.txt";
        String ruleFile = "DATANORM-All-4.0--.xml";

        StringBuilder content = getFileContent(testFile);
        DATANORMParser parser = getDatanormParser(ruleFile, content);
        parser.parse();
    }

    @Disabled
    void testParseString() throws Exception {
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
        assertThat(content, not(nullValue()));
        return content;
    }

    private File getFile(String testFile) throws URISyntaxException {
        URL url = DATANORMParserTest.class.getResource(testFile);
        assertThat("File not found: " + testFile, url, not(nullValue()));
        return new File(url.toURI());
    }
}
