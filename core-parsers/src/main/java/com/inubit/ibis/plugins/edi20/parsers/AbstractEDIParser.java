package com.inubit.ibis.plugins.edi20.parsers;

import com.inubit.ibis.plugins.edi20.outputs.IOutputWriter;
import com.inubit.ibis.plugins.edi20.outputs.OutputWriterFactory;
import com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule;
import com.inubit.ibis.plugins.edi20.scanners.IScanner;
import com.inubit.ibis.plugins.edi20.scanners.IToken;
import com.inubit.ibis.utils.InubitException;
import com.inubit.ibis.utils.StringUtil;

/**
 * @author r4fter
 */
public abstract class AbstractEDIParser implements IEDIParser {

    private final AbstractEDIRule ediRule;
    private final IScanner scanner;
    private IOutputWriter writer;

    /**
     * @param scanner
     *         lexical scanner
     * @param rule
     *         EDI rule
     */
    public AbstractEDIParser(final IScanner scanner, final AbstractEDIRule rule) {
        super();
        this.scanner = scanner;
        this.ediRule = rule;
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
    public IScanner getScanner() {
        return scanner;
    }

    public IOutputWriter getWriter() throws InubitException {
        if (writer == null) {
            this.writer = OutputWriterFactory.getInstance(getRule());
        }
        return writer;
    }

    @Override
    public void parse() throws InubitException {
        if (getScanner().hasMoreTokens()) {
            parseTokens();
        }
    }

    private void parseTokens() throws InubitException {
        while (getScanner().hasMoreTokens() && !isEndOfRule()) {
            IToken token = getScanner().nextToken();
            if (token.isDelimiter()) {
                parseDelimiter(token);
            } else {
                parseToken(token);
            }
        }

        if (getScanner().hasMoreTokens()) {
            String unparsedPart = getUnparsedPart();
            // ignore white spaces at the end of message
            if (!StringUtil.isWhitespacesOnly(unparsedPart)) {
                throw new InubitException("Rule parsing complete but message still contains data [" + unparsedPart + "]!");
            }
        }
    }

    private String getUnparsedPart() {
        // empty the scanner
        StringBuilder unparsedBuilder = new StringBuilder("");
        while (getScanner().hasMoreTokens()) {
            unparsedBuilder.append(getScanner().nextToken().getToken());
        }
        return unparsedBuilder.toString();
    }

    protected abstract boolean isEndOfRule();

    protected abstract void parseToken(IToken token) throws InubitException;

    protected abstract void parseDelimiter(IToken token) throws InubitException;
}
