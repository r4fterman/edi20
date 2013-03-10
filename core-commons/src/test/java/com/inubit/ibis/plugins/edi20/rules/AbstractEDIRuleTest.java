package com.inubit.ibis.plugins.edi20.rules;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import com.inubit.ibis.plugins.edi20.rules.interfaces.IElementRuleToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleCompositeElement;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleElement;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegment;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegmentGroup;
import com.inubit.ibis.utils.XmlUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA. User: r4fter Date: 28.02.13 Time: 17:21 To change this template use File | Settings |
 * File Templates.
 */
public class AbstractEDIRuleTest {

    private AbstractEDIRule rule;

    @Before
    public void setUp() throws Exception {
        this.rule = new AbstractEDIRule(getDocument("EDIFACT-IFCSUM-D-96A.xml")) {
            @Override
            public String getLayout() {
                return "hwed";
            }

            @Override
            public String getStandard() {
                return "EDIFACT";
            }
        };
    }

    private Document getDocument(String fileName) throws DocumentException, URISyntaxException {
        File file = getFile(fileName);
        return XmlUtils.getDocumentThrowing(file);
    }

    private File getFile(String fileName) throws URISyntaxException {
        URL url = AbstractEDIRuleTest.class.getResource(fileName);
        assertNotNull("File not found: " + fileName, url);
        return new File(url.toURI());
    }

    @Test
    public void testGetCurrentRuleToken() throws Exception {
//        IRuleToken currentToken = rule.getCurrentRuleToken();
//        assertTrue(currentToken instanceof EDIRuleRoot);
    }

    @Test
    public void testGetRootElementName() throws Exception {
        assertEquals("Root", rule.getRootElementName());
    }

    @Test
    public void testGetRootElement() throws Exception {
        assertNotNull(rule.getRootElement());
    }

    @Test
    public void testGetAgency() throws Exception {
        assertEquals("UN", rule.getAgency());
    }

    @Test
    public void testGetDescription() throws Exception {
        assertEquals("Forwarding and Consolidation Summary Message", rule.getDescription());
    }

    @Test
    public void testGetRelease() throws Exception {
        assertEquals("96A", rule.getRelease());
    }

    @Test
    public void testGetType() throws Exception {
        assertEquals("IFCSUM", rule.getType());
    }

    @Test
    public void testGetVersion() throws Exception {
        assertEquals("D", rule.getVersion());
    }

    @Test
    public void testGetSegments() throws Exception {
        List<EDIRuleSegment> segments = rule.getSegments();
        assertEquals(13, segments.size());

        assertTrue(segments.get(0) instanceof EDIRuleSegment);
        assertTrue(segments.get(1) instanceof EDIRuleSegment);
        assertTrue(segments.get(2) instanceof EDIRuleSegment);
        assertTrue(segments.get(3) instanceof EDIRuleSegment);
        assertTrue(segments.get(4) instanceof EDIRuleSegment);
        assertTrue(segments.get(5) instanceof EDIRuleSegment);
        assertTrue(segments.get(6) instanceof EDIRuleSegmentGroup);
        assertTrue(segments.get(7) instanceof EDIRuleSegmentGroup);
        assertTrue(segments.get(8) instanceof EDIRuleSegmentGroup);
        assertTrue(segments.get(9) instanceof EDIRuleSegmentGroup);
        assertTrue(segments.get(10) instanceof EDIRuleSegmentGroup);
        assertTrue(segments.get(11) instanceof EDIRuleSegmentGroup);
        assertTrue(segments.get(12) instanceof EDIRuleSegment);
    }

    @Test
    public void testSegmentDetails() throws Exception {
        List<EDIRuleSegment> segments = rule.getSegments();
        assertEquals(13, segments.size());

        assertTrue(segments.get(0) instanceof EDIRuleSegment);
        EDIRuleSegment segment = segments.get(0);
        assertEquals("UNH", segment.getID());
        assertEquals("Message Header", segment.getDescription());
        assertEquals("/Message/Segment[@id='UNH']", segment.getXPath());
        assertEquals("/Root/UNH", segment.getRulePath());
        assertEquals("MessageHeader", segment.getXmlTag());
        assertEquals(1, segment.getLoop());
        assertEquals(1, segment.getCurrentLoopCount());
        assertFalse(segment.isLoopLimitReached());
        assertFalse(segment.isChecked());
        assertFalse(segment.isInProgress());
        assertFalse(segment.isConditional());
        assertTrue(segment.isMandatory());
    }

    @Test
    public void testGetElementsFromSegment() throws Exception {
        List<EDIRuleSegment> segments = rule.getSegments();
        assertEquals(13, segments.size());

        assertTrue(segments.get(0) instanceof EDIRuleSegment);
        EDIRuleSegment segment = segments.get(0);
        List<IElementRuleToken> elements = segment.getElements();
        assertEquals(4, elements.size());

        assertTrue(elements.get(0) instanceof EDIRuleElement);
        assertTrue(elements.get(1) instanceof EDIRuleCompositeElement);
        assertTrue(elements.get(2) instanceof EDIRuleElement);
        assertTrue(elements.get(3) instanceof EDIRuleCompositeElement);
    }

    @Test
    public void testGetElementDetails() throws Exception {
        List<EDIRuleSegment> segments = rule.getSegments();
        assertEquals(13, segments.size());

        assertTrue(segments.get(0) instanceof EDIRuleSegment);
        EDIRuleSegment segment = segments.get(0);
        List<IElementRuleToken> elements = segment.getElements();
        assertEquals(4, elements.size());

        assertTrue(elements.get(0) instanceof EDIRuleElement);
        EDIRuleElement element = (EDIRuleElement) elements.get(0);

        assertEquals("0062", element.getID());
        assertEquals("MessageReferenceNumber", element.getXmlTag());
        assertEquals("Message reference number", element.getDescription());
        assertEquals(1, element.getMinLength());
        assertEquals(14, element.getMaxLength());
        assertEquals("AN", element.getType());
        assertEquals("/Message/Segment[@id='UNH']/Element[@id='0062']", element.getXPath());
        assertEquals("/Root/UNH/0062", element.getRulePath());
    }

    @Test
    public void testGetCompositeElementDetails() throws Exception {
        List<EDIRuleSegment> segments = rule.getSegments();
        assertEquals(13, segments.size());

        assertTrue(segments.get(0) instanceof EDIRuleSegment);
        EDIRuleSegment segment = segments.get(0);
        List<IElementRuleToken> elements = segment.getElements();
        assertEquals(4, elements.size());

        assertTrue(elements.get(1) instanceof EDIRuleCompositeElement);
        EDIRuleCompositeElement element = (EDIRuleCompositeElement) elements.get(1);

        assertEquals("S009", element.getID());
        assertEquals("MessageIdentifierCE", element.getXmlTag());
        assertEquals("Message Identifier", element.getDescription());
        assertEquals("/Message/Segment[@id='UNH']/CompositeElement[@id='S009']", element.getXPath());
        assertEquals("/Root/UNH/S009", element.getRulePath());
    }

    @Test
    public void testGetElementsFromCompositeElement() throws Exception {
        List<EDIRuleSegment> segments = rule.getSegments();
        assertEquals(13, segments.size());

        assertTrue(segments.get(0) instanceof EDIRuleSegment);
        EDIRuleSegment segment = segments.get(0);
        List<IElementRuleToken> elements = segment.getElements();
        assertEquals(4, elements.size());

        assertTrue(elements.get(1) instanceof EDIRuleCompositeElement);
        EDIRuleCompositeElement element = (EDIRuleCompositeElement) elements.get(1);

        elements = element.getElements();
        assertEquals(5, elements.size());

        assertTrue(elements.get(0) instanceof EDIRuleElement);
        assertTrue(elements.get(1) instanceof EDIRuleElement);
        assertTrue(elements.get(2) instanceof EDIRuleElement);
        assertTrue(elements.get(3) instanceof EDIRuleElement);
        assertTrue(elements.get(4) instanceof EDIRuleElement);
    }

    @Test
    public void testGetSegmentGroupDetails() throws Exception {
        List<EDIRuleSegment> segments = rule.getSegments();
        assertEquals(13, segments.size());

        assertTrue(segments.get(6) instanceof EDIRuleSegmentGroup);
        EDIRuleSegmentGroup segmentGroup = (EDIRuleSegmentGroup) segments.get(6);

        assertEquals("Group_1", segmentGroup.getID());
        assertEquals("A group of segments containing references and constants which apply to the entire message.", segmentGroup.getDescription());
        assertEquals("SegmentGroup_1", segmentGroup.getXmlTag());
        assertEquals(9, segmentGroup.getLoop());
        assertEquals(1, segmentGroup.getCurrentLoopCount());
        assertEquals("/Root/Group_1", segmentGroup.getRulePath());
        assertEquals("/Message/SegmentGroup[@id='Group_1']", segmentGroup.getXPath());

        assertFalse(segmentGroup.isMandatory());
        assertTrue(segmentGroup.isConditional());
        assertFalse(segmentGroup.isChecked());
        assertFalse(segmentGroup.isInProgress());
        assertFalse(segmentGroup.isLoopLimitReached());
    }

    @Test
    public void testGetSegmentsFromSegmentGroup() throws Exception {
        List<EDIRuleSegment> segments = rule.getSegments();
        assertEquals(13, segments.size());

        assertTrue(segments.get(6) instanceof EDIRuleSegmentGroup);
        EDIRuleSegmentGroup segment = (EDIRuleSegmentGroup) segments.get(6);

        segments = segment.getSegments();
        assertEquals(2, segments.size());

        assertTrue(segments.get(0) instanceof EDIRuleSegment);
        assertTrue(segments.get(1) instanceof EDIRuleSegment);
    }
}
