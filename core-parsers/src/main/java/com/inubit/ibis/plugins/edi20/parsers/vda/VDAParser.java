package com.inubit.ibis.plugins.edi20.parsers.vda;

import com.inubit.ibis.plugins.edi20.parsers.HWFPEParser;
import com.inubit.ibis.plugins.edi20.parsers.vda.delimiters.VDADelimiters;
import com.inubit.ibis.plugins.edi20.parsers.vda.rules.VDARule;
import com.inubit.ibis.plugins.edi20.parsers.vda.scanners.VDALexicalScanner;
import com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule;
import com.inubit.ibis.plugins.edi20.rules.interfaces.IRuleToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegment;
import com.inubit.ibis.plugins.edi20.scanners.IToken;
import com.inubit.ibis.utils.InubitException;

/**
 * @author r4fter
 */
public class VDAParser extends HWFPEParser {

    public static VDAParser buildParser(StringBuilder textInputDocument, VDARule rule) {
        VDALexicalScanner scanner = new VDALexicalScanner(textInputDocument, getDelimiter());
        return new VDAParser(scanner, rule);
    }

    private static VDADelimiters getDelimiter() {
        return new VDADelimiters();
    }

    /**
     * @param scanner
     *         VDA lexical scanner
     * @param rule
     *         VDA rule
     */
    public VDAParser(final VDALexicalScanner scanner, final VDARule rule) {
        super(scanner, rule);
    }

    private VDARule getVDARule() {
        return (VDARule) getRule();
    }

    @Override
    protected void resetRule() {
//        getVDARule().reset();
    }

    @Override
    protected boolean isEndOfRule() {
        return getVDARule().isEndOfRule();
    }

    @Override
    protected void parseToken(final IToken token) throws InubitException {
        System.out.println("VDAParser.parseToken(): [" + token + "]");
        // find segment in rule for the given token
        EDIRuleSegment segment = getRuleSegment(token);
        System.out.println("VDAParser.parseToken(): rule token=[" + segment + "]");
        if (segment != null) {
            // parse token versus segment content from rule            
            System.out.println("VDAParser.parseToken(parse segment): ...");
            return;
        }
        throw new InubitException("No segment found in rule for token [" + token + "]!");
    }

    private EDIRuleSegment getRuleSegment(final IToken messageToken) throws InubitException {
//        IRuleToken ruleToken = getVDARule().getCurrentRuleToken();
//        if (isCorrectSegmentRuleToken(messageToken, ruleToken)) {
//            EDIRuleSegment segment = (EDIRuleSegment) ruleToken;
//            segment.looped();
//            return segment;
//        }

        EDIRuleSegment ruleSegment;
        while ((ruleSegment = getVDARule().nextSegment()) != null) {
            if (isCorrectSegmentRuleToken(messageToken, ruleSegment)) {
                return ruleSegment;
            }
        }
        return null;
    }

    private boolean isSegment(IRuleToken ruleToken) {
        return ruleToken instanceof EDIRuleSegment;
    }

    private boolean isCorrectSegmentRuleToken(IToken messageToken, IRuleToken ruleToken) {
        if (isSegment(ruleToken)) {
            return messageToken.getToken().startsWith(ruleToken.getID());
        }
        return false;
    }

    @Override
    protected void parseDelimiter(final IToken token) throws InubitException {
        // TODO: segment finished
    }

    @Override
    public boolean canParse(AbstractEDIRule rule) {
        return rule instanceof VDARule;
    }

    @Override
    public boolean canParse(StringBuilder inputDocument) {
        // FIXME: detect vda messages
        return false;
    }

}