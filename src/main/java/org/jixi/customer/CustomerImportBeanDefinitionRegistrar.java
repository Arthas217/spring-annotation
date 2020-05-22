package org.jixi.customer;

import org.jixi.bean.Green;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

/**
 * 使用@Import时，可以添加自定义类
 * 自定义类把所有需要添加到容器中的bean，通过参数registry中方法registerBeanDefinition手工注册进来
 */
public class CustomerImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //逻辑判断
        boolean color = registry.containsBeanDefinition("org.jixi.bean.Color");
        boolean red = registry.containsBeanDefinition("org.jixi.bean.Red");
        if (red && color) {
            // Register a new bean definition with this registry
            // 类RootBeanDefinition实现BeanDefinition接口
            RootBeanDefinition beanDefinition = new RootBeanDefinition(Green.class);
//            beanDefinition.setTargetType(Green.class);
//            beanDefinition.setScope(SCOPE_SINGLETON);

            // 自定义beanName，beanDefinition
            registry.registerBeanDefinition("customer.green", beanDefinition);
        }
    }
}
