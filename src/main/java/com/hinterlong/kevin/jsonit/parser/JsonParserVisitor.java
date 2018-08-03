package com.hinterlong.kevin.jsonit.parser;

import com.hinterlong.kevin.jsonit.antlr.JsonBaseVisitor;
import com.hinterlong.kevin.jsonit.data.JsonArray;
import com.hinterlong.kevin.jsonit.data.JsonNode;
import com.hinterlong.kevin.jsonit.data.JsonObject;
import com.hinterlong.kevin.jsonit.data.JsonPair;
import com.hinterlong.kevin.jsonit.data.JsonRaw;

import java.util.stream.Collectors;

public class JsonParserVisitor extends JsonBaseVisitor<JsonNode> {
    private String parseJsonString(String orig) {
        return orig.subSequence(1, orig.length() - 1).toString();
    }

    @Override
    public JsonNode visitObj(com.hinterlong.kevin.jsonit.antlr.JsonParser.ObjContext ctx) {
        return ctx.pair().stream()
                .map(this::visit)
                .map(JsonPair.class::cast)
                .collect(Collectors.toCollection(JsonObject::new));
    }

    @Override
    public JsonNode visitPair(com.hinterlong.kevin.jsonit.antlr.JsonParser.PairContext ctx) {
        return new JsonPair(parseJsonString(ctx.STRING().getText()), visit(ctx.value()));
    }

    @Override
    public JsonNode visitArray(com.hinterlong.kevin.jsonit.antlr.JsonParser.ArrayContext ctx) {
        return ctx.value().stream()
                .map(this::visit)
                .collect(Collectors.toCollection(JsonArray::new));
    }

    @Override
    public JsonNode visitValue(com.hinterlong.kevin.jsonit.antlr.JsonParser.ValueContext ctx) {
        if ("null".equals(ctx.getText())) {
            return new JsonRaw<>(null);
        } else if ("true".equals(ctx.getText())) {
            return new JsonRaw<>(true);
        } else if ("false".equals(ctx.getText())) {
            return new JsonRaw<>(false);
        } else if (ctx.STRING() != null) {
            return new JsonRaw<>(parseJsonString(ctx.STRING().getText()));
        } else if (ctx.NUMBER() != null) {
            return new JsonRaw<>(Double.parseDouble(ctx.NUMBER().getText()));
        } else if (ctx.array() != null) {
            return visit(ctx.array());
        } else if (ctx.obj() != null) {
            return visit(ctx.obj());
        }
        return super.visitValue(ctx);
    }
}