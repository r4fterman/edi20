package com.inubit.ibis.plugins.edi20.scanners;

import com.inubit.ibis.plugins.edi20.parsers.delimiters.EDIFACTDelimiters;
import com.inubit.ibis.utils.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author rafter
 */
class EDIFACTLexicalScannerTest {

    @Test
    void testEDILexicalScanner() throws Exception {
        String testFile = "EDIFACT-ifcsum-1.txt";
        File edifactFile = getFile(testFile);
        StringBuilder content = FileUtils.getContents(edifactFile);

        EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(content, new EDIFACTDelimiters());
        assertThat(scanner.hasMoreTokens(), is(true));
    }

    @Test
    void testEDILexicalScannerReadLength() throws Exception {
        String testFile = "EDIFACT-ifcsum-1.txt";
        File edifactFile = getFile(testFile);
        StringBuilder content = FileUtils.getContents(edifactFile);

        EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(content, new EDIFACTDelimiters());
        assertThat(scanner.hasMoreTokens(), is(true));

        StringBuilder builder = new StringBuilder();
        while (scanner.hasMoreTokens()) {
            builder.append(scanner.nextToken().getToken());
        }

        int scannerLength = builder.toString().length();
        int contentLength = content.length();
        assertThat("Content differs!" + contentLength + "!=" + scannerLength, scannerLength, is(contentLength));
    }

    @Test
    void testEDILexicalScannerNoEscape() {
        String ediStr = "UNB+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";

        EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuilder(ediStr), new EDIFACTDelimiters());
        assertThat(scanner.hasMoreTokens(), is(true));
        assertThat(scanner.nextToken().getToken(), is("UNB"));
        assertThat(scanner.hasMoreTokens(), is(true));
        assertThat(scanner.nextToken().getToken(), is("+"));
        assertThat(scanner.hasMoreTokens(), is(true));
        assertThat(scanner.nextToken().getToken(), is("UNOB"));
        assertThat(scanner.hasMoreTokens(), is(true));
        assertThat(scanner.nextToken().getToken(), is(":"));
        assertThat(scanner.hasMoreTokens(), is(true));
        assertThat(scanner.nextToken().getToken(), is("3"));
        assertThat(scanner.hasMoreTokens(), is(true));
        assertThat(scanner.nextToken().getToken(), is("+"));
        assertThat(scanner.hasMoreTokens(), is(true));
        assertThat(scanner.nextToken().getToken(), is("RUDOLF0"));
        assertThat(scanner.hasMoreTokens(), is(true));
        assertThat(scanner.nextToken().getToken(), is("+"));
        assertThat(scanner.hasMoreTokens(), is(true));
        assertThat(scanner.nextToken().getToken(), is("ELIX000"));
        assertThat(scanner.hasMoreTokens(), is(true));
        assertThat(scanner.nextToken().getToken(), is("+"));
        assertThat(scanner.hasMoreTokens(), is(true));
        assertThat(scanner.nextToken().getToken(), is("011015"));
        assertThat(scanner.hasMoreTokens(), is(true));
        assertThat(scanner.nextToken().getToken(), is(":"));
        assertThat(scanner.hasMoreTokens(), is(true));
        assertThat(scanner.nextToken().getToken(), is("1628"));
        assertThat(scanner.hasMoreTokens(), is(true));
        assertThat(scanner.nextToken().getToken(), is("+"));
        assertThat(scanner.hasMoreTokens(), is(true));
        assertThat(scanner.nextToken().getToken(), is("1"));
        assertThat(scanner.hasMoreTokens(), is(true));
        assertThat(scanner.nextToken().getToken(), is("'"));
        assertThat(scanner.hasMoreTokens(), is(false));
    }

    @Test
    void testEDILexicalScannerEmptyDocEmptyDelim() {
        assertThrows(IllegalArgumentException.class, () -> new EDIFACTLexicalScanner(new StringBuilder(), new EDIFACTDelimiters()));
    }

    @Test
    void testEDILexicalScannerNullDocEmptyDelim() {
        assertThrows(IllegalArgumentException.class, () -> new EDIFACTLexicalScanner(null, new EDIFACTDelimiters()));
    }

    @Test
    void testEDILexicalScannerNullDocNullDelim() {
        assertThrows(IllegalArgumentException.class, () -> new EDIFACTLexicalScanner(null, null));
    }

    private File getFile(final String testFile) throws URISyntaxException {
        URL url = EDIFACTLexicalScannerTest.class.getResource(testFile);
        assertThat("File not found: " + testFile, url, not(nullValue()));
        return new File(url.toURI());
    }

    @Test
    void testGetNextIndexOfDelimiterEDIFACTElementStartsWithEscape() {
        String ediStr = "?+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";
        EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuilder(ediStr), new EDIFACTDelimiters());
        assertThat(scanner.getIndexOfDelimiter(0, "+"), is(8));
    }

    @Test
    void testGetNextIndexOfDelimiterEDIFACTSegment() {
        String ediStr = "UNB+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";
        EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuilder(ediStr), new EDIFACTDelimiters());
        assertThat(scanner.getIndexOfDelimiter(0, "'"), is(40));
    }

    @Test
    void testGetNextIndexOfDelimiterEDIFACTElement() {
        String ediStr = "UNB+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";
        EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuilder(ediStr), new EDIFACTDelimiters());
        assertThat(scanner.getIndexOfDelimiter(0, "+"), is(3));
    }

    @Test
    void testGetNextIndexOfDelimiterEDIFACTElement4Escapes() {
        String ediStr = "UNB?+UNOB:3?+RUDOLF0?+ELIX000?+011015:1628+1'";
        EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuilder(ediStr), new EDIFACTDelimiters());
        assertThat(scanner.getIndexOfDelimiter(0, "+"), is(42));
    }

    @Test
    void testGetNextIndexOfDelimiterEDIFACTElement1Escapes() {
        String ediStr = "UNB?+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";
        EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuilder(ediStr), new EDIFACTDelimiters());
        assertThat(scanner.getIndexOfDelimiter(0, "+"), is(11));
    }

    @Test
    void testGetNextIndexOfDelimiterEDIFACTComplexElement() {
        String ediStr = "UNB+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";
        EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuilder(ediStr), new EDIFACTDelimiters());
        assertThat(scanner.getIndexOfDelimiter(0, ":"), is(8));
    }

    @Test
    void testScannerTokens() {
        String ediStr = "UNB+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";
        EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuilder(ediStr), new EDIFACTDelimiters());

        IToken token = scanner.nextToken();
        assertThat("Token is null!", token, not(nullValue()));
        assertThat(token, instanceOf(UnknownDelimiterToken.class));
        assertThat(token.getPosition(), is(0));
        assertThat(token.getToken(), is("UNB"));

        token = scanner.nextToken();
        assertThat("Token is null!", token, not(nullValue()));
        assertThat(token, instanceOf(ElementDelimiterToken.class));
        assertThat(token.getPosition(), is(3));
        assertThat(token.getToken(), is("+"));

        token = scanner.nextToken();
        assertThat("Token is null!", token, not(nullValue()));
        assertThat(token, instanceOf(UnknownDelimiterToken.class));
        assertThat(token.getPosition(), is(4));
        assertThat(token.getToken(), is("UNOB"));

        token = scanner.nextToken();
        assertThat(token, instanceOf(ComplexElementDelimiterToken.class));
        assertThat(token.getPosition(), is(8));
        assertThat(token.getToken(), is(":"));

        token = scanner.nextToken();
        assertThat("Token is null!", token, not(nullValue()));
        assertThat(token, instanceOf(UnknownDelimiterToken.class));
        assertThat(token.getPosition(), is(9));
        assertThat(token.getToken(), is("3"));

        token = scanner.nextToken();
        assertThat("Token is null!", token, not(nullValue()));
        assertThat(token, instanceOf(ElementDelimiterToken.class));
        assertThat(token.getPosition(), is(10));
        assertThat(token.getToken(), is("+"));

        token = scanner.nextToken();
        assertThat("Token is null!", token, not(nullValue()));
        assertThat(token, instanceOf(UnknownDelimiterToken.class));
        assertThat(token.getPosition(), is(11));
        assertThat(token.getToken(), is("RUDOLF0"));

        token = scanner.nextToken();
        assertThat("Token is null!", token, not(nullValue()));
        assertThat(token, instanceOf(ElementDelimiterToken.class));
        assertThat(token.getPosition(), is(18));
        assertThat(token.getToken(), is("+"));

        token = scanner.nextToken();
        assertThat("Token is null!", token, not(nullValue()));
        assertThat(token, instanceOf(UnknownDelimiterToken.class));
        assertThat(token.getPosition(), is(19));
        assertThat(token.getToken(), is("ELIX000"));

        token = scanner.nextToken();
        assertThat("Token is null!", token, not(nullValue()));
        assertThat(token, instanceOf(ElementDelimiterToken.class));
        assertThat(token.getPosition(), is(26));
        assertThat(token.getToken(), is("+"));

        token = scanner.nextToken();
        assertThat("Token is null!", token, not(nullValue()));
        assertThat(token, instanceOf(UnknownDelimiterToken.class));
        assertThat(token.getPosition(), is(27));
        assertThat(token.getToken(), is("011015"));

        token = scanner.nextToken();
        assertThat("Token is null!", token, not(nullValue()));
        assertThat(token, instanceOf(ComplexElementDelimiterToken.class));
        assertThat(token.getPosition(), is(33));
        assertThat(token.getToken(), is(":"));

        token = scanner.nextToken();
        assertThat("Token is null!", token, not(nullValue()));
        assertThat(token, instanceOf(UnknownDelimiterToken.class));
        assertThat(token.getPosition(), is(34));
        assertThat(token.getToken(), is("1628"));

        token = scanner.nextToken();
        assertThat("Token is null!", token, not(nullValue()));
        assertThat(token, instanceOf(ElementDelimiterToken.class));
        assertThat(token.getPosition(), is(38));
        assertThat(token.getToken(), is("+"));

        token = scanner.nextToken();
        assertThat("Token is null!", token, not(nullValue()));
        assertThat(token, instanceOf(UnknownDelimiterToken.class));
        assertThat(token.getPosition(), is(39));
        assertThat(token.getToken(), is("1"));

        token = scanner.nextToken();
        assertThat("Token is null!", token, not(nullValue()));
        assertThat(token, instanceOf(SegmentDelimiterToken.class));
        assertThat(token.getPosition(), is(40));
        assertThat(token.getToken(), is("'"));
    }
}
