package com.lcy.mybatis.dao;

import com.lcy.mybatis.domain.Account;
import java.util.List;

public interface AccountDao {
    List<Account> findAccount();
}
