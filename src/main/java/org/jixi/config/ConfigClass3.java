package org.jixi.config;

import org.jixi.bean.Person;
import org.jixi.condition.MacCondition;
import org.jixi.condition.WindowCondition;
import org.springframework.context.annotation.*;

/**
 * @Conditional
 * 按照一定的条件进行判断，如果满足条件，再给容器中注册Bean组件
 */
@Configuration
@Conditional(value = {WindowCondition.class})
public class ConfigClass3 {

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
