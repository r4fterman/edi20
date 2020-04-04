package com.inubit.ibis.plugins.edi20.parsers.x12;

import com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule;
import com.inubit.ibis.utils.XmlUtils;
import org.dom4j.Document;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.junit.MatcherAssert.assertThat;

/**
 * @author r4fter
 */
class X12RuleTest {

    private Document getRuleDocument(final String ruleDocumentName) throws Exception {
        URL url = X12RuleTest.class.getResource(ruleDocumentName);
        assertThat("File not found: " + ruleDocumentName, url, not(nullValue()));
        File xmlFile = new File(url.toURI());
        return XmlUtils.getDocumentThrowing(xmlFile);
    }

    @Test
    void testEDIFACTRule_X12_832_03_030() throws Exception {
        Document ruleDoc = getRuleDocument("X12-832-03-030.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new X12Rule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testEDIFACTRule_X12_832_04_010() throws Exception {
        Document ruleDoc = getRuleDocument("X12-832-04-010.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new X12Rule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testEDIFACTRule_X12_850_03_030() throws Exception {
        Document ruleDoc = getRuleDocument("X12-850-03-030.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new X12Rule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testEDIFACTRule_X12_850_04_010() throws Exception {
        Document ruleDoc = getRuleDocument("X12-850-04-010.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new X12Rule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testGetAgency() throws Exception {
        Document ruleDoc = getRuleDocument("X12-832-03-030.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new X12Rule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getAgency(), is("X"));
    }

    @Test
    void testGetDescription() throws Exception {
        Document ruleDoc = getRuleDocument("X12-832-03-030.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new X12Rule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getDescription(), is("Price/Sales Catalog"));
    }

    @Test
    void testGetLayout() throws Exception {
        Document ruleDoc = getRuleDocument("X12-832-03-030.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new X12Rule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getLayout(), is("hwed"));
    }

    @Test
    void testGetRelease() throws Exception {
        Document ruleDoc = getRuleDocument("X12-832-03-030.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new X12Rule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getRelease(), is("030"));
    }

    @Test
    void testGetStandard() throws Exception {
        Document ruleDoc = getRuleDocument("X12-832-03-030.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new X12Rule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getStandard(), is("X12"));
    }

    @Test
    void testGetType() throws Exception {
        Document ruleDoc = getRuleDocument("X12-832-03-030.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new X12Rule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getType(), is("832"));
    }

    @Test
    void testGetVersion() throws Exception {
        Document ruleDoc = getRuleDocument("X12-832-03-030.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new X12Rule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getVersion(), is("03"));
    }

    @Test
    void testHasMoreRuleTokens() throws Exception {
        Document ruleDoc = getRuleDocument("X12-832-03-030.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new X12Rule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testToString() throws Exception {
        Document ruleDoc = getRuleDocument("X12-832-03-030.xml");
        assertThat(ruleDoc, not(nullValue()));
        AbstractEDIRule rule = new X12Rule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.toString(), is("X12-832-03-030-X"));
    }

}
