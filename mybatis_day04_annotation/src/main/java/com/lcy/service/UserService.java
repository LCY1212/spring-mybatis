package com.lcy.service;

import com.lcy.dao.UserDao;
import com.lcy.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class UserService {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        InputStream is = UserService.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml");
        sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
    }

    //添加用户
    @Test
    public void addUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setUsername("HelloOne");
        user.setSex("男");
        user.setAddress("月球");
        user.setBirthday(new Date());
        int i = userDao.addUser(user);
        System.out.println(i);
    }

    //更新用户
    @Test
    public void updateUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setUsername("HelloTwo");
        user.setId(5);
        int i = userDao.updateUser(user);
        System.out.println(i);
    }

    //删除用户
    @Test
    public void deleteUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        int i = userDao.deleteUser(36);
        System.out.println(i);
    }

    //查询全部用户
    @Test
    public void findUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> list = userDao.findAll();
        for (User user : list) {
            System.out.println(user);
        }
    }

    //根据id查询用户
    @Test
    public void findUserById() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = userDao.findUserById1(2);
        System.out.println(user);
    }

    //模糊查询用户
    @Test
    public void findUserByCondition() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> user = userDao.findUserByCondition("哈");
        System.out.println(user);
    }

    //统计用户数量
    @Test
    public void findUserCount() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        int i = userDao.findUserCount();
        System.out.println(i);
    }

    //根据用户名或性别查询用户信息
    @Test
    public void findUserByCondition1() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setSex("女");
        List<User> list = userDao.findUserByCondition1(user);
        for (User user1 : list) {
            System.out.println(user1);
        }
    }

    //注解实现一对多
    @Test
    public void findUserAndAccount(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> list = userDao.findUserAndAccount();
        for (User user : list) {
            System.out.println(user);
        }
    }
}
