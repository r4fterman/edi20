package com.inubit.ibis.plugins.edi20.utils;

import static junit.framework.Assert.*;

import org.junit.Test;

import com.inubit.ibis.plugins.edi20.utils.EDIUtil;

/**
 * @author r4fter
 * 
 */
public class EDIUtilTest {

	@Test
	public void testIsEscapedEDIFACTSegmentStartsWithEscapeSign() {
		String token = "?'";
		assertEquals(true, EDIUtil.isEscaped(token, "'", "?"));
	}
	
	@Test
	public void testIsEscapedEDIFACTSegmentOneEscapeSign() {
		String token = "bla bla bla 1234567890 ?'";
		assertEquals(true, EDIUtil.isEscaped(token, "'", "?"));
	}

	@Test
	public void testIsEscapedEDIFACTSegmentTwoEscapeSign() {
		String token = "bla bla bla 1234567890 ??'";
		assertEquals(false, EDIUtil.isEscaped(token, "'", "?"));
	}

	@Test
	public void testIsEscapedEDIFACTSegmentThreeEscapeSign() {
		String token = "bla bla bla 1234567890 ???'";
		assertEquals(true, EDIUtil.isEscaped(token, "'", "?"));
	}

	@Test
	public void testIsEscapeEDIFACTSegmentFourEscapeSign() {
		String token = "bla bla bla 1234567890 ????'";
		assertEquals(false, EDIUtil.isEscaped(token, "'", "?"));
	}

	@Test
	public void testIsEscapedEmptyTokenEmptyDelimiter() {
		assertEquals(false, EDIUtil.isEscaped("", "'", "?"));
	}

	@Test
	public void testIsEscapedEmptyDelimiter() {
		String token = "bla bla bla 1234567890 ?'";
		assertEquals(false, EDIUtil.isEscaped(token, "", "?"));
	}

	@Test
	public void testIsEscapedEmptyEscapeDelimiter() {
		String token = "bla bla bla 1234567890 ?'";
		assertEquals(false, EDIUtil.isEscaped(token, "'", ""));
	}

	@Test
	public void testIsEscapedEmptyTokenEmptyDelimiterEmptyEscapeDelimiter() {
		assertEquals(false, EDIUtil.isEscaped("", "", ""));
	}

	@Test
	public void testIsEscapedNULLToken() {
		assertEquals(false, EDIUtil.isEscaped(null, "'", "?"));
	}

	@Test
	public void testIsEscapedNULLDelimiter() {
		String token = "bla bla bla 1234567890 ?'";
		assertEquals(false, EDIUtil.isEscaped(token, null, "?"));
	}

	@Test
	public void testIsEscapedNULLEscapeDelimiter() {
		String token = "bla bla bla 1234567890 ?'";
		assertEquals(false, EDIUtil.isEscaped(token, "'", null));
	}

	@Test
	public void testIsEscapedNULLTokenNULLDelimiterNULLEscapeDelimiter() {
		assertEquals(false, EDIUtil.isEscaped(null, null, null));
	}
}
