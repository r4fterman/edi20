package com.inubit.ibis.plugins.edi20.commons.rules;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;


import org.junit.Test;

import com.inubit.ibis.plugins.edi20.commons.rules.AbstractEDIRule;
import com.inubit.ibis.plugins.edi20.commons.rules.EDIRuleFactory;
import com.inubit.ibis.plugins.edi20.commons.rules.tokens.EDIRuleSegment;
import com.inubit.ibis.plugins.edi20.core.parsers.edifact.delimiters.EDIFACTDelimiters;
import com.inubit.ibis.plugins.edi20.core.parsers.edifact.rules.EDIFACTRule;
import com.inubit.ibis.utils.InubitException;

/**
 * @author r4fter
 * 
 */
public class EDIRuleFactoryTest {

	@Test
	public void testGetRule() {
		try {
			String test = "UNB+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'UNH+00000114000001+IFCSUM:D:96A:UN:FIBO01'BGM+785+470000004+9'";
			StringBuffer testBuffer = new StringBuffer(test);
			AbstractEDIRule rule = EDIRuleFactory.getInstance(testBuffer, new EDIFACTDelimiters()).getRule();
			assertNotNull(rule);
			assertTrue(rule instanceof EDIFACTRule);
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetRuleTokenPath() {
		try {
			String test = "UNB+UNOB:3+RUDOLF0+ELIX000+011015:1628+1'UNH+00000114000001+IFCSUM:D:96A:UN:FIBO01'BGM+785+470000004+9'";
			StringBuffer testBuffer = new StringBuffer(test);
			AbstractEDIRule rule = EDIRuleFactory.getInstance(testBuffer, new EDIFACTDelimiters()).getRule();
			assertNotNull(rule);
			assertTrue(rule instanceof EDIFACTRule);
			EDIFACTRule edifactRule = (EDIFACTRule) rule;
			
			EDIRuleSegment segment = edifactRule.nextSegment("UNH");
			assertNotNull("", segment);
			assertEquals("", "Root/UNH", segment.getPath());
			
			segment = edifactRule.nextSegment("BGM");
			assertNotNull("", segment);
			assertEquals("", "Root/BGM", segment.getPath());
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
