package com.inubit.ibis.plugins.edi20.core.parsers.edifact.delimiters;

import static junit.framework.Assert.*;

import org.junit.Test;

import com.inubit.ibis.plugins.edi20.core.parsers.edifact.delimiters.EDIFACTDelimiters;

/**
 * @author r4fter
 * 
 */
public class EDIFACTDelimitersTest {

	@Test
	public void testStandardEDIFACTDelimiters() {
		// set custom delimiter
		String beginOfDocument = "UNA:+.? '";
		EDIFACTDelimiters delimiters = new EDIFACTDelimiters(beginOfDocument);
		assertEquals(":", delimiters.getComplexElementDelimiter());
		assertEquals(".", delimiters.getDecimalDelimiter());
		assertEquals("+", delimiters.getElementDelimiter());
		assertEquals("?", delimiters.getEscapeDelimiter());
		assertEquals("'", delimiters.getSegmentDelimiter());
	}

	@Test
	public void testEDIFACTDelimiters_SegmentIsSlash() {
		// set custom delimiter
		String beginOfDocument = "UNA/',+ :";
		EDIFACTDelimiters delimiters = new EDIFACTDelimiters(beginOfDocument);
		assertEquals("/", delimiters.getComplexElementDelimiter());
		assertEquals(",", delimiters.getDecimalDelimiter());
		assertEquals("'", delimiters.getElementDelimiter());
		assertEquals("+", delimiters.getEscapeDelimiter());
		assertEquals(":", delimiters.getSegmentDelimiter());
	}

	@Test
	public void testEDIFACTDelimiters_SegmentIsDoubleDot() {
		// set default delimiter
		String beginOfDocument = "UNB+UNOB:3+";
		EDIFACTDelimiters delimiters = new EDIFACTDelimiters(beginOfDocument);
		assertEquals(":", delimiters.getComplexElementDelimiter());
		assertEquals(".", delimiters.getDecimalDelimiter());
		assertEquals("+", delimiters.getElementDelimiter());
		assertEquals("?", delimiters.getEscapeDelimiter());
		assertEquals("'", delimiters.getSegmentDelimiter());
	}

	@Test
	public void testEmptyEDIFACTDelimiters() {
		EDIFACTDelimiters delimiters = new EDIFACTDelimiters("");
		assertEquals(":", delimiters.getComplexElementDelimiter());
		assertEquals(".", delimiters.getDecimalDelimiter());
		assertEquals("+", delimiters.getElementDelimiter());
		assertEquals("?", delimiters.getEscapeDelimiter());
		assertEquals("'", delimiters.getSegmentDelimiter());
	}

	@Test
	public void testNULLEDIFACTDelimiters() {
		EDIFACTDelimiters delimiters = new EDIFACTDelimiters(null);
		assertEquals(":", delimiters.getComplexElementDelimiter());
		assertEquals(".", delimiters.getDecimalDelimiter());
		assertEquals("+", delimiters.getElementDelimiter());
		assertEquals("?", delimiters.getEscapeDelimiter());
		assertEquals("'", delimiters.getSegmentDelimiter());
	}

	@Test
	public void testEDIFACTDelimiters_DefaultConstructor() {
		EDIFACTDelimiters delimiters = new EDIFACTDelimiters();
		assertEquals(":", delimiters.getComplexElementDelimiter());
		assertEquals(".", delimiters.getDecimalDelimiter());
		assertEquals("+", delimiters.getElementDelimiter());
		assertEquals("?", delimiters.getEscapeDelimiter());
		assertEquals("'", delimiters.getSegmentDelimiter());
	}

	@Test
	public void testDelimiters_ContainsTokenDoubleDot() {
		EDIFACTDelimiters delimiters = new EDIFACTDelimiters();
		assertTrue(delimiters.containsDelimiter(":"));
	}

	@Test
	public void testDelimiters_ContainsTokenSlash() {
		EDIFACTDelimiters delimiters = new EDIFACTDelimiters();
		assertFalse(delimiters.containsDelimiter("/"));
	}

	@Test
	public void testDelimiters_ContainsTokenUNB() {
		EDIFACTDelimiters delimiters = new EDIFACTDelimiters();
		assertFalse(delimiters.containsDelimiter("UNB"));
	}

	@Test
	public void testDelimiters_ContainsTokenEmpty() {
		EDIFACTDelimiters delimiters = new EDIFACTDelimiters();
		assertFalse(delimiters.containsDelimiter(""));
	}

	@Test
	public void testDelimiters_ContainsTokenNULL() {
		EDIFACTDelimiters delimiters = new EDIFACTDelimiters();
		assertFalse(delimiters.containsDelimiter(null));
	}

}
