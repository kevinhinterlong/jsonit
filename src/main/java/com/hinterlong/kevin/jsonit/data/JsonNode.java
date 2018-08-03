package com.hinterlong.kevin.jsonit.data;

public abstract class JsonNode {

    public final void accept(JsonVisitor visitor) {
        accept(visitor, 0);
    }

    public abstract void accept(JsonVisitor visitor, int depth);

    public abstract String toString();

    public abstract Type getType();

    enum Type {
        ARRAY,
        OBJECT
    }

    public String prettyPrint() {
        JsonPrintVisitor printer = new JsonPrintVisitor();
        accept(printer);
        return printer.toString();
    }
}
