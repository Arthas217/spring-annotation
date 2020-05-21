package org.jixi.config;

import org.jixi.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 * @Configuration 配置类
 */
@Configuration
public class ConfigClass2 {

    // 默认是单实例
    /**
     * ConfigurableBeanFactory#SCOPE_PROTOTYPE
     * ConfigurableBeanFactory#SCOPE_SINGLETON
     * org.springframework.web.context.WebApplicationContext#SCOPE_REQUEST
     * org.springframework.web.context.WebApplicationContext#SCOPE_SESSION
     */

    @Scope(value = "singleton")
//    @Scope(value = "prototype")
    @Lazy(value = true)
    @Bean(value = "person")
    public Person person() {
        System.out.println("给容器中添加Person.....");
        return new Person("zlk", 3);
    }
}
