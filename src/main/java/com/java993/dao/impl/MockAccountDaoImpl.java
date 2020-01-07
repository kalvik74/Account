package com.java993.dao.impl;

import com.java993.dao.AccountDao;
import com.java993.dao.entity.AccountEntity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

public class MockAccountDaoImpl implements AccountDao {
    private final Map<Long, AccountEntity> entities = new HashMap<>();
    ReadWriteLock lock = new ReentrantReadWriteLock();

    public MockAccountDaoImpl() {
        entities.put(1l, new AccountEntity(1l, BigDecimal.valueOf(100000)));
        entities.put(2l, new AccountEntity(2l, BigDecimal.valueOf(500000)));
        entities.put(3l, new AccountEntity(3l, BigDecimal.valueOf(1000000)));
        entities.put(4l, new AccountEntity(4l, BigDecimal.valueOf(1000000)));
        entities.put(5l, new AccountEntity(5l, BigDecimal.valueOf(100000)));
    }

    @Override
    public AccountEntity findById(Long id) {
        lock.readLock().lock();
        try {
            AccountEntity accountEntity = entities.get(id);
            return accountEntity;
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<AccountEntity> findByIds(List<Long> ids) {
        lock.readLock().lock();
        try {
            List<AccountEntity> accounts = entities.values().stream().filter(accountEntity -> ids.contains(accountEntity.getId())).collect(Collectors.toList());
            return accounts;
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void transfer(Long from, Long to, BigDecimal amount) {

        lock.writeLock().lock();
        try {
            subtract(from, amount);
            add(to, amount);
        } finally {
            lock.writeLock().unlock();
        }
    }


    private void add(Long id, BigDecimal amount) {
        checkAccountId(id);
        entities.get(id).add(amount);
    }

    private void subtract(Long id, BigDecimal amount) {
        checkAccountId(id);
        entities.get(id).subtract(amount);
    }

    private void checkAccountId(Long id) {
        if (!entities.containsKey(id)) {
            throw new IllegalArgumentException("account with id: " + id + " was not found");
        }
    }
}
