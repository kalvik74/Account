package com.java993.dto;

import lombok.Data;

public enum ResponseStatus {
    SUCCESS("SUCCESS"), ERROR("ERROR");
    private String status;
    ResponseStatus(String status) {
        this.status = status;
    }
}
