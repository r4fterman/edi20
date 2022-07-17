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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EDIFACTLexicalScannerTest {

    @Test
    void scanner_with_message_should_has_more_tokens() throws Exception {
        final String testFile = "EDIFACT-ifcsum-1.txt";
        final File edifactFile = getFile(testFile);
        final StringBuilder content = FileUtils.getContents(edifactFile);

        final EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(content, new EDIFACTDelimiters());
        assertThat(scanner.hasMoreTokens(), is(true));
    }

    @Test
    void scanner_should_report_each_token() throws Exception {
        final String testFile = "EDIFACT-ifcsum-1.txt";
        final File edifactFile = getFile(testFile);
        final StringBuilder content = FileUtils.getContents(edifactFile);
        final int contentLength = content.length();

        final EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(content, new EDIFACTDelimiters());

        final StringBuilder builder = new StringBuilder();
        while (scanner.hasMoreTokens()) {
            builder.append(scanner.nextToken().getToken());
        }

        final int scannerLength = builder.toString().length();

        assertThat("Content differs!" + contentLength + "!=" + scannerLength, scannerLength, is(contentLength));
    }

    @Test
    void scanner_should_read_content_with_no_escape_sign() {
        final String content = "UNB+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";

        final EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuilder(content), new EDIFACTDelimiters());

        assertThat(scanner.nextToken().getToken(), is("UNB"));
        assertThat(scanner.nextToken().getToken(), is("+"));
        assertThat(scanner.nextToken().getToken(), is("UNOB"));
        assertThat(scanner.nextToken().getToken(), is(":"));
        assertThat(scanner.nextToken().getToken(), is("3"));
        assertThat(scanner.nextToken().getToken(), is("+"));
        assertThat(scanner.nextToken().getToken(), is("RUDOLF0"));
        assertThat(scanner.nextToken().getToken(), is("+"));
        assertThat(scanner.nextToken().getToken(), is("ELIX000"));
        assertThat(scanner.nextToken().getToken(), is("+"));
        assertThat(scanner.nextToken().getToken(), is("011015"));
        assertThat(scanner.nextToken().getToken(), is(":"));
        assertThat(scanner.nextToken().getToken(), is("1628"));
        assertThat(scanner.nextToken().getToken(), is("+"));
        assertThat(scanner.nextToken().getToken(), is("1"));
        assertThat(scanner.nextToken().getToken(), is("'"));
        assertThat(scanner.hasMoreTokens(), is(false));
    }

    @Test
    void scanner_should_throw_exception_when_reading_empty_content() {
        assertThrows(IllegalArgumentException.class, () -> new EDIFACTLexicalScanner(new StringBuilder(), new EDIFACTDelimiters()));
    }

    @Test
    void scanner_should_throw_exception_when_reading_null_content() {
        assertThrows(IllegalArgumentException.class, () -> new EDIFACTLexicalScanner(null, new EDIFACTDelimiters()));
    }

    @Test
    void scanner_should_throw_exception_when_using_null_delimiter() {
        final String content = "UNB+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";
        assertThrows(IllegalArgumentException.class, () -> new EDIFACTLexicalScanner(new StringBuilder(content), null));
    }

    @Test
    void scanner_should_read_content_with_escape_sign_at_begin_of_message() {
        final String content = "?+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";
        final EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuilder(content), new EDIFACTDelimiters());
        assertThat(scanner.getIndexOfDelimiter(0, "+"), is(8));
    }

    @Test
    void scanner_should_find_index_of_delimiter_at_end_of_message() {
        final String content = "UNB+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";
        final EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuilder(content), new EDIFACTDelimiters());
        assertThat(scanner.getIndexOfDelimiter(0, "'"), is(40));
    }

    @Test
    void scanner_should_find_index_of_delimiter_at_begin_of_message() {
        final String content = "UNB+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";
        final EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuilder(content), new EDIFACTDelimiters());
        assertThat(scanner.getIndexOfDelimiter(0, "+"), is(3));
    }

    @Test
    void scanner_should_read_content_with_escape_sign_at_end_of_message() {
        final String content = "UNB?+UNOB:3?+RUDOLF0?+ELIX000?+011015:1628+1'";
        final EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuilder(content), new EDIFACTDelimiters());
        assertThat(scanner.getIndexOfDelimiter(0, "+"), is(42));
    }

    @Test
    void scanner_should_read_content_with_escape_sign_at_start_of_message() {
        final String ediStr = "UNB?+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";
        final EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuilder(ediStr), new EDIFACTDelimiters());
        assertThat(scanner.getIndexOfDelimiter(0, "+"), is(11));
    }

    @Test
    void scanner_should_find_index_of_delimiter_in_content_with_no_escape_sign() {
        final String ediStr = "UNB+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";
        final EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuilder(ediStr), new EDIFACTDelimiters());
        assertThat(scanner.getIndexOfDelimiter(0, ":"), is(8));
    }

    @Test
    void scanner_should_always_provide_token_value_and_position() {
        final String ediStr = "UNB+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'";
        final EDIFACTLexicalScanner scanner = new EDIFACTLexicalScanner(new StringBuilder(ediStr), new EDIFACTDelimiters());

        assertToken(scanner.nextToken(), UnknownDelimiterToken.class, 0, "UNB");
        assertToken(scanner.nextToken(), ElementDelimiterToken.class, 3, "+");
        assertToken(scanner.nextToken(), UnknownDelimiterToken.class, 4, "UNOB");
        assertToken(scanner.nextToken(), ComplexElementDelimiterToken.class, 8, ":");
        assertToken(scanner.nextToken(), UnknownDelimiterToken.class, 9, "3");
        assertToken(scanner.nextToken(), ElementDelimiterToken.class, 10, "+");
        assertToken(scanner.nextToken(), UnknownDelimiterToken.class, 11, "RUDOLF0");
        assertToken(scanner.nextToken(), ElementDelimiterToken.class, 18, "+");
        assertToken(scanner.nextToken(), UnknownDelimiterToken.class, 19, "ELIX000");
        assertToken(scanner.nextToken(), ElementDelimiterToken.class, 26, "+");
        assertToken(scanner.nextToken(), UnknownDelimiterToken.class, 27, "011015");
        assertToken(scanner.nextToken(), ComplexElementDelimiterToken.class, 33, ":");
        assertToken(scanner.nextToken(), UnknownDelimiterToken.class, 34, "1628");
        assertToken(scanner.nextToken(), ElementDelimiterToken.class, 38, "+");
        assertToken(scanner.nextToken(), UnknownDelimiterToken.class, 39, "1");
        assertToken(scanner.nextToken(), SegmentDelimiterToken.class, 40, "'");
    }

    private void assertToken(
            final Token token,
            final Class<? extends Token> tokenClass,
            final int position,
            final String tokenValue) {
        assertThat("Token is null!", token, not(nullValue()));
        assertThat(token, instanceOf(tokenClass));
        assertThat(token.getPosition(), is(position));
        assertThat(token.getToken(), is(tokenValue));
    }

    private File getFile(final String testFile) throws URISyntaxException {
        final URL url = EDIFACTLexicalScannerTest.class.getResource(testFile);
        assertThat("File not found: " + testFile, url, not(nullValue()));
        return new File(url.toURI());
    }

}
