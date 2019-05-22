package com.lcy.mybatis.service;

import com.lcy.mybatis.dao.AccountDao;
import com.lcy.mybatis.domain.Account;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class AccountService {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        InputStream is = AccountService.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml");
        sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
    }

    @Test
    public void findAccount() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
        List<Account> accounts = accountDao.findAccount();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }
}
