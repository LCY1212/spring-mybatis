package cn.lcy.service;

import cn.lcy.dao.UserDao;
import cn.lcy.dao.impl.UserDaoImpl;
import cn.lcy.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class UserServiceOne {
    @Test
    public void findAll(){
        UserDao userDao = new UserDaoImpl();
        List<User> list = userDao.findAll();
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void findById() {

        UserDao userDao = new UserDaoImpl();

        User user = userDao.findById(20);//如果没有会返回null
        System.out.println(user);
    }

    @Test
    public void findByName() {

        UserDao userDao = new UserDaoImpl();

        User user = userDao.findByName("传智播客");//如果没有会返回null
        System.out.println(user);

    }

    @Test
    public void addUser() {
        UserDao userDao = new UserDaoImpl();

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
        UserDao userDao = new UserDaoImpl();

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
        UserDao userDao = new UserDaoImpl();

        User user = new User();
        user.setUsername("嘿嘿1");
        user.setSex("女");
        user.setId(15);
        int i = userDao.updateUser(user);
        System.out.println(i);

    }

    @Test
    public void deleteUSer() {
        UserDao userDao = new UserDaoImpl();

        User user = new User();
        user.setUsername("嘿嘿1");
        user.setSex("女");
        user.setId(15);
        int i = userDao.deleteUser(10);
        System.out.println(i);

    }

    @Test
    public void findCount() {
        UserDao userDao = new UserDaoImpl();

        int i = userDao.findCount();
        System.out.println(i);

    }

    @Test
    public void likeUser() {
        UserDao userDao = new UserDaoImpl();

        List<User> list = userDao.likeUser("王");
        System.out.println(list);

    }

    @Test
    public void findByResultMap() {
        UserDao userDao = new UserDaoImpl();

        User user = userDao.findByResultMap("小马宝莉");
        System.out.println(user);

    }
}
