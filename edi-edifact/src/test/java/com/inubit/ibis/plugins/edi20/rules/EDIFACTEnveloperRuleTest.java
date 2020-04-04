package com.inubit.ibis.plugins.edi20.rules;

import com.inubit.ibis.utils.XmlUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author r4fter
 */
class EDIFACTEnveloperRuleTest {

    @Test
    void testEnveloperRuleInstance() throws Exception {
        String fileName = "EDIFACT-ENVELOPER.xml";
        new EDIFACTEnveloperRule(getDocument(fileName));
    }

    @Test
    void testContainsSegmentUNB() throws Exception {
        String fileName = "EDIFACT-ENVELOPER.xml";
        EDIFACTEnveloperRule envRule = new EDIFACTEnveloperRule(getDocument(fileName));

        String segmentID = "UNB";
        assertThat("Segment " + segmentID + " not found!", envRule.containsSegment(segmentID), is(true));
    }

    @Test
    void testContainsSegmentUNG() throws Exception {
        String fileName = "EDIFACT-ENVELOPER.xml";
        EDIFACTEnveloperRule envRule = new EDIFACTEnveloperRule(getDocument(fileName));

        String segmentID = "UNG";
        assertThat("Segment " + segmentID + " not found!", envRule.containsSegment(segmentID), is(true));
    }

    @Test
    void testContainsSegmentUNE() throws Exception {
        String fileName = "EDIFACT-ENVELOPER.xml";
        EDIFACTEnveloperRule envRule = new EDIFACTEnveloperRule(getDocument(fileName));

        String segmentID = "UNE";
        assertThat("Segment " + segmentID + " not found!", envRule.containsSegment(segmentID), is(true));
    }

    @Test
    void testContainsSegmentUNZ() throws Exception {
        String fileName = "EDIFACT-ENVELOPER.xml";
        EDIFACTEnveloperRule envRule = new EDIFACTEnveloperRule(getDocument(fileName));

        String segmentID = "UNZ";
        assertThat("Segment " + segmentID + " not found!", envRule.containsSegment(segmentID), is(true));
    }

    private Document getDocument(final String documentName) throws URISyntaxException, DocumentException {
        URL url = EDIFACTEnveloperRuleTest.class.getResource(documentName);
        return XmlUtils.getDocumentThrowing(new File(url.toURI()));
    }
}
