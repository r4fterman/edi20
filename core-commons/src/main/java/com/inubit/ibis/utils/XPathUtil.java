package com.inubit.ibis.utils;

import org.dom4j.Node;

import java.util.List;

/**
 * @author r4fter
 */
public final class XPathUtil {

    @SuppressWarnings("unchecked")
    public static List<Node> evaluateXPathAsNodeList(
            final String xPath,
            final Node node) {
        return node.selectNodes(xPath);
    }
}
