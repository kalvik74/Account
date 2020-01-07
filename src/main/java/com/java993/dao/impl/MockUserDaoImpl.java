package com.java993.dao.impl;

import com.java993.dao.UserDao;
import com.java993.dao.entity.UserEntity;

import java.util.*;

public class MockUserDaoImpl implements UserDao {

    private final Map<Long, UserEntity> entities = new HashMap<>();

    public MockUserDaoImpl() {
        entities.put(1l, new UserEntity(1l, "John", Arrays.asList(1l, 2l)));
        entities.put(2l, new UserEntity(2l, "Matt", Arrays.asList(3l, 4l)));
        entities.put(3l, new UserEntity(3l, "Oleg", Arrays.asList(5l)));
    }

    @Override
    public List<UserEntity> getAll() {
        return new ArrayList<>(entities.values());
    }

    @Override
    public UserEntity findById(Long id) {
        return entities.get(id);
    }
}
