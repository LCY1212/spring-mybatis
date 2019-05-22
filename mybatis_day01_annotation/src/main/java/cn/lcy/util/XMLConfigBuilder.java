package cn.lcy.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.lcy.domain.Configuration;
import cn.lcy.domain.Mapper;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class XMLConfigBuilder {
	//加载mybatis的配置文件 
	public static Configuration buildConfiguration(InputStream is) throws Exception {
		Configuration cfg = new Configuration();
		//创建SAXReader
		SAXReader reader = new SAXReader();
		//获取Document对象（xml的文档）
		Document document = reader.read(is);
		//获取根节点
		Element root = document.getRootElement();
		//获取property节点
		List<Element> propertys =  root.selectNodes("//property"); //获取property节点结合
		//对cfg对象赋值:数据库的基本信息
		for (Element element : propertys) {
			String name = element.attributeValue("name"); //username,password,url,driver
			String value = element.attributeValue("value");
			if("username".equals(name)) {
				cfg.setUsername(value);
			}
			if("password".equals(name)) {
				cfg.setPassword(value);
			}
			if("url".equals(name)) {
				cfg.setUrl(value);
			}
			if("driver".equals(name)) {
				cfg.setDriver(value);
			}
		}
		
		//对sql的map集合赋值
		Map<String, Mapper> mappers = new HashMap();
		
		//解析sql语句的配置文件
		Element mappersElement = root.element("mappers");
		
		if(mappersElement != null) {
		    // 获取所有的 mapper 标签
			List<Element> mapperElements = mappersElement.elements("mapper");
			// 遍历所有的mapper标签
			for (Element mapperElement : mapperElements) {
			    // 获取sql映射文件的路径
				String path = mapperElement.attributeValue("resource");
				//将每一个sql配置文件中的map集合存入到大的map中
                Map<String, Mapper> map = loadXmlForMapper(path);
                mappers.putAll(map);
			}
		}
		
		cfg.setMappers(mappers);
		return cfg;
	}

	/**
	 * 解析sql的配置文件
	 * @throws Exception 
	 */
	public static Map<String,Mapper> loadXmlForMapper(String path) throws Exception {
		//path是sql配置文件的路径，将路径妆化为字节流
		//类加载器加载配置文件的路径：相对于类src的路径
		
		InputStream is = XMLConfigBuilder.class.getClassLoader().getResourceAsStream(path);
		//dom4j解析
		//创建SAXReader
		SAXReader reader = new SAXReader();
		//获取Document对象（xml的文档）
		Document document = reader.read(is);
		//获取根节点
		Element root = document.getRootElement();
		
		Map<String,Mapper> map = new HashMap();
		// 获取sql映射文件的命名空间
		String namespace = root.attributeValue("namespace");
		// 获取sql映射文件中的所有 select 标签
		List<Element> selectElements = root.elements("select");
		// 遍历所有的select标签
		for (Element select : selectElements) {
//			<select id="getUser" resultType="cn.itcast.domain.User">   
//				select *  from user   
//			</select>   
			String id = select.attributeValue("id");
			String resultType = select.attributeValue("resultType");
			String sql = select.getText();
			Mapper mapper = new Mapper();
			mapper.setSql(sql);
			mapper.setResultType(resultType);
			map.put(namespace + "."+id, mapper);
		}
		
		return map;
	}
	
}
