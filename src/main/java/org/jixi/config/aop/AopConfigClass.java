package org.jixi.config.aop;

import org.jixi.aop.LogAspect;
import org.jixi.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 底层动态代理
 * AOP：指程序运行期间 动态的将某段代码切入到指定方法指定位置进行运行的编程方式
 */
@EnableAspectJAutoProxy
@Configuration
public class AopConfigClass {

    // spring需要导入aop依赖 spring-aspects 切面
    // 定义业务类MathCalculator
    // 日志切面类LogAspect  添加（通知--前置、后置、返回、异常、环绕--注解）
    // 给切面类方法标注何时何地运行  --切面表达式指定目标方法
    // 切面类、业务类（目标方法的类）加入容器中 -- bean方式
    // 告诉spring哪个类是切面类( LogAspect类上添加注解@Aspect）
    // 开启基于注解版的切面功能  --注解方式  给配置类中添加@EnableAspectJAutoProxy

    // 总结三步：将业务逻辑组件和切面类都加入倒容器中，告诉spring哪个是切面类（@Aspect）
    // 在切面类上每个通知方法上标注通知注解，告诉spring何时何地运行（切入点表达式）
    // 开启基于注解的aop模式（@EnableAspectJAutoProxy）

    @Bean
    public MathCalculator mathCalculator(){
        return new MathCalculator();
    }

    @Bean
    public LogAspect logAspect(){
        return new LogAspect();
    }

}
