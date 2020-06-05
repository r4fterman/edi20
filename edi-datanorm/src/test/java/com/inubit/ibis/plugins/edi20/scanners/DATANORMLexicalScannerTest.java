package com.inubit.ibis.plugins.edi20.scanners;

import com.inubit.ibis.plugins.edi20.parsers.delimiters.DATANORMDelimiters;
import com.inubit.ibis.utils.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DATANORMLexicalScannerTest {

    @Test
    void testDATANORMLexicalScanner() throws Exception {
        final String fileName = "datanorm_message.txt";
        final StringBuilder content = getContent(fileName);

        final DATANORMLexicalScanner scanner = new DATANORMLexicalScanner(content, new DATANORMDelimiters());
        assertThat(scanner.hasMoreTokens(), is(true));
    }

    @Test
    void testDATANORMLexicalScannerReadLength() throws Exception {
        final String fileName = "datanorm_message.txt";
        final StringBuilder content = getContent(fileName);

        final DATANORMLexicalScanner scanner = new DATANORMLexicalScanner(content, new DATANORMDelimiters());
        assertThat(scanner.hasMoreTokens(), is(true));

        final StringBuilder builder = new StringBuilder();
        while (scanner.hasMoreTokens()) {
            builder.append(scanner.nextToken().getToken());
        }
        final long fileLength = getFile(fileName).length();
        assertThat("Length differs!", Long.parseLong(String.valueOf(builder.length())), is(fileLength));
    }

    @Test
    void testDATANORMLexicalScannerNoEscape() {
        final String ediStr = "C;N;BAU;F;MKF;ABK;0563E.341200000;\n;030101;;;;1S;;;E;25000;62;33;\n;;ST;;;;FZ-DEUT.;563-3412~;;";

        final DATANORMLexicalScanner scanner = new DATANORMLexicalScanner(new StringBuilder(ediStr), new DATANORMDelimiters());
        assertThat(scanner.hasMoreTokens(), is(true));

        Token token;

        assertToken(scanner.nextToken(), DATANORMUnknownDelimiterToken.class, "C");
        assertToken(scanner.nextToken(), DATANORMElementDelimiterToken.class, ";");
        assertToken(scanner.nextToken(), DATANORMUnknownDelimiterToken.class, "N");
        assertToken(scanner.nextToken(), DATANORMElementDelimiterToken.class, ";");
        assertToken(scanner.nextToken(), DATANORMUnknownDelimiterToken.class, "BAU");
        assertToken(scanner.nextToken(), DATANORMElementDelimiterToken.class, ";");
        assertToken(scanner.nextToken(), DATANORMUnknownDelimiterToken.class, "F");
        assertToken(scanner.nextToken(), DATANORMElementDelimiterToken.class, ";");
        assertToken(scanner.nextToken(), DATANORMUnknownDelimiterToken.class, "MKF");
        assertToken(scanner.nextToken(), DATANORMElementDelimiterToken.class, ";");
        assertToken(scanner.nextToken(), DATANORMUnknownDelimiterToken.class, "ABK");
        assertToken(scanner.nextToken(), DATANORMElementDelimiterToken.class, ";");
        assertToken(scanner.nextToken(), DATANORMUnknownDelimiterToken.class, "0563E.341200000");
        assertToken(scanner.nextToken(), DATANORMElementDelimiterToken.class, ";");
        assertToken(scanner.nextToken(), DATANORMSegmentDelimiterToken.class, "\n");
        assertToken(scanner.nextToken(), DATANORMElementDelimiterToken.class, ";");
        assertToken(scanner.nextToken(), DATANORMUnknownDelimiterToken.class, "030101");
        assertToken(scanner.nextToken(), DATANORMElementDelimiterToken.class, ";");
        assertToken(scanner.nextToken(), DATANORMElementDelimiterToken.class, ";");
        assertToken(scanner.nextToken(), DATANORMElementDelimiterToken.class, ";");
        assertToken(scanner.nextToken(), DATANORMElementDelimiterToken.class, ";");
        assertToken(scanner.nextToken(), DATANORMUnknownDelimiterToken.class, "1S");
        assertToken(scanner.nextToken(), DATANORMElementDelimiterToken.class, ";");
        assertToken(scanner.nextToken(), DATANORMElementDelimiterToken.class, ";");
        assertToken(scanner.nextToken(), DATANORMElementDelimiterToken.class, ";");
        assertToken(scanner.nextToken(), DATANORMUnknownDelimiterToken.class, "E");
        assertToken(scanner.nextToken(), DATANORMElementDelimiterToken.class, ";");
        assertToken(scanner.nextToken(), DATANORMUnknownDelimiterToken.class, "25000");
        assertToken(scanner.nextToken(), DATANORMElementDelimiterToken.class, ";");
        assertToken(scanner.nextToken(), DATANORMUnknownDelimiterToken.class, "62");
        assertToken(scanner.nextToken(), DATANORMElementDelimiterToken.class, ";");
        assertToken(scanner.nextToken(), DATANORMUnknownDelimiterToken.class, "33");
        assertToken(scanner.nextToken(), DATANORMElementDelimiterToken.class, ";");
        assertToken(scanner.nextToken(), DATANORMSegmentDelimiterToken.class, "\n");
        assertToken(scanner.nextToken(), DATANORMElementDelimiterToken.class, ";");
        assertToken(scanner.nextToken(), DATANORMElementDelimiterToken.class, ";");
        assertToken(scanner.nextToken(), DATANORMUnknownDelimiterToken.class, "ST");
        assertToken(scanner.nextToken(), DATANORMElementDelimiterToken.class, ";");
        assertToken(scanner.nextToken(), DATANORMElementDelimiterToken.class, ";");
        assertToken(scanner.nextToken(), DATANORMElementDelimiterToken.class, ";");
        assertToken(scanner.nextToken(), DATANORMElementDelimiterToken.class, ";");
        assertToken(scanner.nextToken(), DATANORMUnknownDelimiterToken.class, "FZ-DEUT.");
        assertToken(scanner.nextToken(), DATANORMElementDelimiterToken.class, ";");
        assertToken(scanner.nextToken(), DATANORMUnknownDelimiterToken.class, "563-3412~");
        assertToken(scanner.nextToken(), DATANORMElementDelimiterToken.class, ";");
        assertToken(scanner.nextToken(), DATANORMElementDelimiterToken.class, ";");
        assertThat(scanner.hasMoreTokens(), is(false));
    }

    @Test
    void testDATANORMLexicalScannerEmptyDocEmptyDelimiter() {
        assertThrows(IllegalArgumentException.class, () -> new DATANORMLexicalScanner(new StringBuilder(), new DATANORMDelimiters()));
    }

    @Test
    void testDATANORMLexicalScannerNullDocEmptyDelim() {
        assertThrows(IllegalArgumentException.class, () -> new DATANORMLexicalScanner(null, new DATANORMDelimiters()));
    }

    @Test
    void testLexicalScannerNullDocNullDelim() {
        assertThrows(IllegalArgumentException.class, () -> new DATANORMLexicalScanner(null, null));
    }

    private void assertToken(
            final Token token,
            final Class<? extends Token> tokenClass,
            final String tokenValue) {
        assertThat(token.getClass(), is(tokenClass));
        assertThat(token.getToken(), is(tokenValue));
    }

    private StringBuilder getContent(final String fileName) throws IOException, URISyntaxException {
        final File file = getFile(fileName);
        return FileUtils.getContents(file);
    }

    private File getFile(final String fileName) throws URISyntaxException {
        final URL url = DATANORMLexicalScannerTest.class.getResource(fileName);
        assertThat("File not found: " + fileName, url, not(nullValue()));
        return new File(url.toURI());
    }
}
