package cn.lcy.dao.impl;

import cn.lcy.core.SqlSession;
import cn.lcy.core.SqlSessionFactory;
import cn.lcy.core.SqlSessionFactoryBuilder;
import cn.lcy.dao.Dao;
import cn.lcy.domain.User;

import java.io.InputStream;
import java.util.List;

public class DaoImpl implements Dao {

    @Override
    public List<User> getAll() {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        InputStream is = DaoImpl.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory =null;
        try {
        sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> list = sqlSession.queryAll("user.getAll");

        return list;
    }
}
