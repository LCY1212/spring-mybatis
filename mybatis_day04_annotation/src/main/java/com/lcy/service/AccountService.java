package com.lcy.service;

import com.lcy.dao.AccountDao;
import com.lcy.domain.Account;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class AccountService {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        InputStream is = UserService.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml");
        sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
    }

    @Test
    public void findAll(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
        List<Account> list = accountDao.findAccountAll();
        for (Account account : list) {
            System.out.println(account);
        }
    }

    @Test
    public void findAccountByUid1(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
        Account account = accountDao.findAccountByUid1(3);
        System.out.println(account);
    }

    @Test
    public void testfind(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
        List<Account> list = accountDao.findAccountAll();
        for (Account account : list) {
            System.out.println(account);
        }

        sqlSession.close();
    }


}
