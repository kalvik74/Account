package com.java993.dao.entity;


import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
public class AccountEntity {
    private final Long id;
    private @NonNull BigDecimal amount;

    public void add(BigDecimal amount) {
        this.amount = this.amount.add(amount);
    }

    public void subtract(BigDecimal amount) {
        if (this.amount.compareTo(amount) == -1) {
            throw new IllegalStateException("not enough money");
        }
        this.amount = this.amount.subtract(amount);
    }
}
