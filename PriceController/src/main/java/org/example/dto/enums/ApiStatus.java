package org.example.dto.enums;

public enum ApiStatus {
    SUCCESS("S2000"),
    NOT_FOUND("E4004");

    private final String code;

    ApiStatus(String code) {
        this.code = code;
    }
}
