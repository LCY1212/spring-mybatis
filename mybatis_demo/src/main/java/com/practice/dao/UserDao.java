package com.practice.dao;

import com.practice.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {
    @Select("select * from user")
    List<User> findAllofUser();

    @Select("select * from user where id = #{id}")
    User findUserById(int id);
}
