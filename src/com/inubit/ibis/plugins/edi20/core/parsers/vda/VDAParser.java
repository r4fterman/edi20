package com.inubit.ibis.plugins.edi20.core.parsers.vda;

import com.inubit.ibis.plugins.edi20.commons.parsers.HWFPEParser;
import com.inubit.ibis.plugins.edi20.commons.rules.tokens.EDIRuleSegment;
import com.inubit.ibis.plugins.edi20.commons.scanners.IToken;
import com.inubit.ibis.plugins.edi20.core.parsers.vda.rules.VDARule;
import com.inubit.ibis.plugins.edi20.core.parsers.vda.scanners.VDALexicalScanner;
import com.inubit.ibis.utils.InubitException;

/**
 * @author r4fter
 */
public class VDAParser extends HWFPEParser {

    /**
     * @param scanner
     *            VDA lexical scanner
     * @param rule
     *            VDA rule
     */
    public VDAParser(final VDALexicalScanner scanner, final VDARule rule) {
        super(scanner, rule);
    }

    private VDARule getVDARule() {
        return (VDARule) getRule();
    }

    @Override
    protected boolean isEndOfRule() {
        return getVDARule().isEndOfRule();
    }

    @Override
    protected void parseToken(final IToken token) throws InubitException {
        System.out.println("VDAParser.parseToken(): " + token);
        // find segment in rule for the given token
        EDIRuleSegment segment = getRuleSegment(token);
        System.out.println("VDAParser.parseToken(): " + segment);
        if (segment != null) {
            // parse token versus segment content from rule            
            System.out.println("VDAParser.parseToken(parse segment): " + segment);
            return;
        }
        throw new InubitException("No segment not found in rule for token [" + token + "]!");
    }

    private EDIRuleSegment getRuleSegment(final IToken token) {
        EDIRuleSegment segment = null;
        while ((segment = getVDARule().nextSegment()) != null) {
            if (token.getToken().startsWith(segment.getID())) {
                return segment;
            }
        }
        return null;
    }

    @Override
    protected void parseDelimiter(final IToken token) throws InubitException {
        // TODO: segment finished
    }

}
