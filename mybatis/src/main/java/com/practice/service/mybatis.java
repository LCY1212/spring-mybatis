package com.practice.service;

import com.practice.dao.dao;
import com.practice.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class mybatis {
    public static void main(String[] args) {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        InputStream is = mybatis.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml");

        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        dao dao = sqlSession.getMapper(dao.class);

        User user = dao.findById(2);

        System.out.println(user);
    }
}
