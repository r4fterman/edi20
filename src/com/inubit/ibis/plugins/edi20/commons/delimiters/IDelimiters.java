/**
 * 
 */
package com.inubit.ibis.plugins.edi20.commons.delimiters;

/**
 * @author r4fter
 * 
 */
public interface IDelimiters {

    public static final int DELIMITER_UNKNOWN = -1;

    /**
     * Method returns delimiter for the given identifier.
     * 
     * @param delimiterIdentifier
     *            delimiter identifier
     * @return delimiter or an empty string
     */
    public String getDelimiter(int delimiterIdentifier);

    /**
     * Method sets the given delimiter.
     * 
     * @param delimiter
     *            delimiter
     * @param delimiterIdentifier
     *            delimiter identifier
     */
    public void setDelimiter(String delimiter, int delimiterIdentifier);

    /**
     * @return identifier for Escape delimiter or {@link #DELIMITER_UNKNOWN} if no such identifier exists
     */
    public int getEscapeDelimiterIndentifier();

    /**
     * @param delimiter
     *            delimiter to check
     * @return <code>true</code> if the given delimiter is one of these delimiters, <code>false</code> otherwise
     */
    public boolean containsDelimiter(String delimiter);

    /**
     * @param delimiter
     *            delimiter
     * @return delimiter identifier or -1 if the given delimiter is not one of these delimiters
     */
    public int getDelimiterIdentifier(String delimiter);
}
