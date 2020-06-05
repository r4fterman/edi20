package com.inubit.ibis.plugins.edi20.parsers.delimiters;

import com.inubit.ibis.plugins.edi20.delimiters.Delimiters;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

class VDADelimitersTest {

    @Test
    void testGetEscapeDelimiterUnknown() {
        final VDADelimiters delimiters = new VDADelimiters();

        assertThat(delimiters.getEscapeDelimiterIdentifier(), is(Delimiters.DELIMITER_UNKNOWN));
    }

    @Test
    void testGetSegmentDelimiter() {
        final VDADelimiters delimiters = new VDADelimiters();

        assertThat(delimiters.getDelimiter(VDADelimiters.DELIMITER_SEGMENT), is("\n"));
    }

    @Test
    void testGetDelimiterUnknown() {
        final VDADelimiters delimiters = new VDADelimiters();

        assertThat(delimiters.getDelimiter(Delimiters.DELIMITER_UNKNOWN), is(emptyString()));
    }

    @Test
    void testGetDelimiterZero() {
        final VDADelimiters delimiters = new VDADelimiters();

        assertThat(delimiters.getDelimiter(0), is(emptyString()));
    }

    @Test
    void testGetSegmentDelimiterIdentifier() {
        final VDADelimiters delimiters = new VDADelimiters();

        assertThat(delimiters.getDelimiterIdentifier("\n"), is(VDADelimiters.DELIMITER_SEGMENT));
    }

    @Test
    void testGetDelimiterIdentifierEmpty() {
        final VDADelimiters delimiters = new VDADelimiters();

        assertThat(delimiters.getDelimiterIdentifier(""), is(Delimiters.DELIMITER_UNKNOWN));
    }

    @Test
    void testGetDelimiterIdentifierNull() {
        final VDADelimiters delimiters = new VDADelimiters();

        assertThat(delimiters.getDelimiterIdentifier(null), is(Delimiters.DELIMITER_UNKNOWN));
    }

}
