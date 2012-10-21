package com.inubit.ibis.plugins.edi20.core.parsers.edifact.scanners;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import static junit.framework.Assert.*;

import org.junit.Test;

import com.inubit.ibis.plugins.edi20.core.parsers.edifact.delimiters.EDIFACTDelimiters;
import com.inubit.ibis.plugins.edi20.core.parsers.edifact.scanners.EDIFACTLexicalScanner;
import com.inubit.ibis.utils.FileUtils;

/**
 * @author rafter
 * 
 */
public class EDIFACTLexicalScannerTest {

	@Test
	public void testEDILexicalScanner() {
		try {
			String testFile = "EDIFACT-ifcsum-1.txt";
			URL url = EDIFACTLexicalScannerTest.class.getResource(testFile);
			assertNotNull("File not found: " + testFile, url);

			File edifactFile = new File(url.toURI());
			StringBuffer content = FileUtils.getContents(edifactFile);
			EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(content, new EDIFACTDelimiters());
			assertTrue(scanner.hasMoreTokens());
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (URISyntaxException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testEDILexicalScannerReadLength() {
		try {
			String testFile = "EDIFACT-ifcsum-1.txt";
			URL url = EDIFACTLexicalScannerTest.class.getResource(testFile);
			assertNotNull("File not found: " + testFile, url);

			File edifactFile = new File(url.toURI());
			StringBuffer content = FileUtils.getContents(edifactFile);
			EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(content, new EDIFACTDelimiters());
			assertTrue(scanner.hasMoreTokens());

			StringBuilder builder = new StringBuilder("");
			while (scanner.hasMoreTokens()) {
				builder.append(scanner.nextToken().getToken());
			}
			long fileLength = edifactFile.length() + 1;
			assertEquals("Length differs!", fileLength, Long.parseLong("" + builder.length()));
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (URISyntaxException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testEDILexicalScannerNoEscape() {
		String ediStr = "UNB+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";

		EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuffer(ediStr), new EDIFACTDelimiters());
		assertTrue(scanner.hasMoreTokens());
		assertEquals("UNB", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
		assertEquals("+", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
		assertEquals("UNOB", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
		assertEquals(":", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
		assertEquals("3", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
		assertEquals("+", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
		assertEquals("RUDOLF0", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
		assertEquals("+", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
		assertEquals("ELIX000", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
		assertEquals("+", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
		assertEquals("011015", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
		assertEquals(":", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
		assertEquals("1628", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
		assertEquals("+", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
		assertEquals("1", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
		assertEquals("'", scanner.nextToken().getToken());
        assertFalse(scanner.hasMoreTokens());
	}

	@Test
	public void testEDILexicalScannerEmptyDocEmptyDelim() {
		try {
			new EDIFACTLexicalScanner(new StringBuffer(""), new EDIFACTDelimiters());
			fail("Scanner should not be initialized. IllegalArgumentException should be thrown.");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testEDILexicalScannerNullDocEmptyDelim() {
		try {
			new EDIFACTLexicalScanner(null, new EDIFACTDelimiters());
			fail("Scanner should not be initialized. IllegalArgumentException should be thrown.");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testEDILexicalScannerNullDocNullDelim() {
		try {
			new EDIFACTLexicalScanner(null, null);
			fail("Scanner should not be initialized. IllegalArgumentException should be thrown.");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

}
