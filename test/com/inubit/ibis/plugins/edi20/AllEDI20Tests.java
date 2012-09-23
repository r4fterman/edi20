package com.inubit.ibis.plugins.edi20;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.inubit.ibis.plugins.edi20.commons.AllCommonsTests;
import com.inubit.ibis.plugins.edi20.core.AllCoreTests;
import com.inubit.ibis.plugins.edi20.utils.AllUtilityTests;

@RunWith(value = Suite.class)
@SuiteClasses(value = { AllCommonsTests.class, AllCoreTests.class, AllUtilityTests.class })
public class AllEDI20Tests {
	// do nothing
}
