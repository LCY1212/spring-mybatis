package cn.lcy.core;

import java.util.List;

public interface SqlSession {
    <T> List<T> queryAll(String mapperId);

    <E> E getMapper(Class<E> clazz) throws Exception;
}
