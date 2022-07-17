package com.inubit.ibis.plugins.edi20.rules;

import com.inubit.ibis.utils.XmlUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

class EDIFACTRuleTest {

    private Document getRuleDocument(final String ruleDocumentName) throws URISyntaxException, DocumentException {
        URL url = EDIFACTRuleTest.class.getResource(ruleDocumentName);
        assertThat("File not found: " + ruleDocumentName, url, not(nullValue()));
        File xmlFile = new File(url.toURI());
        return XmlUtils.getDocumentThrowing(xmlFile);
    }

    @Test
    void testEDIFACTRule_IFCSUM_D_96A() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-IFCSUM-D-96A.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testEDIFACTRule_APERAK_D_96A() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-APERAK-D-96A.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testEDIFACTRule_DELFOR_D_96A() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-DELFOR-D-96A.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testEDIFACTRule_DELFOR_D_99B() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-DELFOR-D-99B.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testEDIFACTRule_DESADV_D_96A() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-DESADV-D-96A.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testEDIFACTRule_GENRAL_D_96A() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-GENRAL-D-96A.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testEDIFACTRule_IFTMAN_D_96A() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-IFTMAN-D-96A.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testEDIFACTRule_IFTMIN_D_93A() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-IFTMIN-D-93A.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testEDIFACTRule_IFTSTA_D_96A() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-IFTSTA-D-96A.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testEDIFACTRule_INVOIC_D_96A() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-INVOIC-D-96A.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testEDIFACTRule_INVOIC_D_93A() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-INVOIC-D-93A.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testEDIFACTRule_INVOIC_D_95A() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-INVOIC-D-95A.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testEDIFACTRule_MSCONS_D_04B_2_1() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-MSCONS-D-04B-UN-2.1.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testEDIFACTRule_MSCONS_D_04B() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-MSCONS-D-04B.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testEDIFACTRule_ORDERS_D_96A() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-ORDERS-D-96A.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testEDIFACTRule_PAYMUL_D_96A() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-PAYMUL-D-96A.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testEDIFACTRule_PRICAT_D_96A() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-PRICAT-D-96A.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testEDIFACTRule_PRODAT_D_96B() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-PRODAT-D-96B.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testEDIFACTRule_REMADV_D_96A() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-REMADV-D-96A.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testEDIFACTRule_SSQNOT_5_0() throws Exception {
        Document ruleDoc = getRuleDocument("EDIGAS-SSQNOT-5.0.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new EDIGASRule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testGetAgency() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-IFCSUM-D-96A.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getAgency(), is("UN"));
    }

    @Test
    void testGetDescription() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-IFCSUM-D-96A.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getDescription(), is("Forwarding and Consolidation Summary Message"));
    }

    @Test
    void testGetLayout() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-IFCSUM-D-96A.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getLayout(), is("hwed"));
    }

    @Test
    void testGetRelease() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-IFCSUM-D-96A.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getRelease(), is("96A"));
    }

    @Test
    void testGetStandard() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-IFCSUM-D-96A.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getStandard(), is("EDIFACT"));
    }

    @Test
    void testGetType() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-IFCSUM-D-96A.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getType(), is("IFCSUM"));
    }

    @Test
    void testGetVersion() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-IFCSUM-D-96A.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getVersion(), is("D"));
    }

    @Test
    void testHasMoreRuleTokens() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-IFCSUM-D-96A.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testToString() throws Exception {
        Document ruleDoc = getRuleDocument("EDIFACT-IFCSUM-D-96A.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new EDIFACTRule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.toString(), is("EDIFACT-IFCSUM-D-96A-UN"));
    }

}
