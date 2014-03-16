package com.inubit.ibis.plugins.edi20.rules;

import com.inubit.ibis.utils.XmlUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.Assert.assertTrue;

/**
 * @author r4fter
 */
public class EDIFACTEnveloperRuleTest {

    @Test
    public void testEnveloperRuleInstance() throws Exception {
        String fileName = "EDIFACT-ENVELOPER.xml";
        new EDIFACTEnveloperRule(getDocument(fileName));
    }

    @Test
    public void testContainsSegmentUNB() throws Exception {
        String fileName = "EDIFACT-ENVELOPER.xml";
        EDIFACTEnveloperRule envRule = new EDIFACTEnveloperRule(getDocument(fileName));

        String segmentID = "UNB";
        assertTrue("Segment " + segmentID + " not found!", envRule.containsSegment(segmentID));
    }

    @Test
    public void testContainsSegmentUNG() throws Exception {
        String fileName = "EDIFACT-ENVELOPER.xml";
        EDIFACTEnveloperRule envRule = new EDIFACTEnveloperRule(getDocument(fileName));

        String segmentID = "UNG";
        assertTrue("Segment " + segmentID + " not found!", envRule.containsSegment(segmentID));
    }

    @Test
    public void testContainsSegmentUNE() throws Exception {
        String fileName = "EDIFACT-ENVELOPER.xml";
        EDIFACTEnveloperRule envRule = new EDIFACTEnveloperRule(getDocument(fileName));

        String segmentID = "UNE";
        assertTrue("Segment " + segmentID + " not found!", envRule.containsSegment(segmentID));
    }

    @Test
    public void testContainsSegmentUNZ() throws Exception {
        String fileName = "EDIFACT-ENVELOPER.xml";
        EDIFACTEnveloperRule envRule = new EDIFACTEnveloperRule(getDocument(fileName));

        String segmentID = "UNZ";
        assertTrue("Segment " + segmentID + " not found!", envRule.containsSegment(segmentID));
    }

    private Document getDocument(final String documentName) throws URISyntaxException, DocumentException {
        URL url = EDIFACTEnveloperRuleTest.class.getResource(documentName);
        return XmlUtils.getDocumentThrowing(new File(url.toURI()));
    }
}
