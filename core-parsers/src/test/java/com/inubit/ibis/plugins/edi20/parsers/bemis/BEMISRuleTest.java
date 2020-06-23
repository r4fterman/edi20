package com.inubit.ibis.plugins.edi20.parsers.bemis;

import com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule;
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
import static org.hamcrest.junit.MatcherAssert.assertThat;

class BEMISRuleTest {

    private Document getRuleDocument(final String ruleDocumentName) throws URISyntaxException, DocumentException {
        final URL url = BEMISRuleTest.class.getResource(ruleDocumentName);
        assertThat("File not found: " + ruleDocumentName, url, not(nullValue()));
        final File xmlFile = new File(url.toURI());
        return XmlUtils.getDocumentThrowing(xmlFile);
    }

    @Test
    void testEDIFACTRule_BEMIS_7135_IN_1_0_A() throws Exception {
        final Document ruleDoc = getRuleDocument("BEMIS-7135-IN-1.0-A.xml");
        assertThat(ruleDoc, not(nullValue()));
        final AbstractEDIRule rule = new BEMISRule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testEDIFACTRule_BEMIS_7135_OUT_1_0_A() throws Exception {
        final Document ruleDoc = getRuleDocument("BEMIS-7135-OUT-1.0-A.xml");
        assertThat(ruleDoc, not(nullValue()));
        final AbstractEDIRule rule = new BEMISRule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testEDIFACTRule_BEMIS_7170_IN_1_0_A() throws Exception {
        final Document ruleDoc = getRuleDocument("BEMIS-7170-IN-1.0-A.xml");
        assertThat(ruleDoc, not(nullValue()));
        final AbstractEDIRule rule = new BEMISRule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testEDIFACTRule_BEMIS_7170_OUT_1_0_A() throws Exception {
        final Document ruleDoc = getRuleDocument("BEMIS-7170-OUT-1.0-A.xml");
        assertThat(ruleDoc, not(nullValue()));
        final AbstractEDIRule rule = new BEMISRule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testEDIFACTRule_BEMIS_7255_IN_1_0_A() throws Exception {
        final Document ruleDoc = getRuleDocument("BEMIS-7255-IN-1.0-A.xml");
        assertThat(ruleDoc, not(nullValue()));
        final AbstractEDIRule rule = new BEMISRule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testEDIFACTRule_BEMIS_7255_OUT_1_0_A() throws Exception {
        final Document ruleDoc = getRuleDocument("BEMIS-7255-OUT-1.0-A.xml");
        assertThat(ruleDoc, not(nullValue()));
        final AbstractEDIRule rule = new BEMISRule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testEDIFACTRule_BEMIS_7280_IN_1_0_A() throws Exception {
        final Document ruleDoc = getRuleDocument("BEMIS-7280-IN-1.0-A.xml");
        assertThat(ruleDoc, not(nullValue()));
        final AbstractEDIRule rule = new BEMISRule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testEDIFACTRule_BEMIS_7280_OUT_1_0_A() throws Exception {
        final Document ruleDoc = getRuleDocument("BEMIS-7280-OUT-1.0-A.xml");
        assertThat(ruleDoc, not(nullValue()));
        final AbstractEDIRule rule = new BEMISRule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testGetAgency() throws Exception {
        final Document ruleDoc = getRuleDocument("BEMIS-7135-IN-1.0-A.xml");
        assertThat(ruleDoc, not(nullValue()));
        final AbstractEDIRule rule = new BEMISRule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getAgency(), is("BAAN Electronic Message Interchange System"));
    }

    @Test
    void testGetDescription() throws Exception {
        final Document ruleDoc = getRuleDocument("BEMIS-7135-IN-1.0-A.xml");
        assertThat(ruleDoc, not(nullValue()));
        final AbstractEDIRule rule = new BEMISRule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getDescription(), is("Trading Invoice"));
    }

    @Test
    void testGetLayout() throws Exception {
        final Document ruleDoc = getRuleDocument("BEMIS-7135-IN-1.0-A.xml");
        assertThat(ruleDoc, not(nullValue()));
        final AbstractEDIRule rule = new BEMISRule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getLayout(), is("hwed"));
    }

    @Test
    void testGetRelease() throws Exception {
        final Document ruleDoc = getRuleDocument("BEMIS-7135-IN-1.0-A.xml");
        assertThat(ruleDoc, not(nullValue()));
        final AbstractEDIRule rule = new BEMISRule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getRelease(), is("A"));
    }

    @Test
    void testGetStandard() throws Exception {
        final Document ruleDoc = getRuleDocument("BEMIS-7135-IN-1.0-A.xml");
        assertThat(ruleDoc, not(nullValue()));
        final AbstractEDIRule rule = new BEMISRule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getStandard(), is("BEMIS"));
    }

    @Test
    void testGetType() throws Exception {
        final Document ruleDoc = getRuleDocument("BEMIS-7135-IN-1.0-A.xml");
        assertThat(ruleDoc, not(nullValue()));
        final AbstractEDIRule rule = new BEMISRule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getType(), is("7135-IN"));
    }

    @Test
    void testGetVersion() throws Exception {
        final Document ruleDoc = getRuleDocument("BEMIS-7135-IN-1.0-A.xml");
        assertThat(ruleDoc, not(nullValue()));
        final AbstractEDIRule rule = new BEMISRule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getVersion(), is("1.0"));
    }

    @Test
    void testHasMoreRuleTokens() throws Exception {
        final Document ruleDoc = getRuleDocument("BEMIS-7135-IN-1.0-A.xml");
        assertThat(ruleDoc, not(nullValue()));
        final AbstractEDIRule rule = new BEMISRule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testToString() throws Exception {
        final Document ruleDoc = getRuleDocument("BEMIS-7135-IN-1.0-A.xml");
        assertThat(ruleDoc, not(nullValue()));
        final AbstractEDIRule rule = new BEMISRule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.toString(), is("BEMIS-7135-IN-1.0-A-BAAN Electronic Message Interchange System"));
    }

}
