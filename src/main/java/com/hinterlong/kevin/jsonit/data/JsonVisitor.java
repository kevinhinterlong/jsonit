package com.hinterlong.kevin.jsonit.data;

import com.hinterlong.kevin.jsonit.data.JsonArray;
import com.hinterlong.kevin.jsonit.data.JsonNode;
import com.hinterlong.kevin.jsonit.data.JsonObject;
import com.hinterlong.kevin.jsonit.data.JsonPair;
import com.hinterlong.kevin.jsonit.data.JsonRaw;

public interface JsonVisitor {
    default void visit(JsonNode node) {
        visit(node, 0);
    }

    void visit(JsonNode node, int depth);

    default void visit(JsonObject object) {
        visit(object, 0);
    }

    void visit(JsonObject object, int depth);

    default void visit(JsonPair pair) {
        visit(pair, 0);
    }

    void visit(JsonPair pair, int depth);

    default void visit(JsonArray array) {
        visit(array, 0);
    }

    void visit(JsonArray array, int depth);

    default void visit(JsonRaw raw) {
        visit(raw, 0);
    }

    void visit(JsonRaw raw, int depth);
}
