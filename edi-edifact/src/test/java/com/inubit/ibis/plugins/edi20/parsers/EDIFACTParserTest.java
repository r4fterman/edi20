package com.inubit.ibis.plugins.edi20.parsers;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import com.inubit.ibis.plugins.edi20.parsers.delimiters.EDIFACTDelimiters;
import com.inubit.ibis.plugins.edi20.rules.EDIFACTRule;
import com.inubit.ibis.plugins.edi20.scanners.EDIFACTLexicalScanner;
import com.inubit.ibis.utils.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author r4fter
 */
public class EDIFACTParserTest {

    @Ignore
    public void testGenericParserDocument() throws Exception {
        String testFile = "EDIFACT-ifcsum-1.xml";
        String ruleFile = "EDIFACT-IFCSUM-D-96A.xml";

        EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(getFileContent(testFile), new EDIFACTDelimiters());
        EDIFACTRule rule = new EDIFACTRule(getDocument(ruleFile));
        EDIFACTParser parser = new EDIFACTParser(scanner, rule);
        parser.parse();
    }

    @Test
    public void testGenericParserString() throws Exception {
        String testFile = "EDIFACT-ifcsum-1.txt";
        String ruleFile = "EDIFACT-IFCSUM-D-96A.xml";

        EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(getFileContent(testFile), new EDIFACTDelimiters());
        EDIFACTRule rule = new EDIFACTRule(getDocument(ruleFile));
        EDIFACTParser parser = new EDIFACTParser(scanner, rule);
        parser.parse();
    }

    @Ignore
    public void testLexicalPosition() throws Exception {
        StringBuilder content = new StringBuilder("UNB+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'");
        String ruleFile = "EDIFACT-IFCSUM-D-96A.xml";

        EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(content, new EDIFACTDelimiters());
        EDIFACTRule rule = new EDIFACTRule(getDocument(ruleFile));
        EDIFACTParser parser = new EDIFACTParser(scanner, rule);
        parser.parse();
    }

    private StringBuilder getFileContent(final String testFile) throws IOException, URISyntaxException {
        URL url = EDIFACTParserTest.class.getResource(testFile);
        assertNotNull("File not found: " + testFile, url);
        File tFile = new File(url.toURI());
        StringBuilder content = FileUtils.getContents(tFile);
        assertNotNull(content);
        assertFalse(content.length() == 0);
        System.out.println("EDIFACTParserTest.getFileContent(): load file [" + testFile + "]");
        return content;
    }

    private Document getDocument(final String testFile) throws DocumentException, IOException, URISyntaxException {
        StringBuilder content = getFileContent(testFile);
        Document document = DocumentHelper.parseText(content.toString());
        assertNotNull(document);
        System.out.println("EDIFACTParserTest.getDocument(): load rule [" + testFile + "]");
        return document;
    }
}
