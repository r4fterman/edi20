package com.inubit.ibis.utils;

import java.io.*;

/**
 * @author rafter
 */
public final class FileUtils {

    /**
     * Fetch the entire contents of a text file, and return it in a String. This style of implementation does not throw
     * Exceptions to the caller.
     *
     * @param fileToRead
     *         is a file which already exists and can be read.
     * @throws IOException
     */
    public static StringBuilder getContents(final File fileToRead) throws IOException {
        if (fileToRead == null) {
            throw new IllegalArgumentException("File must not be null.");
        }
        if (!fileToRead.isFile()) {
            throw new IOException("Declared file is not a file.");
        }
        if (!fileToRead.canRead()) {
            throw new IOException("Unable to read file.");
        }

        final StringBuilder contents = new StringBuilder();
        try {
            // use buffering, reading one line at a time
            // FileReader always assumes default encoding is OK!
            final BufferedReader input = new BufferedReader(new FileReader(fileToRead));
            try {
                // not declared within while loop
                String line = null;
                /*
                 * readLine is a bit quirky : it returns the content of a line
                 * MINUS the newline. it returns null only for the END of the
                 * stream. it returns an empty String if two newlines appear in
                 * a row.
                 */
                while ((line = input.readLine()) != null) {
                    contents.append(line);
                    contents.append(System.getProperty("line.separator"));
                }
            } finally {
                input.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return contents;
    }

    /**
     * Change the contents of text file in its entirety, overwriting any existing text.
     *
     * This style of implementation throws all exceptions to the caller.
     *
     * @param fileToWrite
     *         is an existing file which can be written to.
     * @throws IOException
     */
    public static void setContents(final File fileToWrite, final String content) throws IOException {
        if (fileToWrite == null) {
            throw new IllegalArgumentException("File must not be null.");
        }
        if (!fileToWrite.exists()) {
            // create file
        }

        if (!fileToWrite.canWrite()) {
            throw new IOException("File cannot be written: " + fileToWrite);
        }

        // use buffering
        final Writer output = new BufferedWriter(new FileWriter(fileToWrite));
        try {
            // FileWriter always assumes default encoding is OK!
            output.write(content);
        } finally {
            output.close();
        }
    }

}
