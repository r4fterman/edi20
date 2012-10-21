package com.inubit.ibis.plugins.edi20.core.parsers.vda.rules;

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
import com.inubit.ibis.plugins.edi20.core.parsers.vda.rules.VDARule;
import com.inubit.ibis.utils.InubitException;
import com.inubit.ibis.utils.XmlUtils;

/**
 * @author r4fter
 * 
 */
public class VDARuleTest {

	private Document getRuleDocument(final String ruleDocumentName) {
		try {
			URL url = VDARuleTest.class.getResource(ruleDocumentName);
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
	public void testEDIFACTRule_VDA_4905_1() {
		try {
			Document ruleDoc = getRuleDocument("VDA-4905-1--.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new VDARule(ruleDoc);
			assertNotNull(rule);
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testEDIFACTRule_VDA_4913_4() {
		try {
			Document ruleDoc = getRuleDocument("VDA-4913-4--.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new VDARule(ruleDoc);
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
			Document ruleDoc = getRuleDocument("VDA-4905-1--.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new VDARule(ruleDoc);
			assertNotNull(rule);
			assertEquals("VDA", rule.getAgency());
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
			Document ruleDoc = getRuleDocument("VDA-4905-1--.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new VDARule(ruleDoc);
			assertNotNull(rule);
			assertEquals("Delivery instruction VDA 4905/1", rule.getDescription());
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
			Document ruleDoc = getRuleDocument("VDA-4905-1--.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new VDARule(ruleDoc);
			assertNotNull(rule);
			assertEquals("hwfpe", rule.getLayout());
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
			Document ruleDoc = getRuleDocument("VDA-4905-1--.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new VDARule(ruleDoc);
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
			Document ruleDoc = getRuleDocument("VDA-4905-1--.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new VDARule(ruleDoc);
			assertNotNull(rule);
			assertEquals("VDA", rule.getStandard());
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
			Document ruleDoc = getRuleDocument("VDA-4905-1--.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new VDARule(ruleDoc);
			assertNotNull(rule);
			assertEquals("4905", rule.getType());
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
			Document ruleDoc = getRuleDocument("VDA-4905-1--.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new VDARule(ruleDoc);
			assertNotNull(rule);
			assertEquals("1", rule.getVersion());
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
			Document ruleDoc = getRuleDocument("VDA-4905-1--.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new VDARule(ruleDoc);
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
			Document ruleDoc = getRuleDocument("VDA-4905-1--.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new VDARule(ruleDoc);
			assertNotNull(rule);
			assertEquals("VDA-4905-1---VDA", rule.toString());
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
