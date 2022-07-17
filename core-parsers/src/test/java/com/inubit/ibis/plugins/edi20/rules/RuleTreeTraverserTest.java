package com.inubit.ibis.plugins.edi20.rules;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleBaseToken;
import com.inubit.ibis.plugins.edi20.rules.tokens.EDIRuleSegment;
import com.inubit.ibis.plugins.edi20.rules.tokens.hwed.HwedRuleTokenFactory;
import com.inubit.ibis.utils.XmlUtils;

class RuleTreeTraverserTest {

    private static final String RULE_DOCUMENT = "EDIFACT-IFCSUM-D-96A.xml";

    private static final List<String> EXPECTED_SEGMENT_IDS = List.of(
            "UNH",
            "BGM",
            "DTM",
            "MOA",
            "FTX",
            "CNT",
            // Group_1
            "RFF",
            "DTM",
            // Group_2
            "GOR",
            "DTM",
            "LOC",
            "SEL",
            "FTX",
            // Group_3
            "DOC",
            "DTM",
            // Group_4
            "TCC",
            "PRI",
            "EQN",
            "PCD",
            "MOA",
            "QTY",
            "LOC",
            // Group_5
            "TDT",
            "TSR",
            "LOC",
            "DTM",
            "SEL",
            "FTX",
            // Group_6
            "MEA",
            "EQN",
            // Group_7
            "DIM",
            "EQN",
            // Group_8
            "CTA",
            "COM",
            // Group_9
            "NAD",
            "LOC",
            // Group_10
            "CTA",
            "COM",
            // Group_11
            "DOC",
            "DTM",
            // Group_12
            "TCC",
            "PRI",
            "EQN",
            "PCD",
            "MOA",
            "QTY",
            //Group_13
            "EQD",
            "EQN",
            "TPL",
            "TMD",
            "MEA",
            "DIM",
            "SEL",
            "NAD",
            "LOC",
            "HAN",
            "TMP",
            "FTX",
            // Group_14
            "EQA",
            "EQN",
            //
            "UNT"
    );

    private RuleTreeTraverser traverser;
    private Document ruleDocument;

    @BeforeEach
    void setUp() throws Exception {
        this.ruleDocument = getRuleDocument();
        this.traverser = new RuleTreeTraverser();
    }

    @Test
    void traverser_should_find_each_segment_in_rule_document() {
        final Element rootElement = ruleDocument.getRootElement();
        final EDIRuleBaseToken ruleToken = (EDIRuleBaseToken) new HwedRuleTokenFactory().createInstance(rootElement);

        Optional<EDIRuleSegment> segment = Optional.empty();
        for (final String expectedSegmentID: EXPECTED_SEGMENT_IDS) {

            EDIRuleBaseToken tokenToStartFrom = ruleToken;
            if (segment.isPresent()) {
                tokenToStartFrom = segment.get();
            }

            segment = traverser.findNextSegment(tokenToStartFrom, expectedSegmentID);

            assertThat("Segment: " + expectedSegmentID + " - token: " + tokenToStartFrom.getID(),
                    segment.isPresent(),
                    is(true));
            assertThat(expectedSegmentID, segment.get().getID(), is(expectedSegmentID));
        }
    }

    private Document getRuleDocument() throws DocumentException {
        final InputStream stream = RuleTreeTraverserTest.class.getResourceAsStream(RULE_DOCUMENT);
        return XmlUtils.getDocumentThrowing(stream);
    }
}
