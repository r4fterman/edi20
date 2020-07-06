package com.inubit.ibis.plugins.edi20.scanners;

import com.inubit.ibis.plugins.edi20.parsers.delimiters.VDADelimiters;
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

class VDALexicalScannerTest {

    private static final String MESSAGE = "VDA4905_1.txt";

    @Test
    void testVDALexicalScanner() throws Exception {
        final StringBuilder content = getContent();
        final VDALexicalScanner scanner = new VDALexicalScanner(content, new VDADelimiters());
        assertThat(scanner.hasMoreTokens(), is(true));
    }

    @Test
    void testVDALexicalScannerReadLength() throws Exception {
        final StringBuilder content = getContent();

        final VDALexicalScanner scanner = new VDALexicalScanner(content, new VDADelimiters());
        assertThat(scanner.hasMoreTokens(), is(true));

        final StringBuilder builder = new StringBuilder();
        while (scanner.hasMoreTokens()) {
            builder.append(scanner.nextToken().getToken());
        }
        final long fileLength = getFile().length();
        assertThat("Length differs!", Long.parseLong(String.valueOf(builder.length())), is(fileLength));
    }

    @Test
    void testVDALexicalScannerNoEscape() {
        final String ediStr = "51101AG03     CKDVW    \n51201001000000002020610000000001020523\n51301 ";

        final VDALexicalScanner scanner = new VDALexicalScanner(new StringBuilder(ediStr), new VDADelimiters());
        assertThat(scanner.hasMoreTokens(), is(true));

        Token token = scanner.nextToken();
        assertThat(token.getClass(), is(VDAUnknownDelimiterToken.class));
        assertThat(token.getToken(), is("51101AG03     CKDVW    "));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass().equals(VDASegmentDelimiterToken.class), is(true));
        assertThat(token.getToken(), is("\n"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass().equals(VDAUnknownDelimiterToken.class), is(true));
        assertThat(token.getToken(), is("51201001000000002020610000000001020523"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass().equals(VDASegmentDelimiterToken.class), is(true));
        assertThat(token.getToken(), is("\n"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(VDAUnknownDelimiterToken.class));
        assertThat(token.getToken(), is("51301 "));
        assertThat(scanner.hasMoreTokens(), is(false));
    }

    @Test
    void testVDALexicalScannerEmptyDocEmptyDelimiter() {
        assertThrows(IllegalArgumentException.class, () -> new VDALexicalScanner(new StringBuilder(), new VDADelimiters()));
    }

    @Test
    void testVDALexicalScannerNullDocEmptyDelimiter() {
        assertThrows(IllegalArgumentException.class, () -> new VDALexicalScanner(null, new VDADelimiters()));
    }

    @Test
    void testVDALexicalScannerNullDocNullDelim() {
        assertThrows(IllegalArgumentException.class, () -> new VDALexicalScanner(null, null));
    }

    private StringBuilder getContent() throws URISyntaxException, IOException {
        final File file = getFile();
        return FileUtils.getContents(file);
    }

    private File getFile() throws URISyntaxException {
        final URL url = VDALexicalScannerTest.class.getResource(MESSAGE);
        assertThat("File not found: " + MESSAGE, url, not(nullValue()));
        return new File(url.toURI());
    }
}
