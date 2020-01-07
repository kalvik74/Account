package com.java993.service.impl;

import com.java993.dao.AccountDao;
import com.java993.dao.impl.MockAccountDaoImpl;
import com.java993.dto.Transfer;
import com.java993.service.AccountService;

public class AccountServiceImpl implements AccountService {
    private final AccountDao accountDao;
    public AccountServiceImpl(MockAccountDaoImpl accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void transfer(Transfer transfer) {
        accountDao.transfer(transfer.getFromAccountId(), transfer.getToAccountId(), transfer.getAmount());
    }
}
