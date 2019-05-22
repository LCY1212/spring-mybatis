package cn.lcy.service;

import cn.lcy.core.SqlSession;
import cn.lcy.core.SqlSessionFactory;
import cn.lcy.core.SqlSessionFactoryBuilder;
import cn.lcy.dao.Dao;
import cn.lcy.domain.User;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class test1 {
    @Test
    public void test() throws Exception {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        InputStream is = test1.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory =null;
        try {
            sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SqlSession sqlSession = sqlSessionFactory.openSession();

        Dao dao = sqlSession.getMapper(Dao.class);

        List<User> list = dao.getAll();

        for (User user : list) {
            System.out.println(user);
        }
    }
}
