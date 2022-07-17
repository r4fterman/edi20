package com.inubit.ibis.plugins.edi20.parsers;

import com.inubit.ibis.plugins.edi20.outputs.OutputWriter;
import com.inubit.ibis.plugins.edi20.outputs.OutputWriterFactory;
import com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule;
import com.inubit.ibis.plugins.edi20.scanners.Scanner;
import com.inubit.ibis.plugins.edi20.scanners.Token;
import com.inubit.ibis.utils.EDIException;
import com.inubit.ibis.utils.StringUtil;

public abstract class AbstractEDIParser implements EDIParser {

    private final AbstractEDIRule ediRule;
    private final Scanner scanner;
    private OutputWriter writer;

    /**
     * @param scanner lexical scanner
     * @param ediRule EDI rule
     */
    protected AbstractEDIParser(
            final Scanner scanner,
            final AbstractEDIRule ediRule) {
        super();
        this.scanner = scanner;
        this.ediRule = ediRule;
    }

    /**
     * @return EDI rule
     */
    public AbstractEDIRule getRule() {
        return ediRule;
    }

    /**
     * @return lexical scanner
     */
    public Scanner getScanner() {
        return scanner;
    }

    public OutputWriter getWriter() throws EDIException {
        if (writer == null) {
            writer = OutputWriterFactory.getInstance(getRule());
        }
        return writer;
    }

    @Override
    public void parse() throws EDIException {
        if (getScanner().hasMoreTokens()) {
            parseTokens();
        }
    }

    private void parseTokens() throws EDIException {
        while (getScanner().hasMoreTokens() && !isEndOfRule()) {
            final Token token = getScanner().nextToken();
            parseNextToken(token);
        }

        if (getScanner().hasMoreTokens()) {
            final String unparsedPart = getUnparsedPart();
            // ignore white spaces at the end of message
            if (!StringUtil.isWhitespacesOnly(unparsedPart)) {
                final String message = String.format("Rule parsing complete but message still contains data [%s]!",
                        unparsedPart);
                throw new EDIException(message);
            }
        }
    }

    private void parseNextToken(final Token token) throws EDIException {
        if (StringUtil.isLineBreakOnly(token.getToken())) {
            return;
        }
        if (token.isDelimiter()) {
            parseDelimiter(token);
        } else {
            parseToken(token);
        }
    }

    private String getUnparsedPart() {
        // empty the scanner
        final StringBuilder unparsedBuilder = new StringBuilder();
        while (getScanner().hasMoreTokens()) {
            unparsedBuilder.append(getScanner().nextToken().getToken());
        }
        return unparsedBuilder.toString();
    }

    protected abstract boolean isEndOfRule();

    protected abstract void parseToken(Token token) throws EDIException;

    protected abstract void parseDelimiter(Token token) throws EDIException;
}
