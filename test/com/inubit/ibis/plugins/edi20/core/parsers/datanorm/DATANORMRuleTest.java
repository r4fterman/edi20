package com.inubit.ibis.plugins.edi20.core.parsers.datanorm;

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
import com.inubit.ibis.plugins.edi20.core.parsers.datanorm.DATANORMRule;
import com.inubit.ibis.utils.InubitException;
import com.inubit.ibis.utils.XmlUtils;

/**
 * @author r4fter
 * 
 */
public class DATANORMRuleTest {

	private Document getRuleDocument(final String ruleDocumentName) {
		try {
			URL url = DATANORMRuleTest.class.getResource(ruleDocumentName);
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
	public void testEDIFACTRule_DATANORM_ALL_4_0() {
		try {
			Document ruleDoc = getRuleDocument("DATANORM-All-4.0--.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new DATANORMRule(ruleDoc);
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
			Document ruleDoc = getRuleDocument("DATANORM-All-4.0--.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new DATANORMRule(ruleDoc);
			assertNotNull(rule);
			assertEquals("-", rule.getAgency());
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
			Document ruleDoc = getRuleDocument("DATANORM-All-4.0--.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new DATANORMRule(ruleDoc);
			assertNotNull(rule);
			assertEquals("DATANORM segments ", rule.getDescription());
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
			Document ruleDoc = getRuleDocument("DATANORM-All-4.0--.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new DATANORMRule(ruleDoc);
			assertNotNull(rule);
			assertEquals("mswed", rule.getLayout());
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
			Document ruleDoc = getRuleDocument("DATANORM-All-4.0--.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new DATANORMRule(ruleDoc);
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
			Document ruleDoc = getRuleDocument("DATANORM-All-4.0--.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new DATANORMRule(ruleDoc);
			assertNotNull(rule);
			assertEquals("DATANORM", rule.getStandard());
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
			Document ruleDoc = getRuleDocument("DATANORM-All-4.0--.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new DATANORMRule(ruleDoc);
			assertNotNull(rule);
			assertEquals("All", rule.getType());
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
			Document ruleDoc = getRuleDocument("DATANORM-All-4.0--.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new DATANORMRule(ruleDoc);
			assertNotNull(rule);
			assertEquals("4.0", rule.getVersion());
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
			Document ruleDoc = getRuleDocument("DATANORM-All-4.0--.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new DATANORMRule(ruleDoc);
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
			Document ruleDoc = getRuleDocument("DATANORM-All-4.0--.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new DATANORMRule(ruleDoc);
			assertNotNull(rule);
			assertEquals("DATANORM-All-4.0----", rule.toString());
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
