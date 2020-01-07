package com.java993.service.impl;

import com.java993.dao.AccountDao;
import com.java993.dao.UserDao;
import com.java993.dto.Account;
import com.java993.dto.User;
import com.java993.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private AccountDao accountDao;

    public UserServiceImpl(UserDao userDao, AccountDao accountDao) {
        this.userDao = userDao;
        this.accountDao = accountDao;
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll().stream().map(userEntity ->
                User.UserBuilder.anUser()
                        .withId(userEntity.getId())
                        .withName(userEntity.getName())
                        .withAccounts(
                                accountDao.findByIds(userEntity.getAccounts())
                                        .stream().map(accountEntity ->
                                        Account.AccountBuilder.anAccount()
                                                .withId(accountEntity.getId())
                                                .withAmount(accountEntity.getAmount())
                                                .build()).collect(Collectors.toList())
                        ).build()
        ).collect(Collectors.toList());
    }
}
