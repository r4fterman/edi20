package com.inubit.ibis.plugins.edi20.scanners;

/**
 * @author r4fter
 */
public class VDASegmentIdentifier implements Identifier {

    private final String identifier;

    public VDASegmentIdentifier(final String identifier) {
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
