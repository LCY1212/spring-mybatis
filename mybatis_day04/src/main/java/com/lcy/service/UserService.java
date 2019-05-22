package com.lcy.service;

import com.lcy.dao.AccountDao;
import com.lcy.dao.UserDao;
import com.lcy.domain.Account;
import com.lcy.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class UserService {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        InputStream is = UserService.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml");
        sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
    }

    @Test
    public void test1() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
        List<Account> allAccount = accountDao.findAllAccount();
        for (Account account : allAccount) {
            System.out.println(account);
        }
    }

    @Test
    public void test2() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> allUser = userDao.findAllUser();
        for (User user : allUser) {
            System.out.println(user);
        }
    }
}
