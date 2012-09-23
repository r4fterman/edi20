package com.inubit.ibis.plugins.edi20.commons;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.inubit.ibis.plugins.edi20.commons.rules.EDIRuleFactoryTest;
import com.inubit.ibis.plugins.edi20.commons.scanners.EDILexicalScannerTest;

@RunWith(value = Suite.class)
@SuiteClasses(value = { EDIRuleFactoryTest.class, EDILexicalScannerTest.class })
public class AllCommonsTests {
	// do nothing
}
