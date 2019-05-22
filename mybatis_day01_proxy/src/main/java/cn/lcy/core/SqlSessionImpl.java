package cn.lcy.core;

import cn.lcy.domain.Configuration;
import cn.lcy.domain.Mapper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.*;
import java.util.*;

public class SqlSessionImpl implements SqlSession {
    private Configuration cnf;

    public SqlSessionImpl(Configuration cnf) {
        this.cnf = cnf;
    }

    /**
     * 传统模式查询
     *
     * @param <T>
     * @return
     */
    public <T> List<T> queryAll(String mapperId) {
        //获取连接
        Connection conn = null;

        try {
            //开启JDBC驱动
            Class.forName(cnf.getDriver());
            //获取连接
            conn = DriverManager.getConnection(cnf.getUrl(), cnf.getUsername(), cnf.getPassword());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //获取sql语句
        Map<String, Mapper> mappers = cnf.getMappers();
        Mapper mapper = mappers.get(mapperId);
        String sql = mapper.getSql();
        //获取返回结果的全限定名
        String resultType = mapper.getResultType();

        //获取方法返回的集合；
        List list = new ArrayList<>();

        //创建预编译语句，返回结果集
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //获得语句执行者
            ps = conn.prepareStatement(sql);
            //执行sql语句获取查询的结果集
            rs = ps.executeQuery();

            //元数据
            ResultSetMetaData metaData = rs.getMetaData();
            //metaData获取字段个数
            int columnCount = metaData.getColumnCount();
            //获取所有的字段名称并装到集合中
            ArrayList<String> columnNames = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(metaData.getColumnName(i));
            }

            //获取返回结果类型的实例
            Class calzz = Class.forName(resultType);
            //暴力获取所有的方法
            Method[] methods = calzz.getDeclaredMethods();

            //封装方法要返回的list集合
            while (rs.next()) {
                //创建返回结果实体的实例
                Object object = calzz.newInstance();
                //循环遍历并往实体中添加值，（反射赋值）
                for (String columnName : columnNames) {
                    for (Method method : methods) {
                        //判断方法名和字段名是否相同，相同则封装进去
                        if (method.getName().equalsIgnoreCase("set" + columnName)) {
                            method.invoke(object, rs.getObject(columnName));
                        }
                    }
                }
                //添加到list集合中返回
                list.add(object);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {

            try {
                conn.close();
                ps.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        System.out.println(list);
        return list;
    }

    /**
     * 动态代理
     *
     * @param clazz
     * @param <E>
     * @return
     */
    @Override
    public <E> E getMapper(Class<E> clazz) throws Exception {
        //动态代理
        E proxy1 = (E) Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                new Class[]{clazz},
                (Object proxy, Method method, Object[] args) -> {
                    //获取xml文件中的id值
                    Map<String, Mapper> mappers = cnf.getMappers();
                    Set<String> keySet = mappers.keySet();
                    ArrayList<String> XMLIds = new ArrayList<>();
                    for (String s : keySet) {
                        System.out.println(s);
                        String[] ids = s.split("\\.");
                        System.out.println(Arrays.toString(ids));
                        String id = ids[ids.length - 1];
                        System.out.println(id);
                        XMLIds.add(id);
                    }
                    System.out.println(XMLIds);

                    //获取代理类中的method名称
                    String name = method.getName();
                    //循环xml文件中的id值
                    for (String id : XMLIds) {
                        //判断xml中的id与要代理的方法名称是否一致，
                        if (id.equals(name)) {
                            String typeName = clazz.getTypeName();
                            String mapperId = typeName + "." + name;
                            List list = queryAll(mapperId);
                            return list;
                        }
                    }
                    return null;
                }

        );

        return proxy1;
    }
}
