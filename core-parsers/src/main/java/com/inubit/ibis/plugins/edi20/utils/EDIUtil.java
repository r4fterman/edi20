/**
 *
 */
package com.inubit.ibis.plugins.edi20.utils;

import com.inubit.ibis.utils.StringUtil;

import java.io.File;

/**
 * @author r4fter
 */
public final class EDIUtil {

    private static final File USER_FOLDER = new File(System.getProperty("user.dir"));
    public static final File RULE_FILE_FOLDER = new File(USER_FOLDER, "testfiles/rules");

    /**
     * Method checks whether the token delimiter is escaped in the given token.
     *
     * @param token           token to check
     * @param tokenDelimiter  token delimiter
     * @param escapeDelimiter escape delimiter
     * @return <code>true</code> if token delimiter is escaped, <code>false</code> otherwise
     */
    public static boolean isEscaped(final String token, final String tokenDelimiter, final String escapeDelimiter) {
        if (StringUtil.isNotSet(token)) {
            return false;
        }
        if (StringUtil.isNotSet(escapeDelimiter)) {
            return false;
        }
        if (StringUtil.isNotSet(tokenDelimiter)) {
            return false;
        }

        int idx = token.indexOf(tokenDelimiter);
        if (idx == -1) {
            return false;
        }
        String text = token.substring(0, idx);
        if (StringUtil.isNotSet(text)) {
            return false;
        }
        int cnt = countEscapeDelimiters(text, escapeDelimiter);
        return cnt % 2 == 1;
    }

    /**
     * Method counts all conjuncted escape delimiters in the given text by parsing the text backward.
     *
     * @param text            text to parse
     * @param escapeDelimiter escape delimiter to count
     * @return count of escape delimiters
     */
    private static int countEscapeDelimiters(final String text, final String escapeDelimiter) {
        int escDelimCount = 0;
        int escDelimLength = escapeDelimiter.length();

        // parse backward
        int end = text.length();
        int start = end - escDelimLength;
        String textEnd = text.substring(start, end);
        while (textEnd.equals(escapeDelimiter)) {
            escDelimCount++;
            end = start;
            start = end - escDelimLength;
            if (start < 0) {
                break;
            }
            textEnd = text.substring(start, end);
        }
        return escDelimCount;
    }

}
