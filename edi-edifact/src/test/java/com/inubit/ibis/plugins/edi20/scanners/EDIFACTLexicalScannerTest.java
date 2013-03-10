package com.inubit.ibis.plugins.edi20.scanners;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import com.inubit.ibis.plugins.edi20.parsers.delimiters.EDIFACTDelimiters;
import com.inubit.ibis.utils.FileUtils;
import org.junit.Test;

/**
 * @author rafter
 */
public class EDIFACTLexicalScannerTest {

    @Test
    public void testEDILexicalScanner() throws Exception {
        String testFile = "EDIFACT-ifcsum-1.txt";
        File edifactFile = getFile(testFile);
        StringBuilder content = FileUtils.getContents(edifactFile);

        EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(content, new EDIFACTDelimiters());
        assertTrue(scanner.hasMoreTokens());
    }

    @Test
    public void testEDILexicalScannerReadLength() throws Exception {
        String testFile = "EDIFACT-ifcsum-1.txt";
        File edifactFile = getFile(testFile);
        StringBuilder content = FileUtils.getContents(edifactFile);

        EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(content, new EDIFACTDelimiters());
        assertTrue(scanner.hasMoreTokens());

        StringBuilder builder = new StringBuilder("");
        while (scanner.hasMoreTokens()) {
            builder.append(scanner.nextToken().getToken());
        }
        long fileLength = edifactFile.length() + 1;
        assertEquals("Length differs!", fileLength, Long.parseLong(String.valueOf(builder.length()), 0));
    }

    @Test
    public void testEDILexicalScannerNoEscape() {
        String ediStr = "UNB+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";

        EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuilder(ediStr), new EDIFACTDelimiters());
        assertTrue(scanner.hasMoreTokens());
        assertEquals("UNB", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
        assertEquals("+", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
        assertEquals("UNOB", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
        assertEquals(":", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
        assertEquals("3", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
        assertEquals("+", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
        assertEquals("RUDOLF0", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
        assertEquals("+", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
        assertEquals("ELIX000", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
        assertEquals("+", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
        assertEquals("011015", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
        assertEquals(":", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
        assertEquals("1628", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
        assertEquals("+", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
        assertEquals("1", scanner.nextToken().getToken());
        assertTrue(scanner.hasMoreTokens());
        assertEquals("'", scanner.nextToken().getToken());
        assertFalse(scanner.hasMoreTokens());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEDILexicalScannerEmptyDocEmptyDelim() {
        new EDIFACTLexicalScanner(new StringBuilder(""), new EDIFACTDelimiters());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEDILexicalScannerNullDocEmptyDelim() {
        new EDIFACTLexicalScanner(null, new EDIFACTDelimiters());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEDILexicalScannerNullDocNullDelim() {
        new EDIFACTLexicalScanner(null, null);
    }

    private File getFile(String testFile) throws URISyntaxException, IOException {
        URL url = EDIFACTLexicalScannerTest.class.getResource(testFile);
        assertNotNull("File not found: " + testFile, url);
        return new File(url.toURI());
    }

    @Test
    public void testGetNextIndexOfDelimiterEDIFACTElementStartsWithEscape() {
        String ediStr = "?+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";
        EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuilder(ediStr), new EDIFACTDelimiters());
        assertEquals(8, scanner.getIndexOfDelimiter(0, "+"));
    }

    @Test
    public void testGetNextIndexOfDelimiterEDIFACTSegment() {
        String ediStr = "UNB+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";
        EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuilder(ediStr), new EDIFACTDelimiters());
        assertEquals(40, scanner.getIndexOfDelimiter(0, "'"));
    }

    @Test
    public void testGetNextIndexOfDelimiterEDIFACTElement() {
        String ediStr = "UNB+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";
        EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuilder(ediStr), new EDIFACTDelimiters());
        assertEquals(3, scanner.getIndexOfDelimiter(0, "+"));
    }

    @Test
    public void testGetNextIndexOfDelimiterEDIFACTElement4Escapes() {
        String ediStr = "UNB?+UNOB:3?+RUDOLF0?+ELIX000?+011015:1628+1'";
        EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuilder(ediStr), new EDIFACTDelimiters());
        assertEquals(42, scanner.getIndexOfDelimiter(0, "+"));
    }

    @Test
    public void testGetNextIndexOfDelimiterEDIFACTElement1Escapes() {
        String ediStr = "UNB?+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";
        EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuilder(ediStr), new EDIFACTDelimiters());
        assertEquals(11, scanner.getIndexOfDelimiter(0, "+"));
    }

    @Test
    public void testGetNextIndexOfDelimiterEDIFACTComplexElement() {
        String ediStr = "UNB+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";
        EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuilder(ediStr), new EDIFACTDelimiters());
        assertEquals(8, scanner.getIndexOfDelimiter(0, ":"));
    }

    @Test
    public void testScannerTokens() {
        String ediStr = "UNB+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";
        EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuilder(ediStr), new EDIFACTDelimiters());

        IToken token = scanner.nextToken();
        assertNotNull("Token is null!", token);
        assertTrue(token instanceof UnknownDelimiterToken);
        assertEquals(0, token.getPosition());
        assertEquals("UNB", token.getToken());

        token = scanner.nextToken();
        assertNotNull("Token is null!", token);
        assertTrue(token instanceof ElementDelimiterToken);
        assertEquals(3, token.getPosition());
        assertEquals("+", token.getToken());

        token = scanner.nextToken();
        assertNotNull("Token is null!", token);
        assertTrue(token instanceof UnknownDelimiterToken);
        assertEquals(4, token.getPosition());
        assertEquals("UNOB", token.getToken());

        token = scanner.nextToken();
        assertTrue(token instanceof ComplexElementDelimiterToken);
        assertEquals(8, token.getPosition());
        assertEquals(":", token.getToken());

        token = scanner.nextToken();
        assertNotNull("Token is null!", token);
        assertTrue(token instanceof UnknownDelimiterToken);
        assertEquals(9, token.getPosition());
        assertEquals("3", token.getToken());

        token = scanner.nextToken();
        assertNotNull("Token is null!", token);
        assertTrue(token instanceof ElementDelimiterToken);
        assertEquals(10, token.getPosition());
        assertEquals("+", token.getToken());

        token = scanner.nextToken();
        assertNotNull("Token is null!", token);
        assertTrue(token instanceof UnknownDelimiterToken);
        assertEquals(11, token.getPosition());
        assertEquals("RUDOLF0", token.getToken());

        token = scanner.nextToken();
        assertNotNull("Token is null!", token);
        assertTrue(token instanceof ElementDelimiterToken);
        assertEquals(18, token.getPosition());
        assertEquals("+", token.getToken());

        token = scanner.nextToken();
        assertNotNull("Token is null!", token);
        assertTrue(token instanceof UnknownDelimiterToken);
        assertEquals(19, token.getPosition());
        assertEquals("ELIX000", token.getToken());

        token = scanner.nextToken();
        assertNotNull("Token is null!", token);
        assertTrue(token instanceof ElementDelimiterToken);
        assertEquals(26, token.getPosition());
        assertEquals("+", token.getToken());

        token = scanner.nextToken();
        assertNotNull("Token is null!", token);
        assertTrue(token instanceof UnknownDelimiterToken);
        assertEquals(27, token.getPosition());
        assertEquals("011015", token.getToken());

        token = scanner.nextToken();
        assertNotNull("Token is null!", token);
        assertTrue(token instanceof ComplexElementDelimiterToken);
        assertEquals(33, token.getPosition());
        assertEquals(":", token.getToken());

        token = scanner.nextToken();
        assertNotNull("Token is null!", token);
        assertTrue(token instanceof UnknownDelimiterToken);
        assertEquals(34, token.getPosition());
        assertEquals("1628", token.getToken());

        token = scanner.nextToken();
        assertNotNull("Token is null!", token);
        assertTrue(token instanceof ElementDelimiterToken);
        assertEquals(38, token.getPosition());
        assertEquals("+", token.getToken());

        token = scanner.nextToken();
        assertNotNull("Token is null!", token);
        assertTrue(token instanceof UnknownDelimiterToken);
        assertEquals(39, token.getPosition());
        assertEquals("1", token.getToken());

        token = scanner.nextToken();
        assertNotNull("Token is null!", token);
        assertTrue(token instanceof SegmentDelimiterToken);
        assertEquals(40, token.getPosition());
        assertEquals("'", token.getToken());
    }
}
