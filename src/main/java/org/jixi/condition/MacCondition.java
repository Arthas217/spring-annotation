package org.jixi.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 实现Condition接口，创建条件类，条件满足电脑是mac操作系统
 */
public class MacCondition implements Condition {
    /**
     * @param context  上下文判断条件
     * @param metadata 添加condition注解的注释
     * @return
     */
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 获取bean工厂
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        // 获取类加载器
        ClassLoader classLoader = context.getClassLoader();
        // 获取当前环境信息
        String osname = context.getEnvironment().getProperty("os.name");
        // 获取到bean的定义的注册类（所有的bean的定义都在这里注册）
        BeanDefinitionRegistry beanDefinitionRegistry = context.getRegistry();

        System.out.println("---->" + beanFactory + "---->");
        System.out.println("---->" + classLoader + "---->");
        System.out.println("---->" + osname + "---->");
        System.out.println("---->" + beanDefinitionRegistry.getBeanDefinitionCount() + "---->");
        return osname.contains("Mac OS");
    }
}
