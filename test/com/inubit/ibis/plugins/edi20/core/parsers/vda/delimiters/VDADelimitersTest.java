package com.inubit.ibis.plugins.edi20.core.parsers.vda.delimiters;

import static org.junit.Assert.*;

import org.junit.Test;

import com.inubit.ibis.plugins.edi20.commons.delimiters.IDelimiters;

/**
 * @author r4fter
 */
public class VDADelimitersTest {

    @Test
    public void testGetEscapeDelimiterUnknown() {
        VDADelimiters delimiters = new VDADelimiters();

        assertEquals(IDelimiters.DELIMITER_UNKNOWN, delimiters.getEscapeDelimiterIndentifier());
    }

    @Test
    public void testGetSegmentDelimiter() {
        VDADelimiters delimiters = new VDADelimiters();

        assertEquals("\n", delimiters.getDelimiter(VDADelimiters.DELIMITER_SEGMENT));
    }

    @Test
    public void testGetDelimiterUnknown() {
        VDADelimiters delimiters = new VDADelimiters();

        assertEquals("", delimiters.getDelimiter(IDelimiters.DELIMITER_UNKNOWN));
    }

    @Test
    public void testGetDelimiterZero() {
        VDADelimiters delimiters = new VDADelimiters();

        assertEquals("", delimiters.getDelimiter(0));
    }

    @Test
    public void testGetSegmentDelimiterIdentifier() {
        VDADelimiters delimiters = new VDADelimiters();

        assertEquals(VDADelimiters.DELIMITER_SEGMENT, delimiters.getDelimiterIdentifier("\n"));
    }

    @Test
    public void testGetDelimiterIdentifierEmpty() {
        VDADelimiters delimiters = new VDADelimiters();

        assertEquals(IDelimiters.DELIMITER_UNKNOWN, delimiters.getDelimiterIdentifier(""));
    }

    @Test
    public void testGetDelimiterIdentifierNull() {
        VDADelimiters delimiters = new VDADelimiters();

        assertEquals(IDelimiters.DELIMITER_UNKNOWN, delimiters.getDelimiterIdentifier(null));
    }

}
