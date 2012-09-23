package com.inubit.ibis.plugins.edi20.core.outputs;

import com.inubit.ibis.plugins.edi20.commons.rules.AbstractEDIRule;
import com.inubit.ibis.plugins.edi20.core.parsers.edifact.EDIFACTWriter;
import com.inubit.ibis.plugins.edi20.core.parsers.edifact.rules.EDIFACTRule;
import com.inubit.ibis.utils.InubitException;

/**
 * @author r4fter
 */
public final class OutputWriterFactory {

	public static IOutputWriter getInstance(final AbstractEDIRule rule) throws InubitException {
		if (rule == null) {
			throw new IllegalArgumentException("Rule is null!");
		}
		if (rule instanceof EDIFACTRule) {
			return new EDIFACTWriter();
		}
		throw new InubitException("Unknown rule [" + rule.getClass().getCanonicalName() + "]!");
	}

}
