package cn.lcy.core;

import cn.lcy.domain.Configuration;

public class SqlSessionFactory {
    private Configuration cnf;

    public SqlSessionFactory(Configuration cnf) {
        this.cnf = cnf;
    }

    public SqlSession openSession() {
        SqlSession sqlSession = new SqlSessionImpl(cnf);
        return sqlSession;
    }

}
