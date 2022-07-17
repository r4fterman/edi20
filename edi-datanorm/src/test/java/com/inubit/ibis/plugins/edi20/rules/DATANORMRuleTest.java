package com.inubit.ibis.plugins.edi20.rules;

import com.inubit.ibis.utils.XmlUtils;
import org.dom4j.Document;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

class DATANORMRuleTest {

    @Test
    void testEDIFACTRule_DATANORM_ALL_4_0() throws Exception {
        Document ruleDoc = getRuleDocument("DATANORM-All-4.0--.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new DATANORMRule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testGetAgency() throws Exception {
        Document ruleDoc = getRuleDocument("DATANORM-All-4.0--.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new DATANORMRule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getAgency(), is("-"));
    }

    @Test
    void testGetDescription() throws Exception {
        Document ruleDoc = getRuleDocument("DATANORM-All-4.0--.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new DATANORMRule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getDescription(), is("DATANORM segments "));
    }

    @Test
    void testGetLayout() throws Exception {
        Document ruleDoc = getRuleDocument("DATANORM-All-4.0--.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new DATANORMRule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getLayout(), is("mswed"));
    }

    @Test
    void testGetRelease() throws Exception {
        Document ruleDoc = getRuleDocument("DATANORM-All-4.0--.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new DATANORMRule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getRelease(), is("-"));
    }

    @Test
    void testGetStandard() throws Exception {
        Document ruleDoc = getRuleDocument("DATANORM-All-4.0--.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new DATANORMRule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getStandard(), is("DATANORM"));
    }

    @Test
    void testGetType() throws Exception {
        Document ruleDoc = getRuleDocument("DATANORM-All-4.0--.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new DATANORMRule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getType(), is("All"));
    }

    @Test
    void testGetVersion() throws Exception {
        Document ruleDoc = getRuleDocument("DATANORM-All-4.0--.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new DATANORMRule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getVersion(), is("4.0"));
    }

    @Test
    void testHasMoreRuleTokens() throws Exception {
        Document ruleDoc = getRuleDocument("DATANORM-All-4.0--.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new DATANORMRule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testToString() throws Exception {
        Document ruleDoc = getRuleDocument("DATANORM-All-4.0--.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new DATANORMRule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.toString(), is("DATANORM-All-4.0----"));
    }

    private Document getRuleDocument(final String ruleDocumentName) throws Exception {
        URL url = DATANORMRuleTest.class.getResource(ruleDocumentName);
        assertThat("File not found: " + ruleDocumentName, url, not(nullValue()));
        File xmlFile = new File(url.toURI());
        return XmlUtils.getDocumentThrowing(xmlFile);
    }
}
