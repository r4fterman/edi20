package com.inubit.ibis.plugins.edi20.core.parsers.edifact.rules;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import org.dom4j.Document;
import org.junit.Test;

import com.inubit.ibis.utils.InubitException;
import com.inubit.ibis.utils.XmlUtils;

/**
 * @author r4fter
 */
public class EDIFACTEnveloperRuleTest {

	@Test
	public void testEnveloperRuleInstance() {
		try {
			String fileName = "EDIFACT-ENVELOPER.xml";
			new EDIFACTEnveloperRule(getDocument(fileName));
		} catch (InubitException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testContainsSegmentUNB() {
		try {
			String fileName = "EDIFACT-ENVELOPER.xml";
			EDIFACTEnveloperRule envRule = new EDIFACTEnveloperRule(getDocument(fileName));

			String segmentID = "UNB";
			assertTrue("Segment " + segmentID + " not found!", envRule.containsSegment(segmentID));
		} catch (InubitException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testContainsSegmentUNG() {
		try {
			String fileName = "EDIFACT-ENVELOPER.xml";
			EDIFACTEnveloperRule envRule = new EDIFACTEnveloperRule(getDocument(fileName));

			String segmentID = "UNG";
			assertTrue("Segment " + segmentID + " not found!", envRule.containsSegment(segmentID));
		} catch (InubitException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testContainsSegmentUNE() {
		try {
			String fileName = "EDIFACT-ENVELOPER.xml";
			EDIFACTEnveloperRule envRule = new EDIFACTEnveloperRule(getDocument(fileName));

			String segmentID = "UNE";
			assertTrue("Segment " + segmentID + " not found!", envRule.containsSegment(segmentID));
		} catch (InubitException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testContainsSegmentUNZ() {
		try {
			String fileName = "EDIFACT-ENVELOPER.xml";
			EDIFACTEnveloperRule envRule = new EDIFACTEnveloperRule(getDocument(fileName));

			String segmentID = "UNZ";
			assertTrue("Segment " + segmentID + " not found!", envRule.containsSegment(segmentID));
		} catch (InubitException e) {
			fail(e.getMessage());
		}
	}

	private Document getDocument(final String documentName) {
		try {
			URL url = EDIFACTEnveloperRuleTest.class.getResource(documentName);
			assertNotNull("File not found: " + documentName, url);
			return XmlUtils.getDocument(new File(url.toURI()));
		} catch (URISyntaxException e) {
			fail(e.getMessage());
		}
		return null;
	}
}
