package com.java993.dao.entity;

import lombok.Data;

import java.util.List;

@Data
public class UserEntity {
    private final Long id;
    private final String name;
    private final List<Long> accounts;
}
