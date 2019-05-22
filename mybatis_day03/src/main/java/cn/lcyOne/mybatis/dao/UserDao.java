package cn.lcyOne.mybatis.dao;

import cn.lcyOne.mybatis.domain.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();

    User findByCondition(User user);

    List<User> findByLike(User user);

    List<User> findByCondition1(User user);

    List<User> findByIn(int[] ints);
}
