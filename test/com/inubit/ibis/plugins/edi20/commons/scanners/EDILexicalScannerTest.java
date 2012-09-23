package com.inubit.ibis.plugins.edi20.commons.scanners;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import org.junit.Test;

import com.inubit.ibis.plugins.edi20.commons.delimiters.IDelimiters;
import com.inubit.ibis.plugins.edi20.core.parsers.edifact.delimiters.EDIFACTDelimiters;
import com.inubit.ibis.plugins.edi20.core.parsers.edifact.scanners.ComplexElementDelimiterToken;
import com.inubit.ibis.plugins.edi20.core.parsers.edifact.scanners.EDIFACTLexicalScanner;
import com.inubit.ibis.plugins.edi20.core.parsers.edifact.scanners.ElementDelimiterToken;
import com.inubit.ibis.plugins.edi20.core.parsers.edifact.scanners.SegmentDelimiterToken;

/**
 * @author r4fter
 */
public class EDILexicalScannerTest {

    @Test
    public void testGetNextIndexOfDelimiterEDIFACTElementStartsWithEscape() {
        String ediStr = "?+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";
        EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuffer(ediStr), new EDIFACTDelimiters());
        assertEquals(8, scanner.getIndexOfDelimiter(0, "+"));
    }

    @Test
    public void testGetNextIndexOfDelimiterEDIFACTSegment() {
        String ediStr = "UNB+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";
        EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuffer(ediStr), new EDIFACTDelimiters());
        assertEquals(40, scanner.getIndexOfDelimiter(0, "'"));
    }

    @Test
    public void testGetNextIndexOfDelimiterEDIFACTElement() {
        String ediStr = "UNB+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";
        EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuffer(ediStr), new EDIFACTDelimiters());
        assertEquals(3, scanner.getIndexOfDelimiter(0, "+"));
    }

    @Test
    public void testGetNextIndexOfDelimiterEDIFACTElement4Escapes() {
        String ediStr = "UNB?+UNOB:3?+RUDOLF0?+ELIX000?+011015:1628+1'";
        EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuffer(ediStr), new EDIFACTDelimiters());
        assertEquals(42, scanner.getIndexOfDelimiter(0, "+"));
    }

    @Test
    public void testGetNextIndexOfDelimiterEDIFACTElement1Escapes() {
        String ediStr = "UNB?+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";
        EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuffer(ediStr), new EDIFACTDelimiters());
        assertEquals(11, scanner.getIndexOfDelimiter(0, "+"));
    }

    @Test
    public void testGetNextIndexOfDelimiterEDIFACTComplexElement() {
        String ediStr = "UNB+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";
        EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuffer(ediStr), new EDIFACTDelimiters());
        assertEquals(8, scanner.getIndexOfDelimiter(0, ":"));
    }

    @Test
    public void testScannerTokens() {
        String ediStr = "UNB+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";
        EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuffer(ediStr), new EDIFACTDelimiters());

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
