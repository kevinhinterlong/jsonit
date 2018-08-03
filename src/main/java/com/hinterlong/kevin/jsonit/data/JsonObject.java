package com.hinterlong.kevin.jsonit.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class JsonObject extends JsonNode implements Collection<JsonPair> {
    private final List<JsonPair> fields;

    public JsonObject() {
        this.fields = new ArrayList<>();
    }

    @Override
    public void accept(JsonVisitor visitor, int depth) {
        visitor.visit(this, depth);
    }

    @Override
    public String toString() {
        return fields.stream()
                .map(Objects::toString)
                .collect(Collectors.joining(", ", "{", "}"));
    }

    @Override
    public Type getType() {
        return Type.OBJECT;
    }

    @Override
    public int size() {
        return fields.size();
    }

    @Override
    public boolean isEmpty() {
        return fields.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return fields.contains(o);
    }

    @Override
    public Iterator<JsonPair> iterator() {
        return fields.iterator();
    }

    @Override
    public Object[] toArray() {
        return fields.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return fields.toArray(a);
    }

    @Override
    public boolean add(JsonPair jsonPair) {
        return fields.add(jsonPair);
    }

    @Override
    public boolean remove(Object o) {
        return fields.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return fields.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends JsonPair> c) {
        return fields.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return fields.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return fields.retainAll(c);
    }

    @Override
    public void clear() {
        fields.clear();
    }
}
