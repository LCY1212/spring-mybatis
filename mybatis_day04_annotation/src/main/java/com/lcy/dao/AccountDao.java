package com.lcy.dao;

import com.lcy.domain.Account;
import com.lcy.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface AccountDao {
    @Select("select * from account")
    @Results(id = "map", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "uid", property = "uid"),
            @Result(column = "money", property = "money"),
            @Result(
                    property = "user",
                    javaType = User.class,
                    column = "uid",
                    one = @One(
                            select = "com.lcy.dao.UserDao.findUserById1",
                            fetchType = FetchType.LAZY //设置延迟加载
                    )
            )
    }
    )
    List<Account> findAccountAll();

    @Select("select * from account where uid = #{uid}")
    Account findAccountByUid1(int uid);
}
