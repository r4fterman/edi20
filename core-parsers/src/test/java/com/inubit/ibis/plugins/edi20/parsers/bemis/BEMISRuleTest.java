package com.inubit.ibis.plugins.edi20.parsers.bemis;

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
public class BEMISRuleTest {

    private Document getRuleDocument(final String ruleDocumentName) {
        try {
            URL url = BEMISRuleTest.class.getResource(ruleDocumentName);
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
    public void testEDIFACTRule_BEMIS_7135_IN_1_0_A() {
        try {
            Document ruleDoc = getRuleDocument("BEMIS-7135-IN-1.0-A.xml");
            assertNotNull(ruleDoc);
            AbstractEDIRule rule = new BEMISRule(ruleDoc);
            assertNotNull(rule);
        } catch (InubitException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testEDIFACTRule_BEMIS_7135_OUT_1_0_A() {
        try {
            Document ruleDoc = getRuleDocument("BEMIS-7135-OUT-1.0-A.xml");
            assertNotNull(ruleDoc);
            AbstractEDIRule rule = new BEMISRule(ruleDoc);
            assertNotNull(rule);
        } catch (InubitException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testEDIFACTRule_BEMIS_7170_IN_1_0_A() {
        try {
            Document ruleDoc = getRuleDocument("BEMIS-7170-IN-1.0-A.xml");
            assertNotNull(ruleDoc);
            AbstractEDIRule rule = new BEMISRule(ruleDoc);
            assertNotNull(rule);
        } catch (InubitException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testEDIFACTRule_BEMIS_7170_OUT_1_0_A() {
        try {
            Document ruleDoc = getRuleDocument("BEMIS-7170-OUT-1.0-A.xml");
            assertNotNull(ruleDoc);
            AbstractEDIRule rule = new BEMISRule(ruleDoc);
            assertNotNull(rule);
        } catch (InubitException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testEDIFACTRule_BEMIS_7255_IN_1_0_A() {
        try {
            Document ruleDoc = getRuleDocument("BEMIS-7255-IN-1.0-A.xml");
            assertNotNull(ruleDoc);
            AbstractEDIRule rule = new BEMISRule(ruleDoc);
            assertNotNull(rule);
        } catch (InubitException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testEDIFACTRule_BEMIS_7255_OUT_1_0_A() {
        try {
            Document ruleDoc = getRuleDocument("BEMIS-7255-OUT-1.0-A.xml");
            assertNotNull(ruleDoc);
            AbstractEDIRule rule = new BEMISRule(ruleDoc);
            assertNotNull(rule);
        } catch (InubitException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testEDIFACTRule_BEMIS_7280_IN_1_0_A() {
        try {
            Document ruleDoc = getRuleDocument("BEMIS-7280-IN-1.0-A.xml");
            assertNotNull(ruleDoc);
            AbstractEDIRule rule = new BEMISRule(ruleDoc);
            assertNotNull(rule);
        } catch (InubitException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testEDIFACTRule_BEMIS_7280_OUT_1_0_A() {
        try {
            Document ruleDoc = getRuleDocument("BEMIS-7280-OUT-1.0-A.xml");
            assertNotNull(ruleDoc);
            AbstractEDIRule rule = new BEMISRule(ruleDoc);
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
            Document ruleDoc = getRuleDocument("BEMIS-7135-IN-1.0-A.xml");
            assertNotNull(ruleDoc);
            AbstractEDIRule rule = new BEMISRule(ruleDoc);
            assertNotNull(rule);
            assertEquals("BAAN Electronic Message Interchange System", rule.getAgency());
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
            Document ruleDoc = getRuleDocument("BEMIS-7135-IN-1.0-A.xml");
            assertNotNull(ruleDoc);
            AbstractEDIRule rule = new BEMISRule(ruleDoc);
            assertNotNull(rule);
            assertEquals("Trading Invoice", rule.getDescription());
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
            Document ruleDoc = getRuleDocument("BEMIS-7135-IN-1.0-A.xml");
            assertNotNull(ruleDoc);
            AbstractEDIRule rule = new BEMISRule(ruleDoc);
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
            Document ruleDoc = getRuleDocument("BEMIS-7135-IN-1.0-A.xml");
            assertNotNull(ruleDoc);
            AbstractEDIRule rule = new BEMISRule(ruleDoc);
            assertNotNull(rule);
            assertEquals("A", rule.getRelease());
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
            Document ruleDoc = getRuleDocument("BEMIS-7135-IN-1.0-A.xml");
            assertNotNull(ruleDoc);
            AbstractEDIRule rule = new BEMISRule(ruleDoc);
            assertNotNull(rule);
            assertEquals("BEMIS", rule.getStandard());
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
            Document ruleDoc = getRuleDocument("BEMIS-7135-IN-1.0-A.xml");
            assertNotNull(ruleDoc);
            AbstractEDIRule rule = new BEMISRule(ruleDoc);
            assertNotNull(rule);
            assertEquals("7135-IN", rule.getType());
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
            Document ruleDoc = getRuleDocument("BEMIS-7135-IN-1.0-A.xml");
            assertNotNull(ruleDoc);
            AbstractEDIRule rule = new BEMISRule(ruleDoc);
            assertNotNull(rule);
            assertEquals("1.0", rule.getVersion());
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
            Document ruleDoc = getRuleDocument("BEMIS-7135-IN-1.0-A.xml");
            assertNotNull(ruleDoc);
            AbstractEDIRule rule = new BEMISRule(ruleDoc);
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
            Document ruleDoc = getRuleDocument("BEMIS-7135-IN-1.0-A.xml");
            assertNotNull(ruleDoc);
            AbstractEDIRule rule = new BEMISRule(ruleDoc);
            assertNotNull(rule);
            assertEquals("BEMIS-7135-IN-1.0-A-BAAN Electronic Message Interchange System", rule.toString());
        } catch (InubitException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

}
