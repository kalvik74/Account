package com.java993.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Transfer {
    private final Long fromAccountId;
    private final Long toAccountId;
    private final BigDecimal amount;
}
