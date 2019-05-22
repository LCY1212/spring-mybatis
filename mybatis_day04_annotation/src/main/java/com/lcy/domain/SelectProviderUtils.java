package com.lcy.domain;

public class SelectProviderUtils {
    public String sqlReturn(User user) {
        String sql = "select * from user where 1=1 ";
        if (user.getUsername() != null && !"".equals(user.getUsername())) {
            sql += " and username = #{username}";
        }
        if (user.getSex() != null && !"".equals(user.getSex())) {
            sql += " and sex = #{sex}";
        }
        return sql;
    }
}
