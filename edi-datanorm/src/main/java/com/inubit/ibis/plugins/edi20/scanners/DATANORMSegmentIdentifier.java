package com.inubit.ibis.plugins.edi20.scanners;

/**
 * @author r4fter
 */
public class DATANORMSegmentIdentifier implements IIdentifier {

    private final String identifier;

    public DATANORMSegmentIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String getID() {
        return identifier;
    }

    @Override
    public String toString() {
        return getID();
    }
}
