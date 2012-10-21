package com.inubit.ibis.plugins.edi20.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.inubit.ibis.plugins.edi20.commons.rules.EDIRuleFactoryTest;
import com.inubit.ibis.plugins.edi20.commons.scanners.EDILexicalScannerTest;
import com.inubit.ibis.plugins.edi20.core.parsers.bemis.BEMISRuleTest;
import com.inubit.ibis.plugins.edi20.core.parsers.datanorm.DATANORMRuleTest;
import com.inubit.ibis.plugins.edi20.core.parsers.edifact.EDIFACTParserTest;
import com.inubit.ibis.plugins.edi20.core.parsers.edifact.delimiters.EDIFACTDelimitersTest;
import com.inubit.ibis.plugins.edi20.core.parsers.edifact.rules.EDIFACTEnveloperRuleTest;
import com.inubit.ibis.plugins.edi20.core.parsers.edifact.rules.EDIFACTRuleTest;
import com.inubit.ibis.plugins.edi20.core.parsers.edifact.scanners.EDIFACTLexicalScannerTest;
import com.inubit.ibis.plugins.edi20.core.parsers.hl7.HL7RuleTest;
import com.inubit.ibis.plugins.edi20.core.parsers.vda.rules.VDARuleTest;
import com.inubit.ibis.plugins.edi20.core.parsers.x12.X12RuleTest;

@RunWith(value = Suite.class)
@SuiteClasses(value = {
        EDIRuleFactoryTest.class,
        EDIFACTParserTest.class,
        EDIFACTDelimitersTest.class,
        EDILexicalScannerTest.class,
        EDIFACTLexicalScannerTest.class,
        EDIFACTRuleTest.class,
        EDIFACTEnveloperRuleTest.class,
        BEMISRuleTest.class,
        DATANORMRuleTest.class,
        HL7RuleTest.class,
        VDARuleTest.class,
        X12RuleTest.class})
public class AllCoreTests {
    // do nothing
}
