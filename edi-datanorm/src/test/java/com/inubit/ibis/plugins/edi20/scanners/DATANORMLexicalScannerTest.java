package com.inubit.ibis.plugins.edi20.scanners;

import com.inubit.ibis.plugins.edi20.parsers.delimiters.DATANORMDelimiters;
import com.inubit.ibis.utils.FileUtils;
import org.junit.jupiter.api.Disabled;
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
class DATANORMLexicalScannerTest {

    @Test
    void testDATANORMLexicalScanner() throws Exception {
        String fileName = "datanorm_message.txt";
        StringBuilder content = getContent(fileName);

        DATANORMLexicalScanner scanner = new DATANORMLexicalScanner(content, new DATANORMDelimiters());
        assertThat(scanner.hasMoreTokens(), is(true));
    }

    @Disabled
    void testDATANORMLexicalScannerReadLength() throws Exception {
        String fileName = "datanorm_message.txt";
        StringBuilder content = getContent(fileName);

        DATANORMLexicalScanner scanner = new DATANORMLexicalScanner(content, new DATANORMDelimiters());
        assertThat(scanner.hasMoreTokens(), is(true));

        StringBuilder builder = new StringBuilder();
        while (scanner.hasMoreTokens()) {
            builder.append(scanner.nextToken().getToken());
        }
        long fileLength = getFile(fileName).length();
        assertThat("Length differs!", Long.parseLong(String.valueOf(builder.length())), is(fileLength));
    }

    @Test
    void testDATANORMLexicalScannerNoEscape() {
        String ediStr = "C;N;BAU;F;MKF;ABK;0563E.341200000;\n;030101;;;;1S;;;E;25000;62;33;\n;;ST;;;;FZ-DEUT.;563-3412~;;";

        DATANORMLexicalScanner scanner = new DATANORMLexicalScanner(new StringBuilder(ediStr), new DATANORMDelimiters());
        assertThat(scanner.hasMoreTokens(), is(true));

        IToken token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMUnknownDelimiterToken.class));
        assertThat(token.getToken(), is("C"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMElementDelimiterToken.class));
        assertThat(token.getToken(), is(";"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMUnknownDelimiterToken.class));
        assertThat(token.getToken(), is("N"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMElementDelimiterToken.class));
        assertThat(token.getToken(), is(";"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMUnknownDelimiterToken.class));
        assertThat(token.getToken(), is("BAU"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMElementDelimiterToken.class));
        assertThat(token.getToken(), is(";"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMUnknownDelimiterToken.class));
        assertThat(token.getToken(), is("F"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMElementDelimiterToken.class));
        assertThat(token.getToken(), is(";"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMUnknownDelimiterToken.class));
        assertThat(token.getToken(), is("MKF"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMElementDelimiterToken.class));
        assertThat(token.getToken(), is(";"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMUnknownDelimiterToken.class));
        assertThat(token.getToken(), is("ABK"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMElementDelimiterToken.class));
        assertThat(token.getToken(), is(";"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMUnknownDelimiterToken.class));
        assertThat(token.getToken(), is("0563E.341200000"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMElementDelimiterToken.class));
        assertThat(token.getToken(), is(";"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMSegmentDelimiterToken.class));
        assertThat(token.getToken(), is("\n"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMElementDelimiterToken.class));
        assertThat(token.getToken(), is(";"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMUnknownDelimiterToken.class));
        assertThat(token.getToken(), is("030101"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMElementDelimiterToken.class));
        assertThat(token.getToken(), is(";"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMElementDelimiterToken.class));
        assertThat(token.getToken(), is(";"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMElementDelimiterToken.class));
        assertThat(token.getToken(), is(";"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMElementDelimiterToken.class));
        assertThat(token.getToken(), is(";"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMUnknownDelimiterToken.class));
        assertThat(token.getToken(), is("1S"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMElementDelimiterToken.class));
        assertThat(token.getToken(), is(";"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMElementDelimiterToken.class));
        assertThat(token.getToken(), is(";"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMElementDelimiterToken.class));
        assertThat(token.getToken(), is(";"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMUnknownDelimiterToken.class));
        assertThat(token.getToken(), is("E"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMElementDelimiterToken.class));
        assertThat(token.getToken(), is(";"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMUnknownDelimiterToken.class));
        assertThat(token.getToken(), is("25000"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMElementDelimiterToken.class));
        assertThat(token.getToken(), is(";"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMUnknownDelimiterToken.class));
        assertThat(token.getToken(), is("62"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMElementDelimiterToken.class));
        assertThat(token.getToken(), is(";"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMUnknownDelimiterToken.class));
        assertThat(token.getToken(), is("33"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMElementDelimiterToken.class));
        assertThat(token.getToken(), is(";"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMSegmentDelimiterToken.class));
        assertThat(token.getToken(), is("\n"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMElementDelimiterToken.class));
        assertThat(token.getToken(), is(";"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMElementDelimiterToken.class));
        assertThat(token.getToken(), is(";"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMUnknownDelimiterToken.class));
        assertThat(token.getToken(), is("ST"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMElementDelimiterToken.class));
        assertThat(token.getToken(), is(";"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMElementDelimiterToken.class));
        assertThat(token.getToken(), is(";"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMElementDelimiterToken.class));
        assertThat(token.getToken(), is(";"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMElementDelimiterToken.class));
        assertThat(token.getToken(), is(";"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMUnknownDelimiterToken.class));
        assertThat(token.getToken(), is("FZ-DEUT."));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMElementDelimiterToken.class));
        assertThat(token.getToken(), is(";"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMUnknownDelimiterToken.class));
        assertThat(token.getToken(), is("563-3412~"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMElementDelimiterToken.class));
        assertThat(token.getToken(), is(";"));
        assertThat(scanner.hasMoreTokens(), is(true));

        token = scanner.nextToken();
        assertThat(token.getClass(), is(DATANORMElementDelimiterToken.class));
        assertThat(token.getToken(), is(";"));

        assertThat(scanner.hasMoreTokens(), is(false));
    }

    @Test
    void testDATANORMLexicalScannerEmptyDocEmptyDelimiter() {
        assertThrows(IllegalArgumentException.class, () -> new DATANORMLexicalScanner(new StringBuilder(""), new DATANORMDelimiters()));
    }

    @Test
    void testDATANORMLexicalScannerNullDocEmptyDelim() {
        assertThrows(IllegalArgumentException.class, () -> new DATANORMLexicalScanner(null, new DATANORMDelimiters()));
    }

    @Test
    void testLexicalScannerNullDocNullDelim() {
        assertThrows(IllegalArgumentException.class, () -> new DATANORMLexicalScanner(null, null));
    }

    private StringBuilder getContent(String fileName) throws IOException, URISyntaxException {
        File file = getFile(fileName);
        return FileUtils.getContents(file);
    }

    private File getFile(String fileName) throws URISyntaxException {
        URL url = DATANORMLexicalScannerTest.class.getResource(fileName);
        assertThat("File not found: " + fileName, url, not(nullValue()));
        return new File(url.toURI());
    }
}
