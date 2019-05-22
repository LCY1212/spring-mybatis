package cn.lcy.service;

import cn.lcy.dao.Dao;
import cn.lcy.dao.impl.DaoImpl;
import cn.lcy.domain.User;
import org.junit.Test;

import java.util.List;

public class test1 {
    @Test
    public void test(){
        Dao dao = new DaoImpl();
        List<User> list = dao.getAll();
        for (User user : list) {
            System.out.println(user);
        }
    }
}
