package com.hinterlong.kevin.jsonit.data;

public class JsonRaw<T> extends JsonNode {
    private final T t;

    public JsonRaw(T t) {
        this.t = t;
    }

    @Override
    public Type getType() {
        return null;
    }

    @Override
    public void accept(JsonVisitor visitor, int depth) {
        visitor.visit(this, depth);
    }

    @Override
    public String toString() {
        if (t == null) {
            return null;
        } else if (t.getClass().equals(String.class)) {
            return "\"" + t + "\"";
        } else {
            return String.valueOf(t);
        }
    }
}