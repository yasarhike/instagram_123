package org.insta.wrapper.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public final class ObjectParser {

    private final ObjectMapper objectMapper;
    public ObjectParser() {
        objectMapper = new ObjectMapper();
    }

    public ObjectMapper getObjectMapper() {
        return this.objectMapper;
    }

    public JsonObject getObjectNode() {
        return new JsonObject(objectMapper.createObjectNode());
    }

    public JsonNode readTree(final byte[] violations) throws IOException {
        return this.objectMapper.readTree(violations);
    }

    public JsonNode readTree(final String violations) throws IOException {
        return this.objectMapper.readTree(violations);
    }

    public String writeValueAsString(final Object object) throws JsonProcessingException {
        return this.objectMapper.writeValueAsString(object);
    }
}

