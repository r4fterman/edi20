package com.inubit.ibis.plugins.edi20.core.parsers.hl7;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.junit.Test;

import com.inubit.ibis.plugins.edi20.commons.rules.AbstractEDIRule;
import com.inubit.ibis.plugins.edi20.core.parsers.hl7.HL7Rule;
import com.inubit.ibis.utils.InubitException;
import com.inubit.ibis.utils.XmlUtils;

/**
 * @author r4fter
 * 
 */
public class HL7RuleTest {

	private Document getRuleDocument(final String ruleDocumentName) {
		try {
			URL url = HL7RuleTest.class.getResource(ruleDocumentName);
			assertNotNull("File not found: " + ruleDocumentName, url);
			File xmlFile = new File(url.toURI());
			return XmlUtils.getDocumentThrowing(xmlFile);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (DocumentException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		return null;
	}

	/**
	 * Test method for
	 * {@link com.inubit.ibis.plugins.edi20.commons.rules.AbstractEDIRule#EDIFACTRule(org.dom4j.Document)}
	 * .
	 */
	@Test
	public void testEDIFACTRule_HL7_SRM_S01_v231() {
		try {
			Document ruleDoc = getRuleDocument("HL7-SRM_S01-v231--.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new HL7Rule(ruleDoc);
			assertNotNull(rule);
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	@Test
	public void testEDIFACTRule_HL7_SRM_2_3() {
		try {
			Document ruleDoc = getRuleDocument("HL7-SRM-2.3--.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new HL7Rule(ruleDoc);
			assertNotNull(rule);
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	/**
	 * Test method for
	 * {@link com.inubit.ibis.plugins.edi20.commons.rules.AbstractEDIRule#getAgency()}
	 * .
	 */
	@Test
	public void testGetAgency() {
		try {
			Document ruleDoc = getRuleDocument("HL7-SRM_S01-v231--.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new HL7Rule(ruleDoc);
			assertNotNull(rule);
			assertEquals("", rule.getAgency());
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link com.inubit.ibis.plugins.edi20.commons.rules.AbstractEDIRule#getDescription()}
	 * .
	 */
	@Test
	public void testGetDescription() {
		try {
			Document ruleDoc = getRuleDocument("HL7-SRM_S01-v231--.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new HL7Rule(ruleDoc);
			assertNotNull(rule);
			assertEquals("SRM/SRR - Request new appointment booking", rule.getDescription());
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link com.inubit.ibis.plugins.edi20.commons.rules.AbstractEDIRule#getLayout()}
	 * .
	 */
	@Test
	public void testGetLayout() {
		try {
			Document ruleDoc = getRuleDocument("HL7-SRM_S01-v231--.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new HL7Rule(ruleDoc);
			assertNotNull(rule);
			assertEquals("hwed", rule.getLayout());
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link com.inubit.ibis.plugins.edi20.commons.rules.AbstractEDIRule#getRelease()}
	 * .
	 */
	@Test
	public void testGetRelease() {
		try {
			Document ruleDoc = getRuleDocument("HL7-SRM_S01-v231--.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new HL7Rule(ruleDoc);
			assertNotNull(rule);
			assertEquals("-", rule.getRelease());
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link com.inubit.ibis.plugins.edi20.commons.rules.AbstractEDIRule#getStandard()}
	 * .
	 */
	@Test
	public void testGetStandard() {
		try {
			Document ruleDoc = getRuleDocument("HL7-SRM_S01-v231--.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new HL7Rule(ruleDoc);
			assertNotNull(rule);
			assertEquals("HL7", rule.getStandard());
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link com.inubit.ibis.plugins.edi20.commons.rules.AbstractEDIRule#getType()}
	 * .
	 */
	@Test
	public void testGetType() {
		try {
			Document ruleDoc = getRuleDocument("HL7-SRM_S01-v231--.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new HL7Rule(ruleDoc);
			assertNotNull(rule);
			assertEquals("SRM_S01", rule.getType());
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link com.inubit.ibis.plugins.edi20.commons.rules.AbstractEDIRule#getVersion()}
	 * .
	 */
	@Test
	public void testGetVersion() {
		try {
			Document ruleDoc = getRuleDocument("HL7-SRM_S01-v231--.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new HL7Rule(ruleDoc);
			assertNotNull(rule);
			assertEquals("v231", rule.getVersion());
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link com.inubit.ibis.plugins.edi20.commons.rules.AbstractEDIRule#hasMoreRuleTokens()}
	 * .
	 */
	@Test
	public void testHasMoreRuleTokens() {
		try {
			Document ruleDoc = getRuleDocument("HL7-SRM_S01-v231--.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new HL7Rule(ruleDoc);
			assertNotNull(rule);
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link com.inubit.ibis.plugins.edi20.commons.rules.AbstractEDIRule#toString()}
	 * .
	 */
	@Test
	public void testToString() {
		try {
			Document ruleDoc = getRuleDocument("HL7-SRM_S01-v231--.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new HL7Rule(ruleDoc);
			assertNotNull(rule);
			assertEquals("HL7-SRM_S01-v231---", rule.toString());
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
