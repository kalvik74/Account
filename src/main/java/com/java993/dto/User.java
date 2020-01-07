package com.java993.dto;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private final Long id;
    private final String name;
    private final List<Account> accounts;

    public static final class UserBuilder {
        private Long id;
        private String name;
        private List<Account> accounts;

        private UserBuilder() {
        }

        public static UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder withAccounts(List<Account> accounts) {
            this.accounts = accounts;
            return this;
        }

        public User build() {
            return new User(id, name, accounts);
        }
    }
}
