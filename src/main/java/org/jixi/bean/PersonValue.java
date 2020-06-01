package org.jixi.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

/**
 * @Value注解赋值
 * 1.基本类型
 * 2.SpEL  #{}
 * 3.${}  取出配置文件【propterty】中的值(在运行环境变量里面的值）
 */
public class PersonValue {

    @Value(value = "zuo_name")
    private String name;
    @Value("#{20-2}")
    private int age;

    /**
     * 如果是通过外部文件xml配置方式的话，添加<context:property-placeholder location="personvalue.properties">
     * 我们可以通过对应的配置类中添加注解@PropertySource代替
     */
    @Value("${pv.nickName}")
    private String nickName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "PersonValue{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
