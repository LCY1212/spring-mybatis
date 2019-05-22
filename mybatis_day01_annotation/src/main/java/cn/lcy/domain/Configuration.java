package cn.lcy.domain;

import java.util.Map;

/**
 * 存放连接数据库的基本信息
 *      driver
 *      url
 *      username
 *      password
 *  存放所有的sql和返回值类型
 *      Map<String key,Mapper mapper>
 *             key: 命名空间.id
 *             mapper实体
 */
public class Configuration {
    private String driver;
    private String url;
    private String username;
    private String password;
    // 存放所有的sql和返回值类型
    private Map<String,Mapper> mappers;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, Mapper> getMappers() {
        return mappers;
    }

    public void setMappers(Map<String, Mapper> mappers) {
        this.mappers = mappers;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", mappers=" + mappers +
                '}';
    }
}
