package com.inubit.ibis.plugins.edi20.core.parsers.edifact;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.junit.Test;

import com.inubit.ibis.plugins.edi20.core.parsers.edifact.delimiters.EDIFACTDelimiters;
import com.inubit.ibis.plugins.edi20.core.parsers.edifact.rules.EDIFACTRule;
import com.inubit.ibis.plugins.edi20.core.parsers.edifact.scanners.EDIFACTLexicalScanner;
import com.inubit.ibis.utils.FileUtils;
import com.inubit.ibis.utils.InubitException;

/**
 * @author r4fter
 */
public class EDIFACTParserTest {

	public void testGenericParserDocument() {
		try {
			String testFile = "EDIFACT-ifcsum-1.xml";
			String ruleFile = "EDIFACT-IFCSUM-D-96A.xml";

			EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(getFileContent(testFile), new EDIFACTDelimiters());
			EDIFACTRule rule = new EDIFACTRule(getDocument(ruleFile));
			EDIFACTParser parser = new EDIFACTParser(scanner, rule);
			parser.parse();
		} catch (InubitException e) {
			fail("Error while initializing parser! " + e.getMessage());
		}
	}

	@Test
	public void testGenericParserString() {
		try {
			String testFile = "EDIFACT-ifcsum-1.txt";
			String ruleFile = "EDIFACT-IFCSUM-D-96A.xml";

			EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(getFileContent(testFile), new EDIFACTDelimiters());
			EDIFACTRule rule = new EDIFACTRule(getDocument(ruleFile));
			EDIFACTParser parser = new EDIFACTParser(scanner, rule);
			parser.parse();
		} catch (InubitException e) {
			e.printStackTrace();
			fail("Error while parsing! " + e.getMessage());
		}
	}

	public void testLexicalPosition() {
		try {
			StringBuffer content = new StringBuffer("UNB+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'");
			String ruleFile = "EDIFACT-IFCSUM-D-96A.xml";

			EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(content, new EDIFACTDelimiters());
			EDIFACTRule rule = new EDIFACTRule(getDocument(ruleFile));
			EDIFACTParser parser = new EDIFACTParser(scanner, rule);
			parser.parse();

		} catch (InubitException e) {
			fail("Error while initializing parser! " + e.getMessage());
		}
	}

	private StringBuffer getFileContent(final String testFile) {
		try {
			URL url = EDIFACTParserTest.class.getResource(testFile);
			assertNotNull("File not found: " + testFile, url);
			File tFile = new File(url.toURI());
			StringBuffer content = FileUtils.getContents(tFile);
			assertNotNull(content);
			assertFalse(content.length() == 0);
			System.out.println("EDIFACTParserTest.getFileContent(): load file [" + testFile + "]");
			return content;
		} catch (URISyntaxException e) {
			fail("URI wrong! " + e.getMessage());
		} catch (IOException e) {
			fail("Reading file failed! " + e.getMessage());
		}
		return null;
	}

	private Document getDocument(final String testFile) {
		try {
			Document document = DocumentHelper.parseText(getFileContent(testFile).toString());
			assertNotNull(document);
			System.out.println("EDIFACTParserTest.getDocument(): load rule [" + testFile + "]");
			return document;
		} catch (DocumentException e) {
			fail("Document invalid! " + e.getMessage());
		}
		return null;
	}
}
