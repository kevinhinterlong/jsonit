package com.hinterlong.kevin.jsonit.data;

import java.util.Map;

public class JsonPair extends JsonNode implements Map.Entry<String, JsonNode> {
    private final String key;
    private JsonNode value;

    public JsonPair(String key, JsonNode value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public void accept(JsonVisitor visitor, int depth) {
        visitor.visit(this, depth);
    }

    @Override
    public String toString() {
        return "\"" + key + "\": " + value;
    }

    @Override
    public Type getType() {
        return null;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public JsonNode getValue() {
        return value;
    }

    @Override
    public JsonNode setValue(JsonNode value) {
        JsonNode old = value;
        this.value = value;
        return old;
    }
}
