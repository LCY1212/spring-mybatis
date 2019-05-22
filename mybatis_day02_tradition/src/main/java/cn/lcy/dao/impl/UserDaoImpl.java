package cn.lcy.dao.impl;

import cn.lcy.dao.UserDao;
import cn.lcy.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        InputStream is = UserDaoImpl.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml");
        sqlSessionFactory = sqlSessionFactoryBuilder.build(is);

    }

    @Override
    public List<User> findAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        List<User> list = sqlSession.selectList("UserDao.findAll");
        sqlSession.close();
        return list;
    }

    @Override
    public User findById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        User user = sqlSession.selectOne("UserDao.findById", id);
        sqlSession.close();
        return user;
    }

    @Override
    public User findByName(String name) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        User user = sqlSession.selectOne("UserDao.findByName", name);
        sqlSession.close();
        return user;
    }

    @Override
    public int addUser(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        int i = sqlSession.insert("UserDao.addUser", user);
        sqlSession.close();
        return i;
    }

    @Override
    public int addUser1(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        int i = sqlSession.insert("UserDao.addUser1", user);
        sqlSession.close();
        return i;
    }

    @Override
    public int updateUser(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        int i = sqlSession.update("UserDao.updateUser", user);
        sqlSession.close();
        return i;
    }

    @Override
    public int deleteUser(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        int i = sqlSession.delete("UserDao.deleteUser", id);
        sqlSession.close();
        return i;
    }

    @Override
    public int findCount() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        int i = sqlSession.selectOne("UserDao.findCount");
        sqlSession.close();
        return i;
    }

    @Override
    public List<User> likeUser(String name) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        List<User> list = sqlSession.selectList("UserDao.likeUser", name);
        sqlSession.close();
        return list;
    }

    @Override
    public User findByResultMap(String name) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        User user = sqlSession.selectOne("UserDao.findByResultMap", name);
        sqlSession.close();
        return user;
    }
}
