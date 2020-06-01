package org.jixi.customer;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 帮助此组件注入IOC容器
 * 之所以会走到这里组件bean是因为在bean初始化之前，走Bean后置处理器接口 ，ApplicationContextAwareProcessor是实现类
 * 这个实现类中postProcessBeforeInitialization方法会判断ApplicationContextAware接口
 * 可以在该类的第20行打断点，查下调用栈
 */
@Component
public class CustomerApplicationContextAware implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
