package org.jixi.config;

import org.jixi.bean.Car;
import org.jixi.bean.Color;
import org.jixi.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 自动装配：spring利用依赖注入（DI),完成对IOC容器中各个组件的依赖关系赋值
 */
@Configuration
@ComponentScan({"org.jixi.control", "org.jixi.service", "org.jixi.dao","org.jixi.bean"})
public class AutowiredConfigClass {

    // 注册同类型BookDao组件 目的BookService中通过自动注入存在一个同类型的BookDao组件
    // 类型BookDao  id =默认是方法名，也可以在 @Bean(value = "dd")指定bean的名字
    // 断点发现 this指代的是代理类对象
    @Primary
    @Bean
//    @Bean(value = "bookDao")
    public BookDao bookDao1() {
        BookDao bookDao = new BookDao();
        bookDao.setLabel("2");
        return bookDao;
    }

    // @Bean注解 创建对象时，方法参数car是从ioc容器中获取
    // @Autowired 也可以省略
    @Bean
    public Color color(@Autowired Car car){
        Color color  = new Color();
        color.setCar(car);
        return color;
    }

}
