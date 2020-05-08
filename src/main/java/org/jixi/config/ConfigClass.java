package org.jixi.config;

import org.jixi.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注解@Configuration 实质是@Component组件
 * 代表配置类
 * 替代配置xml文件
 */
@Configuration
public class ConfigClass {

    // spring容器注入一个Bean  使用注解@Bean 类型是返回类型  id默认是用方法名
    @Bean(value = "person_01")
    public Person person() {
        return new Person("zlk", 19);
    }
}
