package com.inubit.ibis.plugins.edi20.rules.tokens;

import com.inubit.ibis.plugins.edi20.rules.interfaces.RuleToken;
import org.dom4j.Element;

public interface RuleTokenFactory {

    RuleToken createInstance(Element element);

}
