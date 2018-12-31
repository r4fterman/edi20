package com.inubit.ibis.plugins.edi20.scanners;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.Ignore;
import org.junit.Test;

import com.inubit.ibis.plugins.edi20.parsers.delimiters.DATANORMDelimiters;
import com.inubit.ibis.utils.FileUtils;

/**
 * @author r4fter
 */
public class DATANORMLexicalScannerTest {

    @Test
    public void testDATANORMLexicalScanner() throws Exception {
        String fileName = "datanorm_message.txt";
        StringBuilder content = getContent(fileName);

        DATANORMLexicalScanner scanner = new DATANORMLexicalScanner(content, new DATANORMDelimiters());
        assertTrue(scanner.hasMoreTokens());
    }

    @Ignore
    public void testDATANORMLexicalScannerReadLength() throws Exception {
        String fileName = "datanorm_message.txt";
        StringBuilder content = getContent(fileName);

        DATANORMLexicalScanner scanner = new DATANORMLexicalScanner(content, new DATANORMDelimiters());
        assertTrue(scanner.hasMoreTokens());

        StringBuilder builder = new StringBuilder("");
        while (scanner.hasMoreTokens()) {
            builder.append(scanner.nextToken().getToken());
        }
        long fileLength = getFile(fileName).length();
        assertEquals("Length differs!", fileLength, Long.parseLong(String.valueOf(builder.length())));
    }

    @Test
    public void testDATANORMLexicalScannerNoEscape() {
        String ediStr = "C;N;BAU;F;MKF;ABK;0563E.341200000;\n;030101;;;;1S;;;E;25000;62;33;\n;;ST;;;;FZ-DEUT.;563-3412~;;";

        DATANORMLexicalScanner scanner = new DATANORMLexicalScanner(new StringBuilder(ediStr), new DATANORMDelimiters());
        assertTrue(scanner.hasMoreTokens());

        IToken token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMUnknownDelimiterToken.class));
        assertEquals("C", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMElementDelimiterToken.class));
        assertEquals(";", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMUnknownDelimiterToken.class));
        assertEquals("N", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMElementDelimiterToken.class));
        assertEquals(";", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMUnknownDelimiterToken.class));
        assertEquals("BAU", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMElementDelimiterToken.class));
        assertEquals(";", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMUnknownDelimiterToken.class));
        assertEquals("F", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMElementDelimiterToken.class));
        assertEquals(";", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMUnknownDelimiterToken.class));
        assertEquals("MKF", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMElementDelimiterToken.class));
        assertEquals(";", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMUnknownDelimiterToken.class));
        assertEquals("ABK", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMElementDelimiterToken.class));
        assertEquals(";", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMUnknownDelimiterToken.class));
        assertEquals("0563E.341200000", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMElementDelimiterToken.class));
        assertEquals(";", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMSegmentDelimiterToken.class));
        assertEquals("\n", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMElementDelimiterToken.class));
        assertEquals(";", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMUnknownDelimiterToken.class));
        assertEquals("030101", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMElementDelimiterToken.class));
        assertEquals(";", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMElementDelimiterToken.class));
        assertEquals(";", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMElementDelimiterToken.class));
        assertEquals(";", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMElementDelimiterToken.class));
        assertEquals(";", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMUnknownDelimiterToken.class));
        assertEquals("1S", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMElementDelimiterToken.class));
        assertEquals(";", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMElementDelimiterToken.class));
        assertEquals(";", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMElementDelimiterToken.class));
        assertEquals(";", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMUnknownDelimiterToken.class));
        assertEquals("E", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMElementDelimiterToken.class));
        assertEquals(";", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMUnknownDelimiterToken.class));
        assertEquals("25000", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMElementDelimiterToken.class));
        assertEquals(";", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMUnknownDelimiterToken.class));
        assertEquals("62", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMElementDelimiterToken.class));
        assertEquals(";", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMUnknownDelimiterToken.class));
        assertEquals("33", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMElementDelimiterToken.class));
        assertEquals(";", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMSegmentDelimiterToken.class));
        assertEquals("\n", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMElementDelimiterToken.class));
        assertEquals(";", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMElementDelimiterToken.class));
        assertEquals(";", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMUnknownDelimiterToken.class));
        assertEquals("ST", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMElementDelimiterToken.class));
        assertEquals(";", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMElementDelimiterToken.class));
        assertEquals(";", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMElementDelimiterToken.class));
        assertEquals(";", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMElementDelimiterToken.class));
        assertEquals(";", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMUnknownDelimiterToken.class));
        assertEquals("FZ-DEUT.", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMElementDelimiterToken.class));
        assertEquals(";", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMUnknownDelimiterToken.class));
        assertEquals("563-3412~", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMElementDelimiterToken.class));
        assertEquals(";", token.getToken());
        assertTrue(scanner.hasMoreTokens());

        token = scanner.nextToken();
        assertTrue(token.getClass().equals(DATANORMElementDelimiterToken.class));
        assertEquals(";", token.getToken());

        assertFalse(scanner.hasMoreTokens());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testDATANORMLexicalScannerEmptyDocEmptyDelim() {
        new DATANORMLexicalScanner(new StringBuilder(""), new DATANORMDelimiters());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDATANORMLexicalScannerNullDocEmptyDelim() {
        new DATANORMLexicalScanner(null, new DATANORMDelimiters());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLexicalScannerNullDocNullDelim() {
        new DATANORMLexicalScanner(null, null);
    }

    private StringBuilder getContent(String fileName) throws IOException, URISyntaxException {
        File file = getFile(fileName);
        return FileUtils.getContents(file);
    }

    private File getFile(String fileName) throws URISyntaxException {
        URL url = DATANORMLexicalScannerTest.class.getResource(fileName);
        assertNotNull("File not found: " + fileName, url);
        return new File(url.toURI());
    }
}
