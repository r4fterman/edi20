package com.inubit.ibis.utils;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Vector;

import org.junit.Test;

/**
 * @author r4fter
 */
public class StringUtilsTest {

	@Test
	public void testIsNotSetEmptyString() {
		assertTrue(StringUtils.isNotSet(""));
	}

	@Test
	public void testIsNotSetNULLString() {
		assertTrue(StringUtils.isNotSet((String) null));
	}

	@Test
	public void testIsNotSetString() {
		assertFalse(StringUtils.isNotSet("Text"));
	}

	@Test
	public void testIsNotSetWhitespaceString() {
		assertFalse(StringUtils.isNotSet(" "));
	}

	@Test
	public void testIsSetEmptyString() {
		assertFalse(StringUtils.isSet(""));
	}

	@Test
	public void testIsSetnNULLString() {
		assertFalse(StringUtils.isSet((String) null));
	}

	@Test
	public void testIsSetString() {
		assertTrue(StringUtils.isSet("text"));
	}

	@Test
	public void testIsSetWhitespaceString() {
		assertTrue(StringUtils.isSet(" "));
	}

	@Test
	public void testIsSetEmptyStringBuffer() {
		assertFalse(StringUtils.isSet(new StringBuffer("")));
	}

	@Test
	public void testIsSetNULLStringBuffer() {
		assertFalse(StringUtils.isSet((StringBuffer) null));
	}

	@Test
	public void testIsSetStringBuffer() {
		assertTrue(StringUtils.isSet(new StringBuffer("text")));
	}

	@Test
	public void testIsSetWhitespaceStringBuffer() {
		assertTrue(StringUtils.isSet(new StringBuffer(" ")));
	}

	@Test
	public void testIsNotSetEmptyStringBuffer() {
		assertTrue(StringUtils.isNotSet(new StringBuffer("")));
	}

	@Test
	public void testIsNotSetNULLStringBuffer() {
		assertTrue(StringUtils.isNotSet((StringBuffer) null));
	}

	@Test
	public void testIsNotSetStringBuffer() {
		assertFalse(StringUtils.isNotSet(new StringBuffer("text")));
	}

	@Test
	public void testIsNotSetWhitespaceStringBuffer() {
		assertTrue(StringUtils.isNotSet(new StringBuffer("")));
	}

	@Test
	public void testIsOneOfEmptyStringEmptyListOfString() {
		assertFalse(StringUtils.isOneOf("", new Vector<String>(0)));
	}

	@Test
	public void testIsOneOfNULLStringEmptyListOfString() {
		assertFalse(StringUtils.isOneOf(null, new Vector<String>(0)));
	}

	@Test
	public void testIsOneOfEmptyStringNULLListOfString() {
		assertFalse(StringUtils.isOneOf("", (List<String>) null));
	}

	@Test
	public void testIsOneOfNULLStringNULLListOfString() {
		assertFalse(StringUtils.isOneOf(null, (List<String>) null));
	}

	@Test
	public void testIsOneOfEmptyStringListOfString() {
		List<String> valueList = new Vector<String>(1);
		valueList.add("");
		assertFalse(StringUtils.isOneOf("", valueList));
	}

	@Test
	public void testIsOneOfWhitespaceStringListOfEmptyString() {
		List<String> valueList = new Vector<String>(1);
		valueList.add("");
		assertFalse(StringUtils.isOneOf(" ", valueList));
	}

	@Test
	public void testIsOneOfStringListOfMatchingString() {
		List<String> valueList = new Vector<String>(1);
		valueList.add("text");
		assertTrue(StringUtils.isOneOf("text", valueList));
	}

	@Test
	public void testIsOneOfStringListOfNonMatchingString() {
		List<String> valueList = new Vector<String>(3);
		valueList.add(" text");
		valueList.add(" text ");
		valueList.add("text ");
		assertFalse(StringUtils.isOneOf("text", valueList));
	}

	@Test
	public void testIsOneOfStringListOfMatchingStrings() {
		List<String> valueList = new Vector<String>(4);
		valueList.add(" text");
		valueList.add(" text ");
		valueList.add("text ");
		valueList.add("text");
		assertTrue(StringUtils.isOneOf("text", valueList));
	}

	@Test
	public void testIsWhitespacesOnlyEmptyString() {
		assertFalse(StringUtils.isWhitespacesOnly(""));
	}
	@Test
	public void testIsWhitespacesOnlyNULLString() {
		assertFalse(StringUtils.isWhitespacesOnly(null));
	}
	@Test
	public void testIsWhitespacesOnlyString() {
		assertFalse(StringUtils.isWhitespacesOnly("text"));
	}
	@Test
	public void testIsWhitespacesOnlyStringWithWhitespaces() {
		assertFalse(StringUtils.isWhitespacesOnly("	text "));
	}
	@Test
	public void testIsWhitespacesOnlyWhitespaceString() {
		assertTrue(StringUtils.isWhitespacesOnly("   	"));
	}



}
