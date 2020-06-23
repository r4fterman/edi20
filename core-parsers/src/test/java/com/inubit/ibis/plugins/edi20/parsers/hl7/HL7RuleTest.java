package com.inubit.ibis.plugins.edi20.parsers.hl7;

import com.inubit.ibis.plugins.edi20.rules.AbstractEDIRule;
import com.inubit.ibis.utils.XmlUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.junit.MatcherAssert.assertThat;

class HL7RuleTest {

    private Document getRuleDocument(final String ruleDocumentName) throws URISyntaxException, DocumentException {
        final URL url = HL7RuleTest.class.getResource(ruleDocumentName);
        assertThat("File not found: " + ruleDocumentName, url, not(nullValue()));
        final File xmlFile = new File(url.toURI());
        return XmlUtils.getDocumentThrowing(xmlFile);
    }

    @Test
    void testEDIFACTRule_HL7_SRM_S01_v231() throws Exception {
        final Document ruleDoc = getRuleDocument("HL7-SRM_S01-v231--.xml");
        assertThat(ruleDoc, not(nullValue()));
        final AbstractEDIRule rule = new HL7Rule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testEDIFACTRule_HL7_SRM_2_3() throws Exception {
        final Document ruleDoc = getRuleDocument("HL7-SRM-2.3--.xml");
        assertThat(ruleDoc, not(nullValue()));
        final AbstractEDIRule rule = new HL7Rule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testGetAgency() throws Exception {
        final Document ruleDoc = getRuleDocument("HL7-SRM_S01-v231--.xml");
        assertThat(ruleDoc, not(nullValue()));
        final AbstractEDIRule rule = new HL7Rule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getAgency(), is(emptyString()));
    }

    @Test
    void testGetDescription() throws Exception {
        final Document ruleDoc = getRuleDocument("HL7-SRM_S01-v231--.xml");
        assertThat(ruleDoc, not(nullValue()));
        final AbstractEDIRule rule = new HL7Rule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getDescription(), is("SRM/SRR - Request new appointment booking"));
    }

    @Test
    void testGetLayout() throws Exception {
        final Document ruleDoc = getRuleDocument("HL7-SRM_S01-v231--.xml");
        assertThat(ruleDoc, not(nullValue()));
        final AbstractEDIRule rule = new HL7Rule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getLayout(), is("hwed"));
    }

    @Test
    void testGetRelease() throws Exception {
        final Document ruleDoc = getRuleDocument("HL7-SRM_S01-v231--.xml");
        assertThat(ruleDoc, not(nullValue()));
        final AbstractEDIRule rule = new HL7Rule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getRelease(), is("-"));
    }

    @Test
    void testGetStandard() throws Exception {
        final Document ruleDoc = getRuleDocument("HL7-SRM_S01-v231--.xml");
        assertThat(ruleDoc, not(nullValue()));
        final AbstractEDIRule rule = new HL7Rule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getStandard(), is("HL7"));
    }

    @Test
    void testGetType() throws Exception {
        final Document ruleDoc = getRuleDocument("HL7-SRM_S01-v231--.xml");
        assertThat(ruleDoc, not(nullValue()));
        final AbstractEDIRule rule = new HL7Rule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getType(), is("SRM_S01"));
    }

    @Test
    void testGetVersion() throws Exception {
        final Document ruleDoc = getRuleDocument("HL7-SRM_S01-v231--.xml");
        assertThat(ruleDoc, not(nullValue()));
        final AbstractEDIRule rule = new HL7Rule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.getVersion(), is("v231"));
    }

    @Test
    void testHasMoreRuleTokens() throws Exception {
        final Document ruleDoc = getRuleDocument("HL7-SRM_S01-v231--.xml");
        assertThat(ruleDoc, not(nullValue()));
        final AbstractEDIRule rule = new HL7Rule(ruleDoc);
        assertThat(rule, not(nullValue()));
    }

    @Test
    void testToString() throws Exception {
        final Document ruleDoc = getRuleDocument("HL7-SRM_S01-v231--.xml");
        assertThat(ruleDoc, not(nullValue()));
        final AbstractEDIRule rule = new HL7Rule(ruleDoc);
        assertThat(rule, not(nullValue()));
        assertThat(rule.toString(), is("HL7-SRM_S01-v231---"));
    }

}
