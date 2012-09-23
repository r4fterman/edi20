package com.inubit.ibis.plugins.edi20.core.parsers.edifact;

import java.io.File;

import com.inubit.ibis.plugins.edi20.commons.parsers.HWEDParser;
import com.inubit.ibis.plugins.edi20.commons.rules.AbstractEDIRule;
import com.inubit.ibis.plugins.edi20.commons.rules.tokens.EDIRuleBaseToken;
import com.inubit.ibis.plugins.edi20.commons.rules.tokens.EDIRuleCompositeElement;
import com.inubit.ibis.plugins.edi20.commons.rules.tokens.EDIRuleElement;
import com.inubit.ibis.plugins.edi20.commons.rules.tokens.EDIRuleSegment;
import com.inubit.ibis.plugins.edi20.commons.rules.tokens.IRuleToken;
import com.inubit.ibis.plugins.edi20.commons.scanners.IToken;
import com.inubit.ibis.plugins.edi20.core.parsers.edifact.delimiters.EDIFACTDelimiters;
import com.inubit.ibis.plugins.edi20.core.parsers.edifact.rules.EDIFACTEnveloperRule;
import com.inubit.ibis.plugins.edi20.core.parsers.edifact.rules.EDIFACTRule;
import com.inubit.ibis.plugins.edi20.core.parsers.edifact.scanners.EDIFACTLexicalScanner;
import com.inubit.ibis.plugins.edi20.utils.EDIUtil;
import com.inubit.ibis.utils.InubitException;
import com.inubit.ibis.utils.StringUtils;
import com.inubit.ibis.utils.XmlUtils;

/**
 * @author r4fter
 * 
 */
public class EDIFACTParser extends HWEDParser {

	private static final String ENVELOPER_RULEFILENAME = "EDIFACT-ENVELOPER.xml";

	private static final int SEGMENT_OR_SEGMENTGROUP = 1;
	private static final int ELEMENT_OR_COMPLEXELEMENT = 2;

	// private static final int ENVELOPER_SEGMENT_OR_SEGMENTGROUP = 11;

	private EDIFACTEnveloperRule fEnveloper;
	private int fState = SEGMENT_OR_SEGMENTGROUP;

	private EDIFACTRule fCurrentRule;

	public EDIFACTParser(final EDIFACTLexicalScanner scanner, final AbstractEDIRule rule) throws InubitException {
		super(scanner, rule);
		File enveloperRuleFile = new File(EDIUtil.RULEFILE_FOLDER, ENVELOPER_RULEFILENAME);
		if (!enveloperRuleFile.exists()) {
			throw new InubitException("Enveloper rule file is missing!");
		}
		try {
			fEnveloper = new EDIFACTEnveloperRule(XmlUtils.getDocumentThrowing(enveloperRuleFile));
		} catch (Exception e) {
			throw new InubitException("Error parsing enveloper rule file!", e);
		}
	}

	public EDIFACTEnveloperRule getEnveloper() {
		return fEnveloper;
	}

	@Override
	protected boolean isEndOfRule() {
		return getEDIFACTRule().isEndOfRule();
	}

	@Override
	protected void parseDelimiter(final IToken token) throws InubitException {
		// System.out.println("EDIFACTParser.parseDelimiter(" + fState + "): " + token.getToken());
		getEDIFACTRule().closeCurrentRuleToken(token);
		if (EDIFACTDelimiters.DELIMITER_SEGMENT == token.getDelimiterType()) {
			// segment finished
			fState = SEGMENT_OR_SEGMENTGROUP;
		} else if (EDIFACTDelimiters.DELIMITER_COMPLEXELEMENT == token.getDelimiterType()) {
			// complex element finished
			fState = ELEMENT_OR_COMPLEXELEMENT;
		} else if (EDIFACTDelimiters.DELIMITER_ELEMENT == token.getDelimiterType()) {
			// element finished
			fState = ELEMENT_OR_COMPLEXELEMENT;
		} else {
			throw new InubitException("Unknown delimiter [" + token.getToken() + "] found!");
		}
	}

	@Override
	protected void parseToken(final IToken token) throws InubitException {
		// System.out.println("EDIFACTParser.parseToken(" + fState + "): " + token.getToken());
		switch (fState) {
		case SEGMENT_OR_SEGMENTGROUP:
			parseSegment(token.getToken());
			fState = ELEMENT_OR_COMPLEXELEMENT;
			break;
		case ELEMENT_OR_COMPLEXELEMENT:
			parseElementOrComplexElement(token.getToken());
			break;
		default:
			throw new InubitException("unexpected state [" + fState + "]");
		}
	}

	private void parseSegment(final String segmentID) throws InubitException {
		parseSegment(segmentID, getEDIFACTRule(segmentID));
	}

	private void parseSegment(final String segmentID, final EDIFACTRule rule) throws InubitException {
		EDIRuleSegment segment = rule.nextSegment(segmentID);
		if (segment != null) {
			validRuleToken(segmentID, segment);
			System.out.println("EDIFACTParser.parseSegment(" + fState + "): [S:" + segmentID + " (" + segment.getLoop() + ", "
					+ segment.getCurrentLoopCount() + ")]");
			return;
		}
		throw new InubitException("Segment [" + segmentID + "] not found in rule!");
	}

	private void validRuleToken(final String token, final EDIRuleBaseToken ruleToken) throws InubitException {
		if (!assertTokenIsSet(token, ruleToken.isMandatory())) {
			throw new InubitException("Invalid token [" + token + "]! Expected mandatory rule token [" + ruleToken + "].");
		}
		if (ruleToken instanceof EDIRuleElement) {
			EDIRuleElement elementToken = (EDIRuleElement) ruleToken;
			int min = Integer.valueOf(elementToken.getMinLength()).intValue();
			int max = Integer.valueOf(elementToken.getMaxLength()).intValue();
			if (!assertTokenHasValidLength(token, min, max)) {
				throw new InubitException("Invalid token [" + token + "]! Does not match rule token size [" + ruleToken + "].");
			}
		}
	}

	private boolean assertTokenHasValidLength(final String token, final int min, final int max) {
		int length = 0;
		if (StringUtils.isSet(token)) {
			length = token.length();
		}
		return min <= length && max >= length;
	}

	private boolean assertTokenIsSet(final String token, final boolean shouldBeSet) {
		if (shouldBeSet) {
			return StringUtils.isSet(token);
		}
		return true;
	}

	private void parseElementOrComplexElement(final String element) throws InubitException {
		EDIFACTRule rule = getEDIFACTRule();
		parseElementOrComplexElement(element, rule);
	}

	private void parseElementOrComplexElement(final String element, final EDIFACTRule rule) throws InubitException {
		IRuleToken nextChild = rule.nextElement();
		if (nextChild != null) {
			// next children found
			if (nextChild instanceof EDIRuleCompositeElement) {
				System.out.println("EDIFACTParser.parseElementOrComplexElement(" + fState + "): [CE:" + nextChild.getID() + "]");
				parseElementOrComplexElement(element, rule);
				return;
			}
			if (nextChild instanceof EDIRuleElement) {
				EDIRuleElement ruleElement = (EDIRuleElement) nextChild;
				validRuleToken(element, ruleElement);
				System.out.println("EDIFACTParser.parseElementOrComplexElement(" + fState + "): [E:" + ruleElement.getID() + "]=[" + element + "]");
				return;
			}
			throw new InubitException("Unexpected rule token found [" + nextChild.getClass().getCanonicalName() + "]!");
		}
		throw new InubitException("No further rule tokens found!");
	}

	private EDIFACTRule getEDIFACTRule() {
		if (fCurrentRule == null) {
			fCurrentRule = (EDIFACTRule) getRule();
		}
		return fCurrentRule;
	}

	private EDIFACTRule getEDIFACTRule(final String segmentID) {
		if (isEnveloperSegment(segmentID)) {
			fCurrentRule = getEnveloperRule();
		} else {
			fCurrentRule = (EDIFACTRule) getRule();
		}
		return fCurrentRule;
	}

	private EDIFACTRule getEnveloperRule() {
		return fEnveloper;
	}

	private boolean isEnveloperSegment(final String segmentID) {
		return getEnveloper().containsSegment(segmentID);
	}

}
