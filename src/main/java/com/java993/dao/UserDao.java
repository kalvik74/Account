package com.java993.dao;

import com.java993.dao.entity.UserEntity;

import java.util.List;

public interface UserDao {
    List<UserEntity> getAll();
    UserEntity findById(Long id);
}
