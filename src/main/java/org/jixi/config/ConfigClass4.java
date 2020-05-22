package org.jixi.config;

import org.jixi.bean.Color;
import org.jixi.bean.Person;
import org.jixi.bean.Red;
import org.jixi.condition.MacCondition;
import org.jixi.condition.WindowCondition;
import org.jixi.customer.CustomerImportBeanDefinitionRegistrar;
import org.jixi.customer.CustomerImportSelector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Import 快速给容器中导入一个组件
 * id 默认的是组件的全类名
 */
@Configuration
@Import(value = {Color.class, CustomerImportSelector.class, CustomerImportBeanDefinitionRegistrar.class})
public class ConfigClass4 {

    @Conditional(value = {MacCondition.class})
    @Bean(value = "bill")
    public Person person01() {
        return new Person("bill gates", 30);
    }

    @Conditional(value = {WindowCondition.class})
    @Bean(value = "steven")
    public Person person02() {
        return new Person("steven nash", 50);
    }
}
