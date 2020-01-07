package com.java993.dto;

import com.google.gson.JsonElement;
import lombok.Data;

@Data
public class StdResponse<T> {
    private final ResponseStatus status;
    private final T body;
}
