package com.inubit.ibis.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Vector;

import org.junit.Test;

/**
 * @author r4fter
 */
public class StringUtilTest {

    @Test
    public void testIsNotSetEmptyString() {
        assertTrue(StringUtil.isNotSet(""));
    }

    @Test
    public void testIsNotSetNULLString() {
        assertTrue(StringUtil.isNotSet((String) null));
    }

    @Test
    public void testIsNotSetString() {
        assertFalse(StringUtil.isNotSet("Text"));
    }

    @Test
    public void testIsNotSetWhitespaceString() {
        assertFalse(StringUtil.isNotSet(" "));
    }

    @Test
    public void testIsSetEmptyString() {
        assertFalse(StringUtil.isSet(""));
    }

    @Test
    public void testIsSetnNULLString() {
        assertFalse(StringUtil.isSet((String) null));
    }

    @Test
    public void testIsSetString() {
        assertTrue(StringUtil.isSet("text"));
    }

    @Test
    public void testIsSetWhitespaceString() {
        assertTrue(StringUtil.isSet(" "));
    }

    @Test
    public void testIsSetEmptyStringBuffer() {
        assertFalse(StringUtil.isSet(new StringBuffer("")));
    }

    @Test
    public void testIsSetNULLStringBuffer() {
        assertFalse(StringUtil.isSet((StringBuffer) null));
    }

    @Test
    public void testIsSetStringBuffer() {
        assertTrue(StringUtil.isSet(new StringBuffer("text")));
    }

    @Test
    public void testIsSetWhitespaceStringBuffer() {
        assertTrue(StringUtil.isSet(new StringBuffer(" ")));
    }

    @Test
    public void testIsNotSetEmptyStringBuffer() {
        assertTrue(StringUtil.isNotSet(new StringBuffer("")));
    }

    @Test
    public void testIsNotSetNULLStringBuffer() {
        assertTrue(StringUtil.isNotSet((StringBuffer) null));
    }

    @Test
    public void testIsNotSetStringBuffer() {
        assertFalse(StringUtil.isNotSet(new StringBuffer("text")));
    }

    @Test
    public void testIsNotSetWhitespaceStringBuffer() {
        assertTrue(StringUtil.isNotSet(new StringBuffer("")));
    }

    @Test
    public void testIsOneOfEmptyStringEmptyListOfString() {
        assertFalse(StringUtil.isOneOf("", new Vector<String>(0)));
    }

    @Test
    public void testIsOneOfNULLStringEmptyListOfString() {
        assertFalse(StringUtil.isOneOf(null, new Vector<String>(0)));
    }

    @Test
    public void testIsOneOfEmptyStringNULLListOfString() {
        assertFalse(StringUtil.isOneOf("", (List<String>) null));
    }

    @Test
    public void testIsOneOfNULLStringNULLListOfString() {
        assertFalse(StringUtil.isOneOf(null, (List<String>) null));
    }

    @Test
    public void testIsOneOfEmptyStringListOfString() {
        List<String> valueList = new Vector<String>(1);
        valueList.add("");
        assertFalse(StringUtil.isOneOf("", valueList));
    }

    @Test
    public void testIsOneOfWhitespaceStringListOfEmptyString() {
        List<String> valueList = new Vector<String>(1);
        valueList.add("");
        assertFalse(StringUtil.isOneOf(" ", valueList));
    }

    @Test
    public void testIsOneOfStringListOfMatchingString() {
        List<String> valueList = new Vector<String>(1);
        valueList.add("text");
        assertTrue(StringUtil.isOneOf("text", valueList));
    }

    @Test
    public void testIsOneOfStringListOfNonMatchingString() {
        List<String> valueList = new Vector<String>(3);
        valueList.add(" text");
        valueList.add(" text ");
        valueList.add("text ");
        assertFalse(StringUtil.isOneOf("text", valueList));
    }

    @Test
    public void testIsOneOfStringListOfMatchingStrings() {
        List<String> valueList = new Vector<String>(4);
        valueList.add(" text");
        valueList.add(" text ");
        valueList.add("text ");
        valueList.add("text");
        assertTrue(StringUtil.isOneOf("text", valueList));
    }

    @Test
    public void testIsWhitespacesOnlyEmptyString() {
        assertFalse(StringUtil.isWhitespacesOnly(""));
    }

    @Test
    public void testIsWhitespacesOnlyNULLString() {
        assertFalse(StringUtil.isWhitespacesOnly(null));
    }

    @Test
    public void testIsWhitespacesOnlyString() {
        assertFalse(StringUtil.isWhitespacesOnly("text"));
    }

    @Test
    public void testIsWhitespacesOnlyStringWithWhitespaces() {
        assertFalse(StringUtil.isWhitespacesOnly("	text "));
    }

    @Test
    public void testIsWhitespacesOnlyWhitespaceString() {
        assertTrue(StringUtil.isWhitespacesOnly("   	"));
    }

}
