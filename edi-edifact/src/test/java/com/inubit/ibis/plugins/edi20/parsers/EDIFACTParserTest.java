package com.inubit.ibis.plugins.edi20.parsers;

import com.inubit.ibis.plugins.edi20.parsers.delimiters.EDIFACTDelimiters;
import com.inubit.ibis.plugins.edi20.rules.EDIFACTRule;
import com.inubit.ibis.plugins.edi20.scanners.EDIFACTLexicalScanner;
import com.inubit.ibis.utils.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.junit.MatcherAssert.assertThat;

class EDIFACTParserTest {

    @Disabled("Need a converted EDIFACT file in XML")
    void testGenericParserDocument() throws Exception {
        final String testFile = "EDIFACT-ifcsum-1.xml";
        final String ruleFile = "EDIFACT-IFCSUM-D-96A.xml";

        final EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(getFileContent(testFile), new EDIFACTDelimiters());
        final EDIFACTRule rule = new EDIFACTRule(getDocument(ruleFile));
        final EDIFACTParser parser = new EDIFACTParser(scanner, rule);
        parser.parse();
    }

    @Test
    void testGenericParserString() throws Exception {
        final String testFile = "EDIFACT-ifcsum-1.txt";
        final String ruleFile = "EDIFACT-IFCSUM-D-96A.xml";

        final EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(getFileContent(testFile), new EDIFACTDelimiters());
        final EDIFACTRule rule = new EDIFACTRule(getDocument(ruleFile));
        final EDIFACTParser parser = new EDIFACTParser(scanner, rule);
        parser.parse();
    }

    @Test
    void testLexicalPosition() throws Exception {
        final StringBuilder content = new StringBuilder("UNH+00000114000001+IFCSUM:D:96A:UN:FIBO01'BGM+785+470000004+9'BGM+785+470000004+9'DTM+137:200110150000:203'CNT+7:9776:KGM'CNT+10:36:NMB'CNT+11:94:NMB'RFF+AFB:470000004'DTM+171:200110150000:203'TDT+20+  470+30:LKW++:::WM-VIKTORIA GMBH & CO.'TSR++1++GEN'NAD+CS+27634000++RUDOLPH EDIFOR DUMMY'NAD+DC+27647000++WM-VIKTORIA GMBH & CO.'NAD+CA+012104011++WM-VIKTORIA GMBH & CO.+GATERWEG 120+DUISBURG++   047229+DE'EQD+SW+    1'EQD+EFP+1'EQN+5'CNI+1+3407070039455+1'DTM+137:200110120000:203'DTM+2:200110160154000000000000:203'CNT+7:115:KGM'CNT+11:1:NMB'TSR++++GEN'TOD+2++901'TOD+6++901'NAD+CZ+++DECOR BRAUNS-HEITMANN:GMBH & CO. KG+LUETKEFELD 15+WARBURG++34414+DE'");
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
