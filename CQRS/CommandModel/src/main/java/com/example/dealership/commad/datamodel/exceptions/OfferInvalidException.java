package com.example.dealership.commad.datamodel.exceptions;

public class OfferInvalidException extends RuntimeException {

    public final JsonException jsonException;

    public OfferInvalidException(String message) {
        super(message);
        this.jsonException = new JsonException(message);
    }

    public static class JsonException {
        public final String reason;
        public JsonException(String reason) {
            this.reason = reason;
        }

        @Override
        public String toString() {
            return "JsonException{" +
                    "reason='" + reason + '\'' +
                    '}';
        }
    }
}
