package org.jixi.config;


import org.jixi.bean.Car;
import org.springframework.context.annotation.*;

/**
 * 配置类  bean生命周期
 */
@Configuration
@ComponentScans({
        @ComponentScan(value = "org.jixi.bean"),
        @ComponentScan(value = "org.jixi.customer")
})
public class ConfigClassForLifeCycle {

    @Scope(value = "prototype")
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Car car() {
        return new Car();
    }
}
