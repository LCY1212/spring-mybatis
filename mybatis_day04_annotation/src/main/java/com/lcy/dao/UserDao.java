package com.lcy.dao;

import com.lcy.domain.SelectProviderUtils;
import com.lcy.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface UserDao {
    //添加用户
    @Insert(value = "insert into user (address,username,birthday,sex)\n" +
            " values (#{address},#{username},#{birthday},#{sex}) ;")
    int addUser(User user);

    //更新用户
    @Update("update user set username = #{username} where id = #{id}")
    int updateUser(User user);

    //删除用户
    @Delete("delete from user where id = #{id}")
    int deleteUser(int id);

    //查询全部用户
    @Select("select * from user")
    List<User> findAll();

    //根据id查询用户
    @Select("select * from user where id = #{id} ")
    User findUserById1(int id);

    //模糊查询用户
    @Select("select * from user where username like concat('%',#{username},'%')")
    List<User> findUserByCondition(String username);

    //统计用户数量
    @Select("select count(*) from user ")
    int findUserCount();

    //根据用户名或性别查询用户信息
    @SelectProvider(type = SelectProviderUtils.class, method = "sqlReturn")
    List<User> findUserByCondition1(User user);

    //注解实现一对多
    @Select("select * from user")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "birthday", property = "birthday"),
            @Result(column = "sex", property = "sex"),
            @Result(column = "address", property = "address"),
            @Result(
                    property = "list",
                    javaType = List.class,
                    column = "id",
                    many = @Many(
                            select = "com.lcy.dao.AccountDao.findAccountByUid1",
                            fetchType = FetchType.LAZY
                    )
            )
    })
    List<User> findUserAndAccount();
}
