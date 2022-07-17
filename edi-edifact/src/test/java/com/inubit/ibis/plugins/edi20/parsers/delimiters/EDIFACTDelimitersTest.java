package com.inubit.ibis.plugins.edi20.parsers.delimiters;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class EDIFACTDelimitersTest {

    @Test
    void testStandardEDIFACTDelimiters() {
        // set custom delimiter
        final String beginOfDocument = "UNA:+.? '";
        final EDIFACTDelimiters delimiters = new EDIFACTDelimiters(beginOfDocument);
        assertThat(delimiters.getComplexElementDelimiter(), is(":"));
        assertThat(delimiters.getDecimalDelimiter(), is("."));
        assertThat(delimiters.getElementDelimiter(), is("+"));
        assertThat(delimiters.getEscapeDelimiter(), is("?"));
        assertThat(delimiters.getSegmentDelimiter(), is("'"));
    }

    @Test
    void testEDIFACTDelimiters_SegmentIsSlash() {
        // set custom delimiter
        final String beginOfDocument = "UNA/',+ :";
        final EDIFACTDelimiters delimiters = new EDIFACTDelimiters(beginOfDocument);
        assertThat(delimiters.getComplexElementDelimiter(), is("/"));
        assertThat(delimiters.getDecimalDelimiter(), is(","));
        assertThat(delimiters.getElementDelimiter(), is("'"));
        assertThat(delimiters.getEscapeDelimiter(), is("+"));
        assertThat(delimiters.getSegmentDelimiter(), is(":"));
    }

    @Test
    void testEDIFACTDelimiters_SegmentIsDoubleDot() {
        // set default delimiter
        final String beginOfDocument = "UNB+UNOB:3+";
        final EDIFACTDelimiters delimiters = new EDIFACTDelimiters(beginOfDocument);
        assertThat(delimiters.getComplexElementDelimiter(), is(":"));
        assertThat(delimiters.getDecimalDelimiter(), is("."));
        assertThat(delimiters.getElementDelimiter(), is("+"));
        assertThat(delimiters.getEscapeDelimiter(), is("?"));
        assertThat(delimiters.getSegmentDelimiter(), is("'"));
    }

    @Test
    void testEmptyEDIFACTDelimiters() {
        final EDIFACTDelimiters delimiters = new EDIFACTDelimiters("");
        assertThat(delimiters.getComplexElementDelimiter(), is(":"));
        assertThat(delimiters.getDecimalDelimiter(), is("."));
        assertThat(delimiters.getElementDelimiter(), is("+"));
        assertThat(delimiters.getEscapeDelimiter(), is("?"));
        assertThat(delimiters.getSegmentDelimiter(), is("'"));
    }

    @Test
    void testNULLEDIFACTDelimiters() {
        final EDIFACTDelimiters delimiters = new EDIFACTDelimiters(null);
        assertThat(delimiters.getComplexElementDelimiter(), is(":"));
        assertThat(delimiters.getDecimalDelimiter(), is("."));
        assertThat(delimiters.getElementDelimiter(), is("+"));
        assertThat(delimiters.getEscapeDelimiter(), is("?"));
        assertThat(delimiters.getSegmentDelimiter(), is("'"));
    }

    @Test
    void testEDIFACTDelimiters_DefaultConstructor() {
        final EDIFACTDelimiters delimiters = new EDIFACTDelimiters();
        assertThat(delimiters.getComplexElementDelimiter(), is(":"));
        assertThat(delimiters.getDecimalDelimiter(), is("."));
        assertThat(delimiters.getElementDelimiter(), is("+"));
        assertThat(delimiters.getEscapeDelimiter(), is("?"));
        assertThat(delimiters.getSegmentDelimiter(), is("'"));
    }

    @Test
    void testDelimiters_ContainsTokenDoubleDot() {
        final EDIFACTDelimiters delimiters = new EDIFACTDelimiters();
        assertThat(delimiters.containsDelimiter(":"), is(true));
    }

    @Test
    void testDelimiters_ContainsTokenSlash() {
        final EDIFACTDelimiters delimiters = new EDIFACTDelimiters();
        assertThat(delimiters.containsDelimiter("/"), is(false));
    }

    @Test
    void testDelimiters_ContainsTokenUNB() {
        final EDIFACTDelimiters delimiters = new EDIFACTDelimiters();
        assertThat(delimiters.containsDelimiter("UNB"), is(false));
    }

    @Test
    void testDelimiters_ContainsTokenEmpty() {
        final EDIFACTDelimiters delimiters = new EDIFACTDelimiters();
        assertThat(delimiters.containsDelimiter(""), is(false));
    }

    @Test
    void testDelimiters_ContainsTokenNULL() {
        final EDIFACTDelimiters delimiters = new EDIFACTDelimiters();
        assertThat(delimiters.containsDelimiter(null), is(false));
    }

}
