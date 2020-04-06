package com.inubit.ibis.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

/**
 * @author r4fter
 */
class StringUtilTest {

    @Test
    void testIsNotSetEmptyString() {
        assertThat(StringUtil.isNotSet(""), is(true));
    }

    @Test
    void testIsNotSetNULLString() {
        assertThat(StringUtil.isNotSet((String) null), is(true));
    }

    @Test
    void testIsNotSetString() {
        assertThat(StringUtil.isNotSet("Text"), is(false));
    }

    @Test
    void testIsNotSetWhitespaceString() {
        assertThat(StringUtil.isNotSet(" "), is(false));
    }

    @Test
    void testIsSetEmptyString() {
        assertThat(StringUtil.isSet(""), is(false));
    }

    @Test
    void testIsSetNULLString() {
        assertThat(StringUtil.isSet((String) null), is(false));
    }

    @Test
    void testIsSetString() {
        assertThat(StringUtil.isSet("text"), is(true));
    }

    @Test
    void testIsSetWhitespaceString() {
        assertThat(StringUtil.isSet(" "), is(true));
    }

    @Test
    void testIsSetEmptyStringBuffer() {
        assertThat(StringUtil.isSet(new StringBuffer()), is(false));
    }

    @Test
    void testIsSetNULLStringBuffer() {
        assertThat(StringUtil.isSet((StringBuffer) null), is(false));
    }

    @Test
    void testIsSetStringBuffer() {
        assertThat(StringUtil.isSet(new StringBuffer("text")), is(true));
    }

    @Test
    void testIsSetWhitespaceStringBuffer() {
        assertThat(StringUtil.isSet(new StringBuffer(" ")), is(true));
    }

    @Test
    void testIsNotSetEmptyStringBuffer() {
        assertThat(StringUtil.isNotSet(new StringBuffer()), is(true));
    }

    @Test
    void testIsNotSetNULLStringBuffer() {
        assertThat(StringUtil.isNotSet((StringBuffer) null), is(true));
    }

    @Test
    void testIsNotSetStringBuffer() {
        assertThat(StringUtil.isNotSet(new StringBuffer("text")), is(false));
    }

    @Test
    void testIsNotSetWhitespaceStringBuffer() {
        assertThat(StringUtil.isNotSet(new StringBuffer()), is(true));
    }

    @Test
    void testIsOneOfEmptyStringEmptyListOfString() {
        assertThat(StringUtil.isOneOf("", List.of()), is(false));
    }

    @Test
    void testIsOneOfNULLStringEmptyListOfString() {
        assertThat(StringUtil.isOneOf(null, List.of()), is(false));
    }

    @Test
    void testIsOneOfEmptyStringNULLListOfString() {
        assertThat(StringUtil.isOneOf("", (List<String>) null), is(false));
    }

    @Test
    void testIsOneOfNULLStringNULLListOfString() {
        assertThat(StringUtil.isOneOf(null, (List<String>) null), is(false));
    }

    @Test
    void testIsOneOfEmptyStringListOfString() {
        final List<String> valueList = List.of("");
        assertThat(StringUtil.isOneOf("", valueList), is(false));
    }

    @Test
    void testIsOneOfWhitespaceStringListOfEmptyString() {
        final List<String> valueList = List.of("");
        assertThat(StringUtil.isOneOf(" ", valueList), is(false));
    }

    @Test
    void testIsOneOfStringListOfMatchingString() {
        final List<String> valueList = List.of("text");
        assertThat(StringUtil.isOneOf("text", valueList), is(true));
    }

    @Test
    void testIsOneOfStringListOfNonMatchingString() {
        final List<String> valueList = List.of(" text", " text ", "text ");
        assertThat(StringUtil.isOneOf("text", valueList), is(false));
    }

    @Test
    void testIsOneOfStringListOfMatchingStrings() {
        final List<String> valueList = List.of(" text", " text ", "text ", "text");
        assertThat(StringUtil.isOneOf("text", valueList), is(true));
    }

    @Test
    void testIsWhitespacesOnlyEmptyString() {
        assertThat(StringUtil.isWhitespacesOnly(""), is(false));
    }

    @Test
    void testIsWhitespacesOnlyNULLString() {
        assertThat(StringUtil.isWhitespacesOnly(null), is(false));
    }

    @Test
    void testIsWhitespacesOnlyString() {
        assertThat(StringUtil.isWhitespacesOnly("text"), is(false));
    }

    @Test
    void testIsWhitespacesOnlyStringWithWhitespaces() {
        assertThat(StringUtil.isWhitespacesOnly("   text "), is(false));
    }

    @Test
    void testIsWhitespacesOnlyWhitespaceString() {
        assertThat(StringUtil.isWhitespacesOnly("       "), is(true));
    }

}
