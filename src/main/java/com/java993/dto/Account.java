package com.java993.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Account {
    private final Long id;
    private final BigDecimal amount;

    public static final class AccountBuilder {
        private Long id;
        private BigDecimal amount;

        private AccountBuilder() {
        }

        public static AccountBuilder anAccount() {
            return new AccountBuilder();
        }

        public AccountBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public AccountBuilder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Account build() {
            return new Account(id, amount);
        }
    }
}
