package com.hinterlong.kevin.jsonit.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class JsonArray extends JsonNode implements Collection<JsonNode> {
    private final List<JsonNode> items;

    public JsonArray() {
        items = new ArrayList<>();
    }

    @Override
    public Type getType() {
        return Type.ARRAY;
    }

    @Override
    public void accept(JsonVisitor visitor, int depth) {
        visitor.visit(this, depth);
    }

    @Override
    public String toString() {
        return items.stream()
                .map(Objects::toString)
                .collect(Collectors.joining(",", "[", "]"));
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return items.contains(o);
    }

    @Override
    public Iterator<JsonNode> iterator() {
        return items.iterator();
    }

    @Override
    public Object[] toArray() {
        return items.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return items.toArray(a);
    }

    @Override
    public boolean add(JsonNode jsonNode) {
        return items.add(jsonNode);
    }

    @Override
    public boolean remove(Object o) {
        return items.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return items.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends JsonNode> c) {
        return items.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return items.retainAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return items.retainAll(c);
    }

    @Override
    public void clear() {
        items.clear();
    }
}
