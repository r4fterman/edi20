package com.inubit.ibis.plugins.edi20.utils;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class EDIUtilTest {

    @Test
    void testIsEscapedEDIFACTSegmentStartsWithEscapeSign() {
        String token = "?'";
        assertThat(EDIUtil.isEscaped(token, "'", "?"), is(true));
    }

    @Test
    void testIsEscapedEDIFACTSegmentOneEscapeSign() {
        String token = "bla bla bla 1234567890 ?'";
        assertThat(EDIUtil.isEscaped(token, "'", "?"), is(true));
    }

    @Test
    void testIsEscapedEDIFACTSegmentTwoEscapeSign() {
        String token = "bla bla bla 1234567890 ??'";
        assertThat(EDIUtil.isEscaped(token, "'", "?"), is(false));
    }

    @Test
    void testIsEscapedEDIFACTSegmentThreeEscapeSign() {
        String token = "bla bla bla 1234567890 ???'";
        assertThat(EDIUtil.isEscaped(token, "'", "?"), is(true));
    }

    @Test
    void testIsEscapeEDIFACTSegmentFourEscapeSign() {
        String token = "bla bla bla 1234567890 ????'";
        assertThat(EDIUtil.isEscaped(token, "'", "?"), is(false));
    }

    @Test
    void testIsEscapedEmptyTokenEmptyDelimiter() {
        assertThat(EDIUtil.isEscaped("", "'", "?"), is(false));
    }

    @Test
    void testIsEscapedEmptyDelimiter() {
        String token = "bla bla bla 1234567890 ?'";
        assertThat(EDIUtil.isEscaped(token, "", "?"), is(false));
    }

    @Test
    void testIsEscapedEmptyEscapeDelimiter() {
        String token = "bla bla bla 1234567890 ?'";
        assertThat(EDIUtil.isEscaped(token, "'", ""), is(false));
    }

    @Test
    void testIsEscapedEmptyTokenEmptyDelimiterEmptyEscapeDelimiter() {
        assertThat(EDIUtil.isEscaped("", "", ""), is(false));
    }

    @Test
    void testIsEscapedNULLToken() {
        assertThat(EDIUtil.isEscaped(null, "'", "?"), is(false));
    }

    @Test
    void testIsEscapedNULLDelimiter() {
        String token = "bla bla bla 1234567890 ?'";
        assertThat(EDIUtil.isEscaped(token, null, "?"), is(false));
    }

    @Test
    void testIsEscapedNULLEscapeDelimiter() {
        String token = "bla bla bla 1234567890 ?'";
        assertThat(EDIUtil.isEscaped(token, "'", null), is(false));
    }

    @Test
    void testIsEscapedNULLTokenNULLDelimiterNULLEscapeDelimiter() {
        assertThat(EDIUtil.isEscaped(null, null, null), is(false));
    }
}
