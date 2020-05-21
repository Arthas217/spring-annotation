package org.jixi.config;

import org.jixi.bean.Person;
import org.jixi.filter.CustomerFilterType;
import org.jixi.service.BookService;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * @ComponentScan替代xml配置文件中
 * <context:component-scan base-package="org.jixi.bean"/>
 * 只要标注了@Controller/@Service/@Repositroy/@Component的组件,都会自动扫描加入容器中
 */
@Configuration
@ComponentScans(value = {
        @ComponentScan(value = "org.jixi",useDefaultFilters = false),
        @ComponentScan(value = "org.jixi",
                includeFilters = {
                        // 按照注解、按照给定类型、自定义
                        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class}),
                        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {BookService.class}),
                        @ComponentScan.Filter(type = FilterType.CUSTOM,classes = {CustomerFilterType.class})},
                useDefaultFilters = false)
})
public class ConfigClass1 {

    @Bean(value = "person")
    public Person person01() {
        return new Person("zlk", 2);
    }
}
