package com.hinterlong.kevin.jsonit.parser;

import com.hinterlong.kevin.jsonit.data.JsonNode;

import org.antlr.v4.runtime.CharStream;

public interface JsonParser {
    JsonNode parse(CharStream stream);
}
