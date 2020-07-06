package com.inubit.ibis.plugins.edi20.rules;

import com.inubit.ibis.utils.XmlUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.junit.MatcherAssert.assertThat;

class VDARuleTest {

    private static final String VDA_4905_1_XML = "VDA-4905-1--.xml";
    private static final String VDA_4913_4_XML = "VDA-4913-4--.xml";

    @Test
    void testEDIFACTRule_VDA_4905_1() throws Exception {
        final Document ruleDoc = getRuleDocument(VDA_4905_1_XML);
        final VDARule rule = new VDARule(ruleDoc);

        assertThat(rule, not(nullValue()));
    }

    @Test
    void testEDIFACTRule_VDA_4913_4() throws Exception {
        final Document ruleDoc = getRuleDocument(VDA_4913_4_XML);
        final AbstractEDIRule rule = new VDARule(ruleDoc);

        assertThat(rule, not(nullValue()));
    }

    @Test
    void testGetAgency() throws Exception {
        final Document ruleDoc = getRuleDocument(VDA_4905_1_XML);
        final VDARule rule = new VDARule(ruleDoc);

        assertThat(rule.getAgency(), is("VDA"));
    }

    @Test
    void testGetDescription() throws Exception {
        final Document ruleDoc = getRuleDocument(VDA_4905_1_XML);
        final AbstractEDIRule rule = new VDARule(ruleDoc);

        assertThat(rule.getDescription(), is("Delivery instruction VDA 4905/1"));
    }

    @Test
    void testGetLayout() throws Exception {
        final Document ruleDoc = getRuleDocument(VDA_4905_1_XML);
        final VDARule rule = new VDARule(ruleDoc);

        assertThat(rule.getLayout(), is("hwfpe"));
    }

    @Test
    void testGetRelease() throws Exception {
        final Document ruleDoc = getRuleDocument(VDA_4905_1_XML);
        final VDARule rule = new VDARule(ruleDoc);

        assertThat(rule.getRelease(), is("-"));
    }

    @Test
    void testGetStandard() throws Exception {
        final Document ruleDoc = getRuleDocument(VDA_4905_1_XML);
        final VDARule rule = new VDARule(ruleDoc);

        assertThat(rule.getStandard(), is("VDA"));
    }

    @Test
    void testGetType() throws Exception {
        final Document ruleDoc = getRuleDocument(VDA_4905_1_XML);
        final VDARule rule = new VDARule(ruleDoc);

        assertThat(rule.getType(), is("4905"));
    }

    @Test
    void testGetVersion() throws Exception {
        final Document ruleDoc = getRuleDocument(VDA_4905_1_XML);
        final VDARule rule = new VDARule(ruleDoc);

        assertThat(rule.getVersion(), is("1"));
    }

    @Test
    void testToString() throws Exception {
        final Document ruleDoc = getRuleDocument(VDA_4905_1_XML);
        final VDARule rule = new VDARule(ruleDoc);

        assertThat(rule.toString(), is("VDA-4905-1---VDA"));
    }

    private Document getRuleDocument(final String ruleDocumentName) throws URISyntaxException, DocumentException {
        final URL url = VDARuleTest.class.getResource(ruleDocumentName);
        assertThat("File not found: " + ruleDocumentName, url, not(nullValue()));

        return XmlUtils.getDocumentThrowing(new File(url.toURI()));
    }
}
