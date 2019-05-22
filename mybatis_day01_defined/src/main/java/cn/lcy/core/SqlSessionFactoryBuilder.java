package cn.lcy.core;

import cn.lcy.domain.Configuration;
import cn.lcy.domain.Mapper;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlSessionFactoryBuilder {
    //空参构造
    public SqlSessionFactoryBuilder() {
    }

    /**
     * 解析SqlMapConfig.xml封装到实体中（cnf），
     * 把实体（cnf）给到SqlSessionFactory对象中
     *
     * @param is 核心配置文件流
     * @return 返回带有cnf的SqlSessionFactory
     */

    public static SqlSessionFactory build(InputStream is) throws Exception {
        //1.解析xml文件并封装到cnf实体中
        SAXReader reader = new SAXReader();
        //获取document对象（解析文件的DOM树）
        Document document = reader.read(is);
        //获取根元素
        Element root = document.getRootElement();
        //获取root中的所有property标签的元素
        List<Element> propertys = root.selectNodes("//property");
        //获取config对象
        Configuration cnf = new Configuration();
        //判断是否为空
        if (propertys != null) {
            //遍历propertys集合，封装进cnf实体中

            for (Element property : propertys) {
                String name = property.attributeValue("name");
                String value = property.attributeValue("value");
                if ("driver".equals(name)) {
                    cnf.setDriver(value);
                } else if ("url".equals(name)) {
                    cnf.setUrl(value);
                } else if ("username".equals(name)) {
                    cnf.setUsername(value);
                } else if ("password".equals(name)) {
                    cnf.setPassword(value);
                }
            }

        } else {
            System.out.println("propertys集合为空~~~");
        }

        //对sql的map集合赋值
        Map<String, Mapper> map = new HashMap();

        //获取Mapper映射xml文件（path）路径
        Element mappersElement = root.element("mappers");
        if (mappersElement != null) {
            //获取mappers中的mapper
            List<Element> mapperElements = mappersElement.elements("mapper");
            //遍历集合
            for (Element mapperElement : mapperElements) {
                //获取mapper映射文件的名称（路径）
                String path = mapperElement.attributeValue("resource");
                //根据路径加载xml文件
                InputStream mapperIs = SqlSessionFactoryBuilder.class.getClassLoader().getResourceAsStream("UserMapper.xml");
                //获取document-mapper的DOM树
                SAXReader reader1 = new SAXReader();
                Document mapperDocument = reader1.read(mapperIs);
                //获取root跟元素
                Element mapperRoot = mapperDocument.getRootElement();
                //获取namespace值
                String namespace = mapperRoot.attributeValue("namespace");
                //获取子元素标签
                List<Element> selectElements = mapperRoot.elements("select");
                //遍历子元素标签，获取子元素标签中的属性和值
                for (Element selectElement : selectElements) {
                    //获取Mapper对象
                    Mapper mapper = new Mapper();
                    //获取select中的所有信息
                    String id = selectElement.attributeValue("id");
                    String resultType = selectElement.attributeValue("resultType");
                    String sql = selectElement.getText();
                    //把sql和result类型路径封装到mapper中
                    mapper.setSql(sql);
                    mapper.setResultType(resultType);
                    map.put(namespace + "." + id, mapper);
                }
            }
        }
        System.out.println(map);
        cnf.setMappers(map);
        System.out.println(cnf);
        //2.创建SqlSessionFactory 并且把cnf给SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactory(cnf);
        return sqlSessionFactory;
    }

    //测试
    public static void main(String[] args) throws Exception {
        InputStream is = SqlSessionFactoryBuilder.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory build = build(is);
    }
}
