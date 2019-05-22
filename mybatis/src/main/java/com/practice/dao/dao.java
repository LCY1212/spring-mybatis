package com.practice.dao;

import com.practice.domain.User;

import java.util.List;

public interface dao {
    List<User> findAll();

    User findById(int i);
}
