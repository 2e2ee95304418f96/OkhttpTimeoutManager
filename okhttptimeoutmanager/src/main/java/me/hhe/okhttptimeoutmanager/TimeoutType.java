package me.hhe.okhttptimeoutmanager;

/**
 * author:hhe
 * email:hhecoder@gmail.com
 * createTime:2019/4/11
 * discription:
 */
public interface TimeoutType {
    /**
     * 所有的类型
     */
    String ALL= "allTimeout";
    String CONNECT= "connectTimeout";
    String READ= "readTimeout";
    String WRITE= "writeTimeout";
    String DNS= "dnsTimeout";
}
