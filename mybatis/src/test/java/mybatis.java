import com.practice.dao.dao;
import com.practice.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class mybatis {
    @Test
    public void test(){
    //测试
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        InputStream is = mybatis.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml");

        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        dao daouser = sqlSession.getMapper(dao.class);

        List<User> list = daouser.findAll();

        for (User user : list) {
            System.out.println(user);
        }

    }
}
