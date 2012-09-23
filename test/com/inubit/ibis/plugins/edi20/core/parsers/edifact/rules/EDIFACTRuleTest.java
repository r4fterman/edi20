package com.inubit.ibis.plugins.edi20.core.parsers.edifact.rules;

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
import com.inubit.ibis.plugins.edi20.core.parsers.edifact.rules.EDIFACTRule;
import com.inubit.ibis.utils.InubitException;
import com.inubit.ibis.utils.XmlUtils;

/**
 * @author r4fter
 * 
 */
public class EDIFACTRuleTest {

	private Document getRuleDocument(final String ruleDocumentName) {
		try {
			URL url = EDIFACTRuleTest.class.getResource(ruleDocumentName);
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
	public void testEDIFACTRule_IFCSUM_D_96A() {
		try {
			Document ruleDoc = getRuleDocument("EDIFACT-IFCSUM-D-96A.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
			assertNotNull(rule);
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testEDIFACTRule_APERAK_D_96A() {
		try {
			Document ruleDoc = getRuleDocument("EDIFACT-APERAK-D-96A.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
			assertNotNull(rule);
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testEDIFACTRule_DELFOR_D_96A() {
		try {
			Document ruleDoc = getRuleDocument("EDIFACT-DELFOR-D-96A.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
			assertNotNull(rule);
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	@Test
	public void testEDIFACTRule_DELFOR_D_99B() {
		try {
			Document ruleDoc = getRuleDocument("EDIFACT-DELFOR-D-99B.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
			assertNotNull(rule);
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	@Test
	public void testEDIFACTRule_DESADV_D_96A() {
		try {
			Document ruleDoc = getRuleDocument("EDIFACT-DESADV-D-96A.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
			assertNotNull(rule);
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	@Test
	public void testEDIFACTRule_GENRAL_D_96A() {
		try {
			Document ruleDoc = getRuleDocument("EDIFACT-GENRAL-D-96A.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
			assertNotNull(rule);
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	@Test
	public void testEDIFACTRule_IFTMAN_D_96A() {
		try {
			Document ruleDoc = getRuleDocument("EDIFACT-IFTMAN-D-96A.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
			assertNotNull(rule);
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	@Test
	public void testEDIFACTRule_IFTMIN_D_93A() {
		try {
			Document ruleDoc = getRuleDocument("EDIFACT-IFTMIN-D-93A.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
			assertNotNull(rule);
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	@Test
	public void testEDIFACTRule_IFTSTA_D_96A() {
		try {
			Document ruleDoc = getRuleDocument("EDIFACT-IFTSTA-D-96A.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
			assertNotNull(rule);
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	@Test
	public void testEDIFACTRule_INVOIC_D_96A() {
		try {
			Document ruleDoc = getRuleDocument("EDIFACT-INVOIC-D-96A.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
			assertNotNull(rule);
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	@Test
	public void testEDIFACTRule_INVOIC_D_93A() {
		try {
			Document ruleDoc = getRuleDocument("EDIFACT-INVOIC-D-93A.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
			assertNotNull(rule);
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	@Test
	public void testEDIFACTRule_INVOIC_D_95A() {
		try {
			Document ruleDoc = getRuleDocument("EDIFACT-INVOIC-D-95A.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
			assertNotNull(rule);
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	@Test
	public void testEDIFACTRule_MSCONS_D_04B_2_1() {
		try {
			Document ruleDoc = getRuleDocument("EDIFACT-MSCONS-D-04B-UN-2.1.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
			assertNotNull(rule);
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	@Test
	public void testEDIFACTRule_MSCONS_D_04B() {
		try {
			Document ruleDoc = getRuleDocument("EDIFACT-MSCONS-D-04B.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
			assertNotNull(rule);
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	@Test
	public void testEDIFACTRule_ORDERS_D_96A() {
		try {
			Document ruleDoc = getRuleDocument("EDIFACT-ORDERS-D-96A.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
			assertNotNull(rule);
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	@Test
	public void testEDIFACTRule_PAYMUL_D_96A() {
		try {
			Document ruleDoc = getRuleDocument("EDIFACT-PAYMUL-D-96A.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
			assertNotNull(rule);
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	@Test
	public void testEDIFACTRule_PRICAT_D_96A() {
		try {
			Document ruleDoc = getRuleDocument("EDIFACT-PRICAT-D-96A.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
			assertNotNull(rule);
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	@Test
	public void testEDIFACTRule_PRODAT_D_96B() {
		try {
			Document ruleDoc = getRuleDocument("EDIFACT-PRODAT-D-96B.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
			assertNotNull(rule);
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	@Test
	public void testEDIFACTRule_REMADV_D_96A() {
		try {
			Document ruleDoc = getRuleDocument("EDIFACT-REMADV-D-96A.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
			assertNotNull(rule);
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	@Test
	public void testEDIFACTRule_SSQNOT_5_0() {
		try {
			Document ruleDoc = getRuleDocument("EDIGAS-SSQNOT-5.0.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new EDIGASRule(ruleDoc);
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
			Document ruleDoc = getRuleDocument("EDIFACT-IFCSUM-D-96A.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
			assertNotNull(rule);
			assertEquals("UN", rule.getAgency());
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
			Document ruleDoc = getRuleDocument("EDIFACT-IFCSUM-D-96A.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
			assertNotNull(rule);
			assertEquals("Forwarding and Consolidation Summary Message", rule.getDescription());
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
			Document ruleDoc = getRuleDocument("EDIFACT-IFCSUM-D-96A.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
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
			Document ruleDoc = getRuleDocument("EDIFACT-IFCSUM-D-96A.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
			assertNotNull(rule);
			assertEquals("96A", rule.getRelease());
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
			Document ruleDoc = getRuleDocument("EDIFACT-IFCSUM-D-96A.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
			assertNotNull(rule);
			assertEquals("EDIFACT", rule.getStandard());
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
			Document ruleDoc = getRuleDocument("EDIFACT-IFCSUM-D-96A.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
			assertNotNull(rule);
			assertEquals("IFCSUM", rule.getType());
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
			Document ruleDoc = getRuleDocument("EDIFACT-IFCSUM-D-96A.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
			assertNotNull(rule);
			assertEquals("D", rule.getVersion());
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
			Document ruleDoc = getRuleDocument("EDIFACT-IFCSUM-D-96A.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
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
			Document ruleDoc = getRuleDocument("EDIFACT-IFCSUM-D-96A.xml");
			assertNotNull(ruleDoc);
			AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
			assertNotNull(rule);
			assertEquals("EDIFACT-IFCSUM-D-96A-UN", rule.toString());
		} catch (InubitException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
