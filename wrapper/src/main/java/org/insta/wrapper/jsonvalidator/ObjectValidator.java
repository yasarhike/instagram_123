package org.insta.wrapper.jsonvalidator;

import org.insta.wrapper.hibernate.*;
import org.insta.wrapper.jackson.ObjectParser;
import org.insta.wrapper.jackson.JsonObject;

public final class ObjectValidator<T,V> {

    private static ObjectValidator objectValidator;
    private final Validate validate;

    public ObjectValidator() {
        validate = Validate.getInstance();
    }

    public ObjectValidator getInstance() {
        return objectValidator == null ? new ObjectValidator() : objectValidator;
    }

    public byte[] validate(final T object, final Class<?> groups) {
        try {
            final ObjectParser objectParser = new ObjectParser();
            final org.insta.wrapper.jackson.JsonObject jsonObject = objectParser.getObjectNode();

            validate.validate(object, groups).forEach(violation -> jsonObject.objectNode().put(violation.getPropertyPath().toString(), violation.getMessage()));

            return !jsonObject.objectNode().isEmpty() ? objectParser.getObjectMapper().writeValueAsString(jsonObject.objectNode()).getBytes() : new byte[]{};
        } catch (Exception ignored) {
        }

        return new byte[]{};
    }

    public byte[] forSuccessResponse(final int tableId, final byte[] violations) {
        try {
            final ObjectParser objectParser = new ObjectParser();
            final JsonObject response = objectParser.getObjectNode();
            final JsonObject tableData = objectParser.getObjectNode();

            if (tableId != 0) {
                tableData.putNode("table id", tableId);
            } else {
                tableData.putNode("status", "wrong credentials");
            }
            response.setValue("data", tableData.objectNode());
            response.setValue("Violations ", objectParser.readTree(violations));

            return objectParser.writeValueAsString(response).getBytes();
        } catch (Exception ignored) {
        }
        return new byte[]{};
    }

    public byte[] forFailureResponse(final byte[] violations, final Boolean result) {
        try {
            final ObjectParser objectParser = new ObjectParser();
            final JsonObject response = objectParser.getObjectNode();
            final JsonObject tableData = objectParser.getObjectNode();

            tableData.putValue("status", result);
            response.setValue("data", tableData.objectNode());
            response.setValue("Violations ", objectParser.readTree(violations));

            return objectParser.writeValueAsString(response).getBytes();
        } catch (Exception ignored) {
        }
        return new byte[]{};
    }

    public byte[] objectResponse(final T object) {
        if (object == null) manualResponse(false);
        try {
            final ObjectParser objectParser = new ObjectParser();

            return objectParser.writeValueAsString(object).getBytes();
        } catch (Exception ignored) {
        }
        return new byte[] {};
    }

    public byte[] manualResponse(final boolean result) {
        try {
            final ObjectParser objectParser = new ObjectParser();
            final JsonObject response = objectParser.getObjectNode();

            response.putValue("status", result);
            return objectParser.writeValueAsString(response).getBytes();
        } catch (Exception ignored) {
        }
        return new byte[] {};
    }

    public byte[] stringManualResponse(final String result) {
        try {
            final ObjectParser objectParser = new ObjectParser();
            final JsonObject response = objectParser.getObjectNode();

            response.putValue("status", result);
            return objectParser.writeValueAsString(response).getBytes();
        } catch (Exception ignored) {
        }
        return new byte[] {};
    }
}
