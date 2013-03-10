package com.inubit.ibis.plugins.edi20.parsers.x12;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule;
import com.inubit.ibis.utils.InubitException;
import com.inubit.ibis.utils.XmlUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.junit.Test;

/**
 * @author r4fter
 */
public class X12RuleTest {

    private Document getRuleDocument(final String ruleDocumentName) {
        try {
            URL url = X12RuleTest.class.getResource(ruleDocumentName);
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
     * Test method for {@link com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule#EDIFACTRule(org.dom4j.Document)} .
     */
    @Test
    public void testEDIFACTRule_X12_832_03_030() {
        try {
            Document ruleDoc = getRuleDocument("X12-832-03-030.xml");
            assertNotNull(ruleDoc);
            AbstractEDIRule rule = new X12Rule(ruleDoc);
            assertNotNull(rule);
        } catch (InubitException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testEDIFACTRule_X12_832_04_010() {
        try {
            Document ruleDoc = getRuleDocument("X12-832-04-010.xml");
            assertNotNull(ruleDoc);
            AbstractEDIRule rule = new X12Rule(ruleDoc);
            assertNotNull(rule);
        } catch (InubitException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testEDIFACTRule_X12_850_03_030() {
        try {
            Document ruleDoc = getRuleDocument("X12-850-03-030.xml");
            assertNotNull(ruleDoc);
            AbstractEDIRule rule = new X12Rule(ruleDoc);
            assertNotNull(rule);
        } catch (InubitException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testEDIFACTRule_X12_850_04_010() {
        try {
            Document ruleDoc = getRuleDocument("X12-850-04-010.xml");
            assertNotNull(ruleDoc);
            AbstractEDIRule rule = new X12Rule(ruleDoc);
            assertNotNull(rule);
        } catch (InubitException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    /**
     * Test method for {@link com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule#getAgency()} .
     */
    @Test
    public void testGetAgency() {
        try {
            Document ruleDoc = getRuleDocument("X12-832-03-030.xml");
            assertNotNull(ruleDoc);
            AbstractEDIRule rule = new X12Rule(ruleDoc);
            assertNotNull(rule);
            assertEquals("X", rule.getAgency());
        } catch (InubitException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    /**
     * Test method for {@link com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule#getDescription()} .
     */
    @Test
    public void testGetDescription() {
        try {
            Document ruleDoc = getRuleDocument("X12-832-03-030.xml");
            assertNotNull(ruleDoc);
            AbstractEDIRule rule = new X12Rule(ruleDoc);
            assertNotNull(rule);
            assertEquals("Price/Sales Catalog", rule.getDescription());
        } catch (InubitException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    /**
     * Test method for {@link com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule#getLayout()} .
     */
    @Test
    public void testGetLayout() {
        try {
            Document ruleDoc = getRuleDocument("X12-832-03-030.xml");
            assertNotNull(ruleDoc);
            AbstractEDIRule rule = new X12Rule(ruleDoc);
            assertNotNull(rule);
            assertEquals("hwed", rule.getLayout());
        } catch (InubitException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    /**
     * Test method for {@link com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule#getRelease()} .
     */
    @Test
    public void testGetRelease() {
        try {
            Document ruleDoc = getRuleDocument("X12-832-03-030.xml");
            assertNotNull(ruleDoc);
            AbstractEDIRule rule = new X12Rule(ruleDoc);
            assertNotNull(rule);
            assertEquals("030", rule.getRelease());
        } catch (InubitException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    /**
     * Test method for {@link com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule#getStandard()} .
     */
    @Test
    public void testGetStandard() {
        try {
            Document ruleDoc = getRuleDocument("X12-832-03-030.xml");
            assertNotNull(ruleDoc);
            AbstractEDIRule rule = new X12Rule(ruleDoc);
            assertNotNull(rule);
            assertEquals("X12", rule.getStandard());
        } catch (InubitException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    /**
     * Test method for {@link com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule#getType()} .
     */
    @Test
    public void testGetType() {
        try {
            Document ruleDoc = getRuleDocument("X12-832-03-030.xml");
            assertNotNull(ruleDoc);
            AbstractEDIRule rule = new X12Rule(ruleDoc);
            assertNotNull(rule);
            assertEquals("832", rule.getType());
        } catch (InubitException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    /**
     * Test method for {@link com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule#getVersion()} .
     */
    @Test
    public void testGetVersion() {
        try {
            Document ruleDoc = getRuleDocument("X12-832-03-030.xml");
            assertNotNull(ruleDoc);
            AbstractEDIRule rule = new X12Rule(ruleDoc);
            assertNotNull(rule);
            assertEquals("03", rule.getVersion());
        } catch (InubitException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    /**
     * Test method for {@link com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule#hasMoreRuleTokens()} .
     */
    @Test
    public void testHasMoreRuleTokens() {
        try {
            Document ruleDoc = getRuleDocument("X12-832-03-030.xml");
            assertNotNull(ruleDoc);
            AbstractEDIRule rule = new X12Rule(ruleDoc);
            assertNotNull(rule);
        } catch (InubitException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    /**
     * Test method for {@link com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule#toString()} .
     */
    @Test
    public void testToString() {
        try {
            Document ruleDoc = getRuleDocument("X12-832-03-030.xml");
            assertNotNull(ruleDoc);
            AbstractEDIRule rule = new X12Rule(ruleDoc);
            assertNotNull(rule);
            assertEquals("X12-832-03-030-X", rule.toString());
        } catch (InubitException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

}
