package com.inubit.ibis.plugins.edi20.scanners;

import static junit.framework.Assert.*;

import java.io.File;
import java.net.URL;

import com.inubit.ibis.plugins.edi20.parsers.delimiters.VDADelimiters;
import com.inubit.ibis.utils.FileUtils;
import org.junit.Test;

/**
 * @author r4fter
 */
public class VDALexicalScannerTest {
    @Test
    public void testVDALexicalScanner() throws Exception {
        String testFile = "VDA4905_1.txt";
        URL url = VDALexicalScannerTest.class.getResource(testFile);
        assertNotNull("File not found: " + testFile, url);

        File edifactFile = new File(url.toURI());
        StringBuilder content = FileUtils.getContents(edifactFile);
        VDALexicalScanner scanner = new VDALexicalScanner(content, new VDADelimiters());
        assertTrue(scanner.hasMoreTokens());
    }

    @Test
    public void testVDALexicalScannerReadLength() throws Exception {
        String testFile = "VDA4905_1.txt";
        URL url = VDALexicalScannerTest.class.getResource(testFile);
        assertNotNull("File not found: " + testFile, url);

        File edifactFile = new File(url.toURI());
        StringBuilder content = FileUtils.getContents(edifactFile);
        VDALexicalScanner scanner = new VDALexicalScanner(content, new VDADelimiters());
        assertTrue(scanner.hasMoreTokens());

        StringBuilder builder = new StringBuilder("");
        while (scanner.hasMoreTokens()) {
            builder.append(scanner.nextToken().getToken());
        }
        long fileLength = edifactFile.length() + 1;
        assertEquals("Length differs!", fileLength, Long.parseLong("" + builder.length()));
    }

    @Test
    public void testVDALexicalScannerNoEscape() {
        String ediStr = "51101AG03     CKDVW    \n51201001000000002020610000000001020523\n51301 ";

        VDALexicalScanner scanner = new VDALexicalScanner(new StringBuilder(ediStr), new VDADelimiters());
        assertTrue(scanner.hasMoreTokens());
        assertEquals("51101AG03     CKDVW    ", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
        assertEquals("\n", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
        assertEquals("51201001000000002020610000000001020523", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
        assertEquals("\n", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
        assertEquals("51301 ", scanner.nextToken().getToken());
        assertFalse(scanner.hasMoreTokens());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testVDALexicalScannerEmptyDocEmptyDelim() {
        new VDALexicalScanner(new StringBuilder(""), new VDADelimiters());
        fail("Scanner should not be initialized. IllegalArgumentException should be thrown.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testVDALexicalScannerNullDocEmptyDelim() {
        new VDALexicalScanner(null, new VDADelimiters());
        fail("Scanner should not be initialized. IllegalArgumentException should be thrown.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testVDALexicalScannerNullDocNullDelim() {
        new VDALexicalScanner(null, null);
        fail("Scanner should not be initialized. IllegalArgumentException should be thrown.");
    }
}
