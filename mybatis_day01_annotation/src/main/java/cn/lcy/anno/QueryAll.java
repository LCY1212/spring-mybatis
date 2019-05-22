package cn.lcy.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface QueryAll {

    String sql();    //sql语句

    String resultType();//返回值类型

    String id();//要执行的方法
}
