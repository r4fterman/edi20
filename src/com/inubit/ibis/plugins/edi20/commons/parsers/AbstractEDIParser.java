package com.inubit.ibis.plugins.edi20.commons.parsers;

import com.inubit.ibis.plugins.edi20.commons.rules.AbstractEDIRule;
import com.inubit.ibis.plugins.edi20.commons.scanners.IScanner;
import com.inubit.ibis.plugins.edi20.core.outputs.IOutputWriter;
import com.inubit.ibis.plugins.edi20.core.outputs.OutputWriterFactory;
import com.inubit.ibis.utils.InubitException;

/**
 * @author r4fter
 * 
 */
public abstract class AbstractEDIParser implements IEDIParser {

	private AbstractEDIRule fEDIRule;
	private IScanner fScanner;
	private IOutputWriter fWriter;

	/**
	 * @param scanner
	 *            lexical scanner
	 * @param rule
	 *            EDI rule
	 */
	public AbstractEDIParser(final IScanner scanner, final AbstractEDIRule rule) {
		super();
		fScanner = scanner;
		fEDIRule = rule;
	}

	/**
	 * @return EDI rule
	 */
	public AbstractEDIRule getRule() {
		return fEDIRule;
	}

	/**
	 * @return lexical scanner
	 */
	public IScanner getScanner() {
		return fScanner;
	}
	
	public IOutputWriter getWriter() throws InubitException {
		if (fWriter == null) {
			fWriter = OutputWriterFactory.getInstance(getRule());
		}
		return fWriter;
	}
}
