package com.inubit.ibis.plugins.edi20.parsers;

import com.inubit.ibis.plugins.edi20.parsers.delimiters.VDADelimiters;
import com.inubit.ibis.plugins.edi20.rules.VDARule;
import com.inubit.ibis.plugins.edi20.scanners.VDALexicalScanner;
import com.inubit.ibis.utils.FileUtils;
import com.inubit.ibis.utils.XmlUtils;
import org.dom4j.Document;
import org.junit.jupiter.api.Disabled;

import java.io.File;
import java.net.URL;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.junit.MatcherAssert.assertThat;

/**
 * @author r4fter
 */
class VDAParserTest {

    @Disabled
    void testGenericParserString() throws Exception {
        String testFile = "VDA4905_1.txt";
        String ruleFile = "VDA-4905-1--.xml";

        StringBuilder content = getFileContent(testFile);
        VDALexicalScanner scanner = new VDALexicalScanner(content, new VDADelimiters());
        VDARule rule = new VDARule(getDocument(ruleFile));
        VDAParser parser = new VDAParser(scanner, rule);
        parser.parse();
    }

    @Disabled
    void testParseString() throws Exception {
        String testString = "51201001000000002020610000000001020523 1H9 609 075 A         1H9 609 075 A        AGA130291S2300001ABT STL  S              50    ";
        String ruleFile = "VDA-4905-1--.xml";

        VDALexicalScanner scanner = new VDALexicalScanner(new StringBuilder(testString), new VDADelimiters());
        VDARule rule = new VDARule(getDocument(ruleFile));
        VDAParser parser = new VDAParser(scanner, rule);
        parser.parse();
    }

    private Document getDocument(final String testFile) throws Exception {
        URL url = VDAParserTest.class.getResource(testFile);
        assertThat("File not found: " + testFile, url, not(nullValue()));
        return XmlUtils.getDocumentThrowing(new File(url.toURI()));
    }

    private StringBuilder getFileContent(final String testFile) throws Exception {
        URL url = VDAParserTest.class.getResource(testFile);
        assertThat("File not found: " + testFile, url, not(nullValue()));
        StringBuilder content = FileUtils.getContents(new File(url.toURI()));
        assertThat(content, not(nullValue()));
        return content;
    }

}
