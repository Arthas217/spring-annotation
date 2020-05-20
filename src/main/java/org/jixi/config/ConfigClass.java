package org.jixi.config;

import org.jixi.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration  告诉spring这是配置类
 * 它可以替代之前的配置xml文件
 * 实质是@Component组件
 */
@Configuration
public class ConfigClass {
    // 注册组件 （相当于原来xml中 bean标签）
    // spring容器注入一个组件Bean  使用注解@Bean
    // 类型class是返回类型  id默认是用方法名person01  也可以用value指定获取bean的名字

    @Bean(value = "person")
    public Person person01() {
        return new Person("zlk", 19);
    }
}
