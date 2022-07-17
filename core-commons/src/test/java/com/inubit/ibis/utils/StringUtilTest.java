package com.inubit.ibis.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class StringUtilTest {

    @Test
    void testIsNotSetEmptyString() {
        final boolean value = StringUtil.isNotSet("");

        assertThat(value, is(true));
    }

    @Test
    void testIsNotSetNULLString() {
        final boolean value = StringUtil.isNotSet((String) null);

        assertThat(value, is(true));
    }

    @Test
    void testIsNotSetString() {
        final boolean value = StringUtil.isNotSet("Text");

        assertThat(value, is(false));
    }

    @Test
    void testIsNotSetWhitespaceString() {
        final boolean value = StringUtil.isNotSet(" ");

        assertThat(value, is(false));
    }

    @Test
    void testIsSetEmptyString() {
        final boolean value = StringUtil.isSet("");

        assertThat(value, is(false));
    }

    @Test
    void testIsSetNULLString() {
        final boolean value = StringUtil.isSet(null);

        assertThat(value, is(false));
    }

    @Test
    void testIsSetString() {
        final boolean value = StringUtil.isSet("text");

        assertThat(value, is(true));
    }

    @Test
    void testIsSetWhitespaceString() {
        final boolean value = StringUtil.isSet(" ");

        assertThat(value, is(true));
    }

    @Test
    void testIsOneOfEmptyStringEmptyListOfString() {
        final boolean value = StringUtil.isOneOf("", List.of());

        assertThat(value, is(false));
    }

    @Test
    void testIsOneOfNULLStringEmptyListOfString() {
        final boolean value = StringUtil.isOneOf(null, List.of());

        assertThat(value, is(false));
    }

    @Test
    void testIsOneOfEmptyStringNULLListOfString() {
        final boolean value = StringUtil.isOneOf("", (List<String>) null);

        assertThat(value, is(false));
    }

    @Test
    void testIsOneOfNULLStringNULLListOfString() {
        final boolean value = StringUtil.isOneOf(null, (List<String>) null);

        assertThat(value, is(false));
    }

    @Test
    void testIsOneOfEmptyStringListOfString() {
        final List<String> valueList = List.of("");
        final boolean value = StringUtil.isOneOf("", valueList);

        assertThat(value, is(false));
    }

    @Test
    void testIsOneOfWhitespaceStringListOfEmptyString() {
        final List<String> valueList = List.of("");
        final boolean value = StringUtil.isOneOf(" ", valueList);

        assertThat(value, is(false));
    }

    @Test
    void testIsOneOfStringListOfMatchingString() {
        final List<String> valueList = List.of("text");
        final boolean value = StringUtil.isOneOf("text", valueList);

        assertThat(value, is(true));
    }

    @Test
    void testIsOneOfStringListOfNonMatchingString() {
        final List<String> valueList = List.of(" text", " text ", "text ");
        final boolean value = StringUtil.isOneOf("text", valueList);

        assertThat(value, is(false));
    }

    @Test
    void testIsOneOfStringListOfMatchingStrings() {
        final List<String> valueList = List.of(" text", " text ", "text ", "text");
        final boolean value = StringUtil.isOneOf("text", valueList);

        assertThat(value, is(true));
    }

    @Test
    void testIsWhitespacesOnlyEmptyString() {
        final boolean value = StringUtil.isWhitespacesOnly("");

        assertThat(value, is(false));
    }

    @Test
    void testIsWhitespacesOnlyNULLString() {
        final boolean value = StringUtil.isWhitespacesOnly(null);

        assertThat(value, is(false));
    }

    @Test
    void testIsWhitespacesOnlyString() {
        final boolean value = StringUtil.isWhitespacesOnly("text");

        assertThat(value, is(false));
    }

    @Test
    void testIsWhitespacesOnlyStringWithWhitespaces() {
        final boolean value = StringUtil.isWhitespacesOnly("   text ");

        assertThat(value, is(false));
    }

    @Test
    void testIsWhitespacesOnlyWhitespaceString() {
        final boolean value = StringUtil.isWhitespacesOnly("       ");

        assertThat(value, is(true));
    }

}
