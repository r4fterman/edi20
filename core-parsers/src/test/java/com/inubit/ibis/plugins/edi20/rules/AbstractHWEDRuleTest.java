package com.inubit.ibis.plugins.edi20.rules;

import com.inubit.ibis.plugins.edi20.rules.interfaces.ElementRuleToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleCompositeElement;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleElement;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegment;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegmentGroup;
import com.inubit.ibis.plugins.edi20.rules.tokens.hwed.HwedRuleElement;
import com.inubit.ibis.plugins.edi20.scanners.Token;
import com.inubit.ibis.utils.XmlUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.junit.MatcherAssert.assertThat;

/**
 * @author r4fter
 */
class AbstractHWEDRuleTest {

    private AbstractHWEDRule rule;

    @BeforeEach
    void setUp() throws Exception {
        this.rule = new AbstractHWEDRule(getDocument("EDIFACT-IFCSUM-D-96A.xml")) {
            @Override
            public void closeCurrentRuleToken(final Token token) {
                // do nothing
            }

            @Override
            public boolean isEndOfRule() {
                return false;
            }

            @Override
            public String getStandard() {
                return "EDIFACT";
            }
        };
    }

    private Document getDocument(final String fileName) throws DocumentException, URISyntaxException {
        File file = getFile(fileName);
        return XmlUtils.getDocumentThrowing(file);
    }

    private File getFile(final String fileName) throws URISyntaxException {
        URL url = AbstractHWEDRuleTest.class.getResource(fileName);
        return new File(url.toURI());
    }

    @Test
    void testGetCurrentRuleToken() {
//        IRuleToken currentToken = rule.getCurrentRuleToken();
//        assertThat(currentToken instanceOf( EDIRuleRoot);
    }

    @Test
    void testGetRootElementName() {
        assertThat(rule.getRootElementName(), is("Root"));
    }

    @Test
    void testGetRootElement() {
        assertThat(rule.getRootElement(), not(nullValue()));
    }

    @Test
    void testGetAgency() {
        assertThat(rule.getAgency(), is("UN"));
    }

    @Test
    void testGetDescription() {
        assertThat(rule.getDescription(), is("Forwarding and Consolidation Summary Message"));
    }

    @Test
    void testGetRelease() {
        assertThat(rule.getRelease(), is("96A"));
    }

    @Test
    void testGetType() {
        assertThat(rule.getType(), is("IFCSUM"));
    }

    @Test
    void testGetVersion() {
        assertThat(rule.getVersion(), is("D"));
    }

    @Test
    void testGetSegments() {
        List<EDIRuleSegment> segments = rule.getSegments();
        assertThat(segments.size(), is(13));

        assertThat(segments.get(0), instanceOf(EDIRuleSegment.class));
        assertThat(segments.get(1), instanceOf(EDIRuleSegment.class));
        assertThat(segments.get(2), instanceOf(EDIRuleSegment.class));
        assertThat(segments.get(3), instanceOf(EDIRuleSegment.class));
        assertThat(segments.get(4), instanceOf(EDIRuleSegment.class));
        assertThat(segments.get(5), instanceOf(EDIRuleSegment.class));
        assertThat(segments.get(6), instanceOf(EDIRuleSegmentGroup.class));
        assertThat(segments.get(7), instanceOf(EDIRuleSegmentGroup.class));
        assertThat(segments.get(8), instanceOf(EDIRuleSegmentGroup.class));
        assertThat(segments.get(9), instanceOf(EDIRuleSegmentGroup.class));
        assertThat(segments.get(10), instanceOf(EDIRuleSegmentGroup.class));
        assertThat(segments.get(11), instanceOf(EDIRuleSegmentGroup.class));
        assertThat(segments.get(12), instanceOf(EDIRuleSegment.class));
    }

    @Test
    void testSegmentDetails() {
        List<EDIRuleSegment> segments = rule.getSegments();
        assertThat(segments.size(), is(13));

        assertThat(segments.get(0), instanceOf(EDIRuleSegment.class));
        EDIRuleSegment segment = segments.get(0);
        assertThat(segment.getID(), is("UNH"));
        assertThat(segment.getDescription(), is("Message Header"));
        assertThat(segment.getXPath(), is("/Message/Segment[@id='UNH']"));
        assertThat(segment.getRulePath(), is("/Root/UNH"));
        assertThat(segment.getXmlTag(), is("MessageHeader"));
        assertThat(segment.getLoop(), is(1));
        assertThat(segment.getCurrentLoopCount(), is(1));
        assertThat(segment.isLoopLimitReached(), is(false));
        assertThat(segment.isChecked(), is(false));
        assertThat(segment.isInProgress(), is(false));
        assertThat(segment.isConditional(), is(false));
        assertThat(segment.isMandatory(), is(true));
    }

    @Test
    void testGetElementsFromSegment() {
        List<EDIRuleSegment> segments = rule.getSegments();
        assertThat(segments.size(), is(13));

        assertThat(segments.get(0), instanceOf(EDIRuleSegment.class));
        EDIRuleSegment segment = segments.get(0);
        List<ElementRuleToken> elements = segment.getElements();
        assertThat(elements.size(), is(4));

        assertThat(elements.get(0), instanceOf(EDIRuleElement.class));
        assertThat(elements.get(1), instanceOf(EDIRuleCompositeElement.class));
        assertThat(elements.get(2), instanceOf(EDIRuleElement.class));
        assertThat(elements.get(3), instanceOf(EDIRuleCompositeElement.class));
    }

    @Test
    void testGetElementDetails() {
        List<EDIRuleSegment> segments = rule.getSegments();
        assertThat(segments.size(), is(13));

        assertThat(segments.get(0), instanceOf(EDIRuleSegment.class));
        EDIRuleSegment segment = segments.get(0);
        List<ElementRuleToken> elements = segment.getElements();
        assertThat(elements.size(), is(4));

        assertThat(elements.get(0), instanceOf(EDIRuleElement.class));
        EDIRuleElement element = (EDIRuleElement) elements.get(0);

        assertThat(element.getID(), is("0062"));
        assertThat(element.getXmlTag(), is("MessageReferenceNumber"));
        assertThat(element.getDescription(), is("Message reference number"));
        assertThat("Element: " + element.getClass().getCanonicalName(), element, instanceOf(HwedRuleElement.class));
        assertThat(((HwedRuleElement) element).getMinLength(), is(1));
        assertThat(((HwedRuleElement) element).getMaxLength(), is(14));
        assertThat(element.getType(), is("AN"));
        assertThat(element.getXPath(), is("/Message/Segment[@id='UNH']/Element[@id='0062']"));
        assertThat(element.getRulePath(), is("/Root/UNH/0062"));
    }

    @Test
    void testGetCompositeElementDetails() {
        List<EDIRuleSegment> segments = rule.getSegments();
        assertThat(segments.size(), is(13));

        assertThat(segments.get(0), instanceOf(EDIRuleSegment.class));
        EDIRuleSegment segment = segments.get(0);
        List<ElementRuleToken> elements = segment.getElements();
        assertThat(elements.size(), is(4));

        assertThat(elements.get(1), instanceOf(EDIRuleCompositeElement.class));
        EDIRuleCompositeElement element = (EDIRuleCompositeElement) elements.get(1);

        assertThat(element.getID(), is("S009"));
        assertThat(element.getXmlTag(), is("MessageIdentifierCE"));
        assertThat(element.getDescription(), is("Message Identifier"));
        assertThat(element.getXPath(), is("/Message/Segment[@id='UNH']/CompositeElement[@id='S009']"));
        assertThat(element.getRulePath(), is("/Root/UNH/S009"));
    }

    @Test
    void testGetElementsFromCompositeElement() {
        List<EDIRuleSegment> segments = rule.getSegments();
        assertThat(segments.size(), is(13));

        assertThat(segments.get(0), instanceOf(EDIRuleSegment.class));
        EDIRuleSegment segment = segments.get(0);
        List<ElementRuleToken> elements = segment.getElements();
        assertThat(elements.size(), is(4));

        assertThat(elements.get(1), instanceOf(EDIRuleCompositeElement.class));
        EDIRuleCompositeElement element = (EDIRuleCompositeElement) elements.get(1);

        elements = element.getElements();
        assertThat(elements.size(), is(5));

        assertThat(elements.get(0), instanceOf(EDIRuleElement.class));
        assertThat(elements.get(1), instanceOf(EDIRuleElement.class));
        assertThat(elements.get(2), instanceOf(EDIRuleElement.class));
        assertThat(elements.get(3), instanceOf(EDIRuleElement.class));
        assertThat(elements.get(4), instanceOf(EDIRuleElement.class));
    }

    @Test
    void testGetSegmentGroupDetails() {
        List<EDIRuleSegment> segments = rule.getSegments();
        assertThat(segments.size(), is(13));

        EDIRuleSegment segment = segments.get(6);
        assertThat("Segment: " + segment.getClass().getCanonicalName(), segment, instanceOf(EDIRuleSegmentGroup.class));
        EDIRuleSegmentGroup segmentGroup = (EDIRuleSegmentGroup) segment;

        assertThat(segmentGroup.getID(), is("Group_1"));
        assertThat(segmentGroup.getDescription(), is("A group of segments containing references and constants which apply to the entire message."));
        assertThat(segmentGroup.getXmlTag(), is("SegmentGroup_1"));
        assertThat(segmentGroup.getLoop(), is(9));
        assertThat(segmentGroup.getCurrentLoopCount(), is(1));
        assertThat(segmentGroup.getRulePath(), is("/Root/Group_1"));
        assertThat(segmentGroup.getXPath(), is("/Message/SegmentGroup[@id='Group_1']"));

        assertThat(segmentGroup.isMandatory(), is(false));
        assertThat(segmentGroup.isConditional(), is(true));
        assertThat(segmentGroup.isChecked(), is(false));
        assertThat(segmentGroup.isInProgress(), is(false));
        assertThat(segmentGroup.isLoopLimitReached(), is(false));
    }

    @Test
    void testGetSegmentsFromSegmentGroup() {
        List<EDIRuleSegment> segments = rule.getSegments();
        assertThat(segments.size(), is(13));

        assertThat(segments.get(6), instanceOf(EDIRuleSegmentGroup.class));
        EDIRuleSegmentGroup segment = (EDIRuleSegmentGroup) segments.get(6);

        segments = segment.getSegments();
        assertThat(segments.size(), is(2));

        assertThat(segments.get(0), instanceOf(EDIRuleSegment.class));
        assertThat(segments.get(1), instanceOf(EDIRuleSegment.class));
    }
}
