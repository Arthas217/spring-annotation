package org.jixi.customer;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 自定义后置处理器
 * 自定义容器初始化前后的处理工作
 * 接口中的2个方法返回值 可以是传过来的bean，也可在方法中包装之后的bean对象
 * @ComponentScan + @Component + 单例
 * 可以在此类中的第17行打断点，查看下栈信息
 */
@Component// 将此组件加入到容器中
public class CustomerBeanPostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("-----------postProcessBeforeInitialization------------" + beanName + bean);
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("-----------postProcessAfterInitialization------------" + beanName + bean);
        return bean ;
    }
}
