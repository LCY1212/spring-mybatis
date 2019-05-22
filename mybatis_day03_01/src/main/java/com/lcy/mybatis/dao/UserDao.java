package com.lcy.mybatis.dao;

import com.lcy.mybatis.domain.User;

import java.util.List;

public interface UserDao {
    List<User> findUser();
}
