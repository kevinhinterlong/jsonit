package com.hinterlong.kevin.jsonit.parser;

import com.hinterlong.kevin.jsonit.antlr.JsonBaseVisitor;
import com.hinterlong.kevin.jsonit.antlr.JsonLexer;
import com.hinterlong.kevin.jsonit.data.JsonNode;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class DefaultJsonParser implements JsonParser {
    private final JsonBaseVisitor<JsonNode> visitor = new JsonParserVisitor();

    @Override
    public JsonNode parse(CharStream input) {
        try {
            JsonLexer lexer = new JsonLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            com.hinterlong.kevin.jsonit.antlr.JsonParser parser = new com.hinterlong.kevin.jsonit.antlr.JsonParser(
                    tokens);
            return visitor.visit(parser.value());
        } catch (Exception e) {
            return null;
        }
    }
}
