package org.jixi.config;

import org.jixi.customer.CustomerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类，注册组件FactoryBean
 */
@Configuration
public class ConfigClass5 {

    @Bean
    public CustomerFactoryBean customerFactoryBean() {
        return new CustomerFactoryBean();
    }
}
