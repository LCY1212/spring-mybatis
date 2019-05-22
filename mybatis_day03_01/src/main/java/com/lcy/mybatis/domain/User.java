package com.lcy.mybatis.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class User implements Serializable{
    private Integer id;
    private String username;
    private String sex;
    private String address;
    private Date birthday;

    private List<Account> list;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                ", list=" + list +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<Account> getList() {
        return list;
    }

    public void setList(List<Account> list) {
        this.list = list;
    }
}
