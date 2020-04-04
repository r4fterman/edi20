package com.inubit.ibis.plugins.edi20.parsers.delimiters;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

/**
 * @author r4fter
 */
class EDIFACTDelimitersTest {

    @Test
    void testStandardEDIFACTDelimiters() {
        // set custom delimiter
        String beginOfDocument = "UNA:+.? '";
        EDIFACTDelimiters delimiters = new EDIFACTDelimiters(beginOfDocument);
        assertThat(delimiters.getComplexElementDelimiter(), is(":"));
        assertThat(delimiters.getDecimalDelimiter(), is("."));
        assertThat(delimiters.getElementDelimiter(), is("+"));
        assertThat(delimiters.getEscapeDelimiter(), is("?"));
        assertThat(delimiters.getSegmentDelimiter(), is("'"));
    }

    @Test
    void testEDIFACTDelimiters_SegmentIsSlash() {
        // set custom delimiter
        String beginOfDocument = "UNA/',+ :";
        EDIFACTDelimiters delimiters = new EDIFACTDelimiters(beginOfDocument);
        assertThat(delimiters.getComplexElementDelimiter(), is("/"));
        assertThat(delimiters.getDecimalDelimiter(), is(","));
        assertThat(delimiters.getElementDelimiter(), is("'"));
        assertThat(delimiters.getEscapeDelimiter(), is("+"));
        assertThat(delimiters.getSegmentDelimiter(), is(":"));
    }

    @Test
    void testEDIFACTDelimiters_SegmentIsDoubleDot() {
        // set default delimiter
        String beginOfDocument = "UNB+UNOB:3+";
        EDIFACTDelimiters delimiters = new EDIFACTDelimiters(beginOfDocument);
        assertThat(delimiters.getComplexElementDelimiter(), is(":"));
        assertThat(delimiters.getDecimalDelimiter(), is("."));
        assertThat(delimiters.getElementDelimiter(), is("+"));
        assertThat(delimiters.getEscapeDelimiter(), is("?"));
        assertThat(delimiters.getSegmentDelimiter(), is("'"));
    }

    @Test
    void testEmptyEDIFACTDelimiters() {
        EDIFACTDelimiters delimiters = new EDIFACTDelimiters("");
        assertThat(delimiters.getComplexElementDelimiter(), is(":"));
        assertThat(delimiters.getDecimalDelimiter(), is("."));
        assertThat(delimiters.getElementDelimiter(), is("+"));
        assertThat(delimiters.getEscapeDelimiter(), is("?"));
        assertThat(delimiters.getSegmentDelimiter(), is("'"));
    }

    @Test
    void testNULLEDIFACTDelimiters() {
        EDIFACTDelimiters delimiters = new EDIFACTDelimiters(null);
        assertThat(delimiters.getComplexElementDelimiter(), is(":"));
        assertThat(delimiters.getDecimalDelimiter(), is("."));
        assertThat(delimiters.getElementDelimiter(), is("+"));
        assertThat(delimiters.getEscapeDelimiter(), is("?"));
        assertThat(delimiters.getSegmentDelimiter(), is("'"));
    }

    @Test
    void testEDIFACTDelimiters_DefaultConstructor() {
        EDIFACTDelimiters delimiters = new EDIFACTDelimiters();
        assertThat(delimiters.getComplexElementDelimiter(), is(":"));
        assertThat(delimiters.getDecimalDelimiter(), is("."));
        assertThat(delimiters.getElementDelimiter(), is("+"));
        assertThat(delimiters.getEscapeDelimiter(), is("?"));
        assertThat(delimiters.getSegmentDelimiter(), is("'"));
    }

    @Test
    void testDelimiters_ContainsTokenDoubleDot() {
        EDIFACTDelimiters delimiters = new EDIFACTDelimiters();
        assertThat(delimiters.containsDelimiter(":"), is(true));
    }

    @Test
    void testDelimiters_ContainsTokenSlash() {
        EDIFACTDelimiters delimiters = new EDIFACTDelimiters();
        assertThat(delimiters.containsDelimiter("/"), is(false));
    }

    @Test
    void testDelimiters_ContainsTokenUNB() {
        EDIFACTDelimiters delimiters = new EDIFACTDelimiters();
        assertThat(delimiters.containsDelimiter("UNB"), is(false));
    }

    @Test
    void testDelimiters_ContainsTokenEmpty() {
        EDIFACTDelimiters delimiters = new EDIFACTDelimiters();
        assertThat(delimiters.containsDelimiter(""), is(false));
    }

    @Test
    void testDelimiters_ContainsTokenNULL() {
        EDIFACTDelimiters delimiters = new EDIFACTDelimiters();
        assertThat(delimiters.containsDelimiter(null), is(false));
    }

}
