package org.insta.wrapper.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public record JsonObject(ObjectNode objectNode) {

    public void putNode(final String key, final int value) {
        this.objectNode.put(key, value);
    }

    public void putNode(final String key, final String value) {
        this.objectNode.put(key, value);
    }

    public void setValue(final String key, final ObjectNode objectNode) {
        this.objectNode.set(key, objectNode);
    }

    public void setValue(final String key, final JsonNode jsonNode) {
        this.objectNode.set(key, jsonNode);
    }

    public void putValue(final String key, final Boolean result) {
        this.objectNode.put(key, result);
    }

    public void putValue(final String key, final String result) {
        this.objectNode.put(key, result);
    }
}
