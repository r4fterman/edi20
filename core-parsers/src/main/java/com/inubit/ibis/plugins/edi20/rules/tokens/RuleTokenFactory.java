package com.inubit.ibis.plugins.edi20.rules.tokens;

import com.inubit.ibis.plugins.edi20.rules.interfaces.IRuleToken;
import org.dom4j.Element;

public interface RuleTokenFactory {

    IRuleToken createInstance(Element element);

}
