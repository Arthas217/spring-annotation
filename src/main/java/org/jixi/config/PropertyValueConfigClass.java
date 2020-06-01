package org.jixi.config;

import org.jixi.bean.PersonValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 配置类
 * 组件PersonValue中的属性可以用xml中property标签赋值，也可以通过@Value注解替代property标签的作用
 *
 * 如果是通过外部文件配置方式，使用context:property-placeholder标签，也可以使用@PropertySource注解配置文件的位置。读取外部配置文件中k,v值
 * （也就保存到运行的环境变量中，加载完配置文件后，通过${}方式取出配置文件的值
 */
@PropertySource(value = {"classpath:/personvalue.properties"})
@Configuration
public class PropertyValueConfigClass {

    @Bean
    public PersonValue personValue(){
        return new PersonValue();
    }
}
