package com.java993.dao;

import com.java993.dao.entity.AccountEntity;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {
    AccountEntity findById(Long id);
    List<AccountEntity> findByIds(List<Long> ids);
    void transfer(Long from, Long to, BigDecimal amount);
}
