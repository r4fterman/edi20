package com.inubit.ibis.plugins.edi20.commons.scanners;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import com.inubit.ibis.plugins.edi20.core.parsers.edifact.delimiters.EDIFACTDelimiters;
import com.inubit.ibis.plugins.edi20.core.parsers.edifact.scanners.EDIFACTLexicalScanner;

/**
 * @author r4fter
 */
public class EDILexicalScannerTest {

	@Test
	public void testGetNextIndexOfDelimiterEDIFACTElementStartsWithEscape() {
		String ediStr = "?+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";
		EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuffer(ediStr), new EDIFACTDelimiters());
		assertEquals(8, scanner.getIndexOfDelimiter(0, "+"));
	}

	@Test
	public void testGetNextIndexOfDelimiterEDIFACTSegment() {
		String ediStr = "UNB+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";
		EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuffer(ediStr), new EDIFACTDelimiters());
		assertEquals(40, scanner.getIndexOfDelimiter(0, "'"));
	}

	@Test
	public void testGetNextIndexOfDelimiterEDIFACTElement() {
		String ediStr = "UNB+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";
		EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuffer(ediStr), new EDIFACTDelimiters());
		assertEquals(3, scanner.getIndexOfDelimiter(0, "+"));
	}

	@Test
	public void testGetNextIndexOfDelimiterEDIFACTElement4Escapes() {
		String ediStr = "UNB?+UNOB:3?+RUDOLF0?+ELIX000?+011015:1628+1'";
		EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuffer(ediStr), new EDIFACTDelimiters());
		assertEquals(42, scanner.getIndexOfDelimiter(0, "+"));
	}

	@Test
	public void testGetNextIndexOfDelimiterEDIFACTElement1Escapes() {
		String ediStr = "UNB?+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";
		EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuffer(ediStr), new EDIFACTDelimiters());
		assertEquals(11, scanner.getIndexOfDelimiter(0, "+"));
	}

	@Test
	public void testGetNextIndexOfDelimiterEDIFACTComplexElement() {
		String ediStr = "UNB+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";
		EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuffer(ediStr), new EDIFACTDelimiters());
		assertEquals(8, scanner.getIndexOfDelimiter(0, ":"));
	}

}
