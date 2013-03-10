package com.inubit.ibis.plugins.edi20.rules;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import com.inubit.ibis.utils.XmlUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.junit.Test;

/**
 * @author r4fter
 */
public class EDIFACTRuleTest {

    private Document getRuleDocument(final String ruleDocumentName) throws URISyntaxException, DocumentException {
        URL url = EDIFACTRuleTest.class.getResource(ruleDocumentName);
        assertNotNull("File not found: " + ruleDocumentName, url);
        File xmlFile = new File(url.toURI());
        return XmlUtils.getDocumentThrowing(xmlFile);
    }

    @Test
    public void testEDIFACTRule_IFCSUM_D_96A() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-IFCSUM-D-96A.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertNotNull(rule);
    }

    @Test
    public void testEDIFACTRule_APERAK_D_96A() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-APERAK-D-96A.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertNotNull(rule);
    }

    @Test
    public void testEDIFACTRule_DELFOR_D_96A() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-DELFOR-D-96A.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertNotNull(rule);
    }

    @Test
    public void testEDIFACTRule_DELFOR_D_99B() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-DELFOR-D-99B.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertNotNull(rule);
    }

    @Test
    public void testEDIFACTRule_DESADV_D_96A() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-DESADV-D-96A.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertNotNull(rule);
    }

    @Test
    public void testEDIFACTRule_GENRAL_D_96A() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-GENRAL-D-96A.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertNotNull(rule);
    }

    @Test
    public void testEDIFACTRule_IFTMAN_D_96A() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-IFTMAN-D-96A.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertNotNull(rule);
    }

    @Test
    public void testEDIFACTRule_IFTMIN_D_93A() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-IFTMIN-D-93A.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertNotNull(rule);
    }

    @Test
    public void testEDIFACTRule_IFTSTA_D_96A() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-IFTSTA-D-96A.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertNotNull(rule);
    }

    @Test
    public void testEDIFACTRule_INVOIC_D_96A() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-INVOIC-D-96A.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertNotNull(rule);
    }

    @Test
    public void testEDIFACTRule_INVOIC_D_93A() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-INVOIC-D-93A.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertNotNull(rule);
    }

    @Test
    public void testEDIFACTRule_INVOIC_D_95A() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-INVOIC-D-95A.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertNotNull(rule);
    }

    @Test
    public void testEDIFACTRule_MSCONS_D_04B_2_1() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-MSCONS-D-04B-UN-2.1.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertNotNull(rule);
    }

    @Test
    public void testEDIFACTRule_MSCONS_D_04B() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-MSCONS-D-04B.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertNotNull(rule);
    }

    @Test
    public void testEDIFACTRule_ORDERS_D_96A() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-ORDERS-D-96A.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertNotNull(rule);
    }

    @Test
    public void testEDIFACTRule_PAYMUL_D_96A() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-PAYMUL-D-96A.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertNotNull(rule);
    }

    @Test
    public void testEDIFACTRule_PRICAT_D_96A() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-PRICAT-D-96A.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertNotNull(rule);
    }

    @Test
    public void testEDIFACTRule_PRODAT_D_96B() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-PRODAT-D-96B.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertNotNull(rule);
    }

    @Test
    public void testEDIFACTRule_REMADV_D_96A() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-REMADV-D-96A.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertNotNull(rule);
    }

    @Test
    public void testEDIFACTRule_SSQNOT_5_0() throws Exception {
        Document ruleDoc = getRuleDocument("EDIGAS-SSQNOT-5.0.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new EDIGASRule(ruleDoc);
        assertNotNull(rule);
    }

    @Test
    public void testGetAgency() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-IFCSUM-D-96A.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertNotNull(rule);
        assertEquals("UN", rule.getAgency());
    }

    @Test
    public void testGetDescription() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-IFCSUM-D-96A.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertNotNull(rule);
        assertEquals("Forwarding and Consolidation Summary Message", rule.getDescription());
    }

    @Test
    public void testGetLayout() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-IFCSUM-D-96A.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertNotNull(rule);
        assertEquals("hwed", rule.getLayout());
    }

    @Test
    public void testGetRelease() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-IFCSUM-D-96A.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertNotNull(rule);
        assertEquals("96A", rule.getRelease());
    }

    @Test
    public void testGetStandard() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-IFCSUM-D-96A.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertNotNull(rule);
        assertEquals("EDIFACT", rule.getStandard());
    }

    @Test
    public void testGetType() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-IFCSUM-D-96A.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertNotNull(rule);
        assertEquals("IFCSUM", rule.getType());
    }

    @Test
    public void testGetVersion() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-IFCSUM-D-96A.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertNotNull(rule);
        assertEquals("D", rule.getVersion());
    }

    @Test
    public void testHasMoreRuleTokens() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-IFCSUM-D-96A.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertNotNull(rule);
    }

    @Test
    public void testToString() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-IFCSUM-D-96A.xml");
        assertNotNull(ruleDoc);
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertNotNull(rule);
        assertEquals("EDIFACT-IFCSUM-D-96A-UN", rule.toString());
    }

}
