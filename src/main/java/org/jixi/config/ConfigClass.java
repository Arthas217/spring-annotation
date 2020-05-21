package org.jixi.config;

import org.jixi.bean.Person;
import org.jixi.filter.CustomerFilterType;
import org.jixi.service.BookService;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * @Configuration 告诉spring这是配置类 它可以替代之前的配置xml文件
 * 实质是@Component组件
 */
@Configuration
//@ComponentScan替代xml配置文件中<context:component-scan base-package="org.jixi.bean"/>
//只要标注了@Controller/@Service/@Repositroy/@Component的组件 都会自动扫描加入容器中
@ComponentScan(value = "org.jixi")
public class ConfigClass {

    // 使用注解@Bean 相当于替代原来xml中bean标签
    // spring容器注册一个组件Bean  type为方法返回类型 id默认是方法名，也可以用value指定获取bean的名字
    @Bean(value = "person")
    public Person person01() {
        return new Person("zlk", 19);
    }
}
