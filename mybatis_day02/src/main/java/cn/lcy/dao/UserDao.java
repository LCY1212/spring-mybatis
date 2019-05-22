package cn.lcy.dao;

import cn.lcy.domain.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();

    User findById(int id);

    User findByName(String name);

    int addUser(User user);

    int addUser1(User user);

    int updateUser(User user);

    int deleteUser(int id);

    int findCount();

    List<User> likeUser(String name);

    User findByResultMap(String name);
}
