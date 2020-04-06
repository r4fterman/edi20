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
        final String testFile = "datanorm4.001.txt";
        final String ruleFile = "DATANORM-All-4.0--.xml";

        final StringBuilder content = getFileContent(testFile);
        final DATANORMParser parser = getDatanormParser(ruleFile, content);
        parser.parse();
    }

    @Disabled
    void testParseString() throws Exception {
        final String testString = "C;N;BAU;F;MKF;ABK;0700E.341280000;;030101;;;;1S;;;E;1000;60;33;1980;;ST;;;;FZ-DEUT.;700-3412~;;";
        final String ruleFile = "DATANORM-All-4.0--.xml";

        final StringBuilder content = new StringBuilder(testString);
        final DATANORMParser parser = getDatanormParser(ruleFile, content);
        parser.parse();
    }

    private DATANORMParser getDatanormParser(
            final String ruleFile,
            final StringBuilder content) throws Exception {
        final DATANORMLexicalScanner scanner = new DATANORMLexicalScanner(content, new DATANORMDelimiters());
        final DATANORMRule rule = new DATANORMRule(getDocument(ruleFile));
        return new DATANORMParser(scanner, rule);
    }

    private Document getDocument(final String testFile) throws Exception {
        final File file = getFile(testFile);
        return XmlUtils.getDocumentThrowing(file);
    }

    private StringBuilder getFileContent(final String testFile) throws Exception {
        final File file = getFile(testFile);
        final StringBuilder content = FileUtils.getContents(file);
        assertThat(content, not(nullValue()));
        return content;
    }

    private File getFile(final String testFile) throws URISyntaxException {
        final URL url = DATANORMParserTest.class.getResource(testFile);
        assertThat("File not found: " + testFile, url, not(nullValue()));
        return new File(url.toURI());
    }
}
