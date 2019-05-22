package cn.lcy.dao;

import cn.lcy.anno.QueryAll;
import cn.lcy.domain.User;

import java.util.List;

public interface Dao {
    @QueryAll(sql = "select * from user",resultType = "cn.lcy.domain.User",id="getAll")
    List<User> getAll();
}
