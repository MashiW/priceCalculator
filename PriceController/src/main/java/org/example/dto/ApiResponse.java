package org.example.dto;

import lombok.Builder;
import lombok.Getter;
import org.example.dto.enums.ApiStatus;

@Builder
@Getter
public class ApiResponse<T> {
    private ApiStatus code;
    private String message;
    private T payload;
}