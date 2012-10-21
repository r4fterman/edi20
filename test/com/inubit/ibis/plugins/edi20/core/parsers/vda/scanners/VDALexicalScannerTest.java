package com.inubit.ibis.plugins.edi20.core.parsers.vda.scanners;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.Test;

import com.inubit.ibis.plugins.edi20.core.parsers.vda.delimiters.VDADelimiters;
import com.inubit.ibis.utils.FileUtils;

/**
 * @author r4fter
 */
public class VDALexicalScannerTest {

    public void testVDALexicalScanner() {
        try {
            String testFile = "VDA4905_1.txt";
            URL url = VDALexicalScannerTest.class.getResource(testFile);
            assertNotNull("File not found: " + testFile, url);

            File edifactFile = new File(url.toURI());
            StringBuffer content = FileUtils.getContents(edifactFile);
            VDALexicalScanner scanner = new VDALexicalScanner(content, new VDADelimiters());
            assertTrue(scanner.hasMoreTokens());
        } catch (IOException e) {
            fail(e.getMessage());
        } catch (URISyntaxException e) {
            fail(e.getMessage());
        }
    }

    public void testVDALexicalScannerReadLength() {
        try {
            String testFile = "VDA4905_1.txt";
            URL url = VDALexicalScannerTest.class.getResource(testFile);
            assertNotNull("File not found: " + testFile, url);

            File edifactFile = new File(url.toURI());
            StringBuffer content = FileUtils.getContents(edifactFile);
            VDALexicalScanner scanner = new VDALexicalScanner(content, new VDADelimiters());
            assertTrue(scanner.hasMoreTokens());

            StringBuilder builder = new StringBuilder("");
            while (scanner.hasMoreTokens()) {
                builder.append(scanner.nextToken().getToken());
            }
            long fileLength = edifactFile.length() + 1;
            assertEquals("Length differs!", fileLength, Long.parseLong("" + builder.length()));
        } catch (IOException e) {
            fail(e.getMessage());
        } catch (URISyntaxException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testVDALexicalScannerNoEscape() {
        String ediStr = "51101AG03     CKDVW    \n51201001000000002020610000000001020523\n51301 ";

        VDALexicalScanner scanner = new VDALexicalScanner(new StringBuffer(ediStr), new VDADelimiters());
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

    public void testVDALexicalScannerEmptyDocEmptyDelim() {
        try {
            new VDALexicalScanner(new StringBuffer(""), new VDADelimiters());
            fail("Scanner should not be initialized. IllegalArgumentException should be thrown.");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    public void testVDALexicalScannerNullDocEmptyDelim() {
        try {
            new VDALexicalScanner(null, new VDADelimiters());
            fail("Scanner should not be initialized. IllegalArgumentException should be thrown.");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    public void testVDALexicalScannerNullDocNullDelim() {
        try {
            new VDALexicalScanner(null, null);
            fail("Scanner should not be initialized. IllegalArgumentException should be thrown.");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }
}
