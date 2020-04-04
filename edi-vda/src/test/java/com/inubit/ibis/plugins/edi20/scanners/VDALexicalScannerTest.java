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

/**
 * @author r4fter
 */
class VDALexicalScannerTest {

    @Test
    void testVDALexicalScanner() throws Exception {
        String testFile = "VDA4905_1.txt";
        StringBuilder content = getContent(testFile);
        VDALexicalScanner scanner = new VDALexicalScanner(content, new VDADelimiters());
        assertThat(scanner.hasMoreTokens(), is(true));
    }

    @Test
    void testVDALexicalScannerReadLength() throws Exception {
        String testFile = "VDA4905_1.txt";
        StringBuilder content = getContent(testFile);

        VDALexicalScanner scanner = new VDALexicalScanner(content, new VDADelimiters());
        assertThat(scanner.hasMoreTokens(), is(true));

        StringBuilder builder = new StringBuilder();
        while (scanner.hasMoreTokens()) {
            builder.append(scanner.nextToken().getToken());
        }
        long fileLength = getFile(testFile).length();
        assertThat("Length differs!", Long.parseLong(String.valueOf(builder.length())), is(fileLength));
    }

    @Test
    void testVDALexicalScannerNoEscape() {
        String ediStr = "51101AG03     CKDVW    \n51201001000000002020610000000001020523\n51301 ";

        VDALexicalScanner scanner = new VDALexicalScanner(new StringBuilder(ediStr), new VDADelimiters());
        assertThat(scanner.hasMoreTokens(), is(true));

        IToken token = scanner.nextToken();
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

    private StringBuilder getContent(String testFile) throws URISyntaxException, IOException {
        File file = getFile(testFile);
        return FileUtils.getContents(file);
    }

    private File getFile(String testFile) throws URISyntaxException {
        URL url = VDALexicalScannerTest.class.getResource(testFile);
        assertThat("File not found: " + testFile, url, not(nullValue()));
        return new File(url.toURI());
    }
}
