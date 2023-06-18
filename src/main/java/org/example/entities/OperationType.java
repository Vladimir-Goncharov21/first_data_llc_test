package org.example.entities;

import java.util.Map;


public enum OperationType {
    BUY,
    SELL;

    private static final Map<String, OperationType> BY_SHORT_STRING = Map.of("b", BUY, "s", SELL);

    public static OperationType byShortString(String string) {
        return  BY_SHORT_STRING.get(string);
    }

}
