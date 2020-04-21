package com.inubit.ibis.plugins.edi20.parsers;

import com.inubit.ibis.plugins.edi20.parsers.delimiters.VDADelimiters;
import com.inubit.ibis.plugins.edi20.rules.VDARule;
import com.inubit.ibis.plugins.edi20.scanners.VDALexicalScanner;
import com.inubit.ibis.utils.FileUtils;
import com.inubit.ibis.utils.XmlUtils;
import org.dom4j.Document;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.junit.MatcherAssert.assertThat;

class VDAParserTest {

    private static final String RULE_DOCUMENT = "VDA-4905-1--.xml";
    private static final String MESSAGE_DOCUMENT = "VDA4905_1.txt";

    @Test
    void testGenericParserString() throws Exception {
        final StringBuilder content = getFileContent();
        final VDALexicalScanner scanner = new VDALexicalScanner(content, new VDADelimiters());
        final VDARule rule = new VDARule(getDocument());
        final VDAParser parser = new VDAParser(scanner, rule);
        parser.parse();
    }

    @Test
    void testParseString() throws Exception {
        final String testString = "51201001000000002020610000000001020523 1H9 609 075 A         1H9 609 075 A        AGA130291S2300001ABT STL  S              50    ";

        final VDALexicalScanner scanner = new VDALexicalScanner(new StringBuilder(testString), new VDADelimiters());
        final VDARule rule = new VDARule(getDocument());
        final VDAParser parser = new VDAParser(scanner, rule);
        parser.parse();
    }

    private Document getDocument() throws Exception {
        final URL url = VDAParserTest.class.getResource(RULE_DOCUMENT);
        assertThat("File not found: " + RULE_DOCUMENT, url, not(nullValue()));

        return XmlUtils.getDocumentThrowing(new File(url.toURI()));
    }

    private StringBuilder getFileContent() throws Exception {
        final URL url = VDAParserTest.class.getResource(MESSAGE_DOCUMENT);
        assertThat("File not found: " + MESSAGE_DOCUMENT, url, not(nullValue()));
        final StringBuilder content = FileUtils.getContents(new File(url.toURI()));
        assertThat(content, not(nullValue()));
        return content;
    }

}
