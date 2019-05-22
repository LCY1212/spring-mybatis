package cn.lcy.service;

import cn.lcy.dao.UserDao;
import cn.lcy.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class UserService {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void befor() {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        InputStream is = this.getClass().getClassLoader().getResourceAsStream("SqlMapConfig.xml");

        sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
    }

    @Test
    public void findAll() {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserDao userDao = sqlSession.getMapper(UserDao.class);

        List<User> list = userDao.findAll();
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void findById() {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserDao userDao = sqlSession.getMapper(UserDao.class);

        User user = userDao.findById(20);//如果没有会返回null
        System.out.println(user);
    }

    @Test
    public void findByName() {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserDao userDao = sqlSession.getMapper(UserDao.class);

        User user = userDao.findByName("传智播客");//如果没有会返回null
        System.out.println(user);

    }

    @Test
    public void addUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        UserDao userDao = sqlSession.getMapper(UserDao.class);

        User user = new User();
        user.setUsername("哈哈");
        user.setAddress("火星");
        user.setSex("男");
        user.setBirthday(new Date());

        int i = userDao.addUser(user);
        System.out.println(i);

    }

    @Test
    public void addUser1() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        UserDao userDao = sqlSession.getMapper(UserDao.class);

        User user = new User();
        user.setUsername("哈哈1");
        user.setAddress("火星1");
        user.setSex("男");
        user.setBirthday(new Date());

        int i = userDao.addUser1(user);
        System.out.println(i);

    }

    @Test
    public void updateUSer() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        UserDao userDao = sqlSession.getMapper(UserDao.class);

        User user = new User();
        user.setUsername("嘿嘿1");
        user.setSex("女");
        user.setId(15);
        int i = userDao.updateUser(user);
        System.out.println(i);

    }

    @Test
    public void deleteUSer() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        UserDao userDao = sqlSession.getMapper(UserDao.class);

        User user = new User();
        user.setUsername("嘿嘿1");
        user.setSex("女");
        user.setId(15);
        int i = userDao.deleteUser(10);
        System.out.println(i);

    }

    @Test
    public void findCount() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        UserDao userDao = sqlSession.getMapper(UserDao.class);

        int i = userDao.findCount();
        System.out.println(i);

    }

    @Test
    public void likeUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        UserDao userDao = sqlSession.getMapper(UserDao.class);

        List<User> list = userDao.likeUser("王");
        System.out.println(list);

    }

    @Test
    public void findByResultMap() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        UserDao userDao = sqlSession.getMapper(UserDao.class);

        User user = userDao.findByResultMap("小马宝莉");
        System.out.println(user);

    }
}
