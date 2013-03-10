package com.inubit.ibis.utils;

import java.util.List;

import org.dom4j.Node;

/**
 * @author r4fter
 */
public final class XPathUtil {

    @SuppressWarnings("unchecked")
    public static List<Node> evaluateXPathAsNodeList(final String xPath, final Node node) {
        return node.selectNodes(xPath);
    }
}
