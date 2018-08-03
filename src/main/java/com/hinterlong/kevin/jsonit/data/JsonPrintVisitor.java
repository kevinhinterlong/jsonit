package com.hinterlong.kevin.jsonit.data;

import java.util.Collection;

public class JsonPrintVisitor implements JsonVisitor {
    public StringBuilder builder;

    public JsonPrintVisitor() {
        this.builder = new StringBuilder();
    }

    protected static StringBuilder tabs(int depth) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            builder.append("  ");
        }
        return builder;
    }

    protected void iterateArray(Collection<? extends JsonNode> nodes, int depth, boolean skipInitTab) {
        iterate("[", "]", nodes, depth, skipInitTab);
    }

    protected void iterateObject(Collection<? extends JsonNode> nodes, int depth, boolean skipInitTab) {
        iterate("{", "}", nodes, depth, skipInitTab);
    }

    protected void iterate(
            String open,
            String close,
            Collection<? extends JsonNode> nodes,
            int depth,
            boolean skipInitTab
    ) {
        if (!skipInitTab) {
            builder.append(tabs(depth));
        }
        builder.append(open).append("\n");
        int i = 0;
        for (JsonNode node : nodes) {
            boolean appendComma = 1 < nodes.size() && i < nodes.size() - 1;

            visit(node, depth + 1);
            if (appendComma) {
                builder.append(",");
            }
            builder.append("\n");
            ++i;
        }
        builder.append(tabs(depth)).append(close);
    }

    @Override
    public void visit(JsonNode node, int depth) {
        node.accept(this, depth);
    }

    @Override
    public void visit(JsonObject object, int depth) {
        iterateObject(object, depth, false);
    }

    @Override
    public void visit(JsonPair pair, int depth) {
        builder.append(tabs(depth)).append("\"").append(pair.getKey()).append("\"").append(": ");
        JsonNode.Type type = pair.getValue().getType();
        if (type == JsonNode.Type.OBJECT) {
            iterateObject((Collection<? extends JsonNode>) pair.getValue(), depth, true);
        } else if (type == JsonNode.Type.ARRAY) {
            iterateArray((Collection<? extends JsonNode>) pair.getValue(), depth, true);
        } else {
            visit(pair.getValue());
        }
    }

    @Override
    public void visit(JsonArray array, int depth) {
        iterateArray(array, depth, false);
    }

    @Override
    public void visit(JsonRaw raw, int depth) {
        builder.append(tabs(depth)).append(raw.toString());
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}

