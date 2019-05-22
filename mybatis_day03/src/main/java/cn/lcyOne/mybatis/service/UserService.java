package cn.lcyOne.mybatis.service;

import cn.lcyOne.mybatis.dao.UserDao;
import cn.lcyOne.mybatis.domain.User;
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
    public void findAll(){

        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> list = userDao.findAll();

        for (User user : list) {
            System.out.println(user);
        }

    }

    @Test
    public void findByCondition(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user1 = new User();
        user1.setUsername("传智播客");
        user1.setSex("男");
        User user = userDao.findByCondition(user1);
        System.out.println(user);
    }

    @Test
    public void findByLike(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user1 = new User();
        user1.setUsername("哈");
        user1.setSex("男");
        List<User> list = userDao.findByLike(user1);
        for (User user : list) {
            System.out.println(user);
        }

    }

    @Test
    public void findByCondition1(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user1 = new User();
        user1.setUsername("王");
        user1.setAddress("燕");
        System.out.println(user1);
        List<User> list = userDao.findByCondition1(user1);
        for (User user : list) {
            System.out.println(user);
        }

    }

    @Test
    public void findByIn(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        int[] ints = {1,2,3,4};
        List<User> list = userDao.findByIn(ints);
        for (User user : list) {
            System.out.println(user);
        }

    }

}
