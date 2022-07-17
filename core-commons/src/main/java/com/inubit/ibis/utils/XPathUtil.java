package com.inubit.ibis.utils;

import java.util.List;

import org.dom4j.Node;

public final class XPathUtil {

    public static List<Node> evaluateXPathAsNodeList(
            final String xPath,
            final Node node) {
        return node.selectNodes(xPath);
    }

    private XPathUtil() {
        // do nothing
    }
}
