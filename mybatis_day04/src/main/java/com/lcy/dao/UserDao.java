package com.lcy.dao;

import com.lcy.domain.User;

import java.util.List;

public interface UserDao {
    User findUserById();

    List<User> findAllUser();
}
