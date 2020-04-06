package com.inubit.ibis.plugins.edi20.parsers;

import com.inubit.ibis.plugins.edi20.parsers.delimiters.EDIFACTDelimiters;
import com.inubit.ibis.plugins.edi20.rules.EDIFACTRule;
import com.inubit.ibis.plugins.edi20.scanners.EDIFACTLexicalScanner;
import com.inubit.ibis.utils.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.junit.jupiter.api.Disabled;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.junit.MatcherAssert.assertThat;

/**
 * @author r4fter
 */
class EDIFACTParserTest {

    @Disabled
    void testGenericParserDocument() throws Exception {
        final String testFile = "EDIFACT-ifcsum-1.xml";
        final String ruleFile = "EDIFACT-IFCSUM-D-96A.xml";

        final EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(getFileContent(testFile), new EDIFACTDelimiters());
        final EDIFACTRule rule = new EDIFACTRule(getDocument(ruleFile));
        final EDIFACTParser parser = new EDIFACTParser(scanner, rule);
        parser.parse();
    }

    @Disabled
    void testGenericParserString() throws Exception {
        final String testFile = "EDIFACT-ifcsum-1.txt";
        final String ruleFile = "EDIFACT-IFCSUM-D-96A.xml";

        final EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(getFileContent(testFile), new EDIFACTDelimiters());
        final EDIFACTRule rule = new EDIFACTRule(getDocument(ruleFile));
        final EDIFACTParser parser = new EDIFACTParser(scanner, rule);
        parser.parse();
    }

    @Disabled
    void testLexicalPosition() throws Exception {
        final StringBuilder content = new StringBuilder("UNB+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'");
        final String ruleFile = "EDIFACT-IFCSUM-D-96A.xml";

        final EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(content, new EDIFACTDelimiters());
        final EDIFACTRule rule = new EDIFACTRule(getDocument(ruleFile));
        final EDIFACTParser parser = new EDIFACTParser(scanner, rule);
        parser.parse();
    }

    private StringBuilder getFileContent(final String testFile) throws IOException, URISyntaxException {
        final URL url = EDIFACTParserTest.class.getResource(testFile);
        assertThat("File not found: " + testFile, url, not(nullValue()));
        final File tFile = new File(url.toURI());
        final StringBuilder content = FileUtils.getContents(tFile);
        assertThat(content, not(nullValue()));
        assertThat(content.length(), not(0));
        System.out.println("EDIFACTParserTest.getFileContent(): load file [" + testFile + "]");
        return content;
    }

    private Document getDocument(final String testFile) throws DocumentException, IOException, URISyntaxException {
        final StringBuilder content = getFileContent(testFile);
        final Document document = DocumentHelper.parseText(content.toString());
        assertThat(document, not(nullValue()));
        System.out.println("EDIFACTParserTest.getDocument(): load rule [" + testFile + "]");
        return document;
    }
}
