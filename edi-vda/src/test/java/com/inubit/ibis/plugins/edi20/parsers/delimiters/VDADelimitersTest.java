package com.inubit.ibis.plugins.edi20.parsers.delimiters;

import com.inubit.ibis.plugins.edi20.delimiters.IDelimiters;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

/**
 * @author r4fter
 */
public class VDADelimitersTest {

    @Test
    public void testGetEscapeDelimiterUnknown() {
        VDADelimiters delimiters = new VDADelimiters();

        assertThat(delimiters.getEscapeDelimiterIdentifier(), is(IDelimiters.DELIMITER_UNKNOWN));
    }

    @Test
    public void testGetSegmentDelimiter() {
        VDADelimiters delimiters = new VDADelimiters();

        assertThat(delimiters.getDelimiter(VDADelimiters.DELIMITER_SEGMENT), is("\n"));
    }

    @Test
    public void testGetDelimiterUnknown() {
        VDADelimiters delimiters = new VDADelimiters();

        assertThat(delimiters.getDelimiter(IDelimiters.DELIMITER_UNKNOWN), is(emptyString()));
    }

    @Test
    public void testGetDelimiterZero() {
        VDADelimiters delimiters = new VDADelimiters();

        assertThat(delimiters.getDelimiter(0), is(emptyString()));
    }

    @Test
    public void testGetSegmentDelimiterIdentifier() {
        VDADelimiters delimiters = new VDADelimiters();

        assertThat(delimiters.getDelimiterIdentifier("\n"), is(VDADelimiters.DELIMITER_SEGMENT));
    }

    @Test
    public void testGetDelimiterIdentifierEmpty() {
        VDADelimiters delimiters = new VDADelimiters();

        assertThat(delimiters.getDelimiterIdentifier(""), is(IDelimiters.DELIMITER_UNKNOWN));
    }

    @Test
    public void testGetDelimiterIdentifierNull() {
        VDADelimiters delimiters = new VDADelimiters();

        assertThat(delimiters.getDelimiterIdentifier(null), is(IDelimiters.DELIMITER_UNKNOWN));
    }

}
