package com.inubit.ibis.plugins.edi20.parsers.datanorm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.net.URL;

import com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule;
import com.inubit.ibis.utils.XmlUtils;
import org.dom4j.Document;
import org.junit.Test;

/**
 * @author r4fter
 */
public class DATANORMRuleTest {

    private Document getRuleDocument(final String ruleDocumentName) throws Exception {
        URL url = DATANORMRuleTest.class.getResource(ruleDocumentName);
        assertNotNull("File not found: " + ruleDocumentName, url);
        File xmlFile = new File(url.toURI());
        return XmlUtils.getDocumentThrowing(xmlFile);
    }

    @Test
    public void testEDIFACTRule_DATANORM_ALL_4_0() throws Exception {
        Document ruleDoc = getRuleDocument("DATANORM-All-4.0--.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new DATANORMRule(ruleDoc);
        assertNotNull(rule);
    }

    @Test
    public void testGetAgency() throws Exception {
        Document ruleDoc = getRuleDocument("DATANORM-All-4.0--.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new DATANORMRule(ruleDoc);
        assertNotNull(rule);
        assertEquals("-", rule.getAgency());
    }

    @Test
    public void testGetDescription() throws Exception {
        Document ruleDoc = getRuleDocument("DATANORM-All-4.0--.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new DATANORMRule(ruleDoc);
        assertNotNull(rule);
        assertEquals("DATANORM segments ", rule.getDescription());
    }

    @Test
    public void testGetLayout() throws Exception {
        Document ruleDoc = getRuleDocument("DATANORM-All-4.0--.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new DATANORMRule(ruleDoc);
        assertNotNull(rule);
        assertEquals("mswed", rule.getLayout());
    }

    @Test
    public void testGetRelease() throws Exception {
        Document ruleDoc = getRuleDocument("DATANORM-All-4.0--.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new DATANORMRule(ruleDoc);
        assertNotNull(rule);
        assertEquals("-", rule.getRelease());
    }

    @Test
    public void testGetStandard() throws Exception {
        Document ruleDoc = getRuleDocument("DATANORM-All-4.0--.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new DATANORMRule(ruleDoc);
        assertNotNull(rule);
        assertEquals("DATANORM", rule.getStandard());
    }

    @Test
    public void testGetType() throws Exception {
        Document ruleDoc = getRuleDocument("DATANORM-All-4.0--.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new DATANORMRule(ruleDoc);
        assertNotNull(rule);
        assertEquals("All", rule.getType());
    }

    @Test
    public void testGetVersion() throws Exception {
        Document ruleDoc = getRuleDocument("DATANORM-All-4.0--.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new DATANORMRule(ruleDoc);
        assertNotNull(rule);
        assertEquals("4.0", rule.getVersion());
    }

    @Test
    public void testHasMoreRuleTokens() throws Exception {
        Document ruleDoc = getRuleDocument("DATANORM-All-4.0--.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new DATANORMRule(ruleDoc);
        assertNotNull(rule);
    }

    @Test
    public void testToString() throws Exception {
        Document ruleDoc = getRuleDocument("DATANORM-All-4.0--.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new DATANORMRule(ruleDoc);
        assertNotNull(rule);
        assertEquals("DATANORM-All-4.0----", rule.toString());
    }

}
