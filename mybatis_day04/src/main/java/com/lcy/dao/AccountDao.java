package com.lcy.dao;

import com.lcy.domain.Account;

import java.util.List;

public interface AccountDao {

    List<Account> findAllAccount();

    List<Account> findAccountByUid();
}
