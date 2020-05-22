package org.jixi.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 实现接口方式 自定义容器bean的初始化和销毁
 * 这里使用的是 组件@Component + 扫码方式  + 多实例
 */
@Component
@Scope(value = "prototype")
public class Dog implements InitializingBean, DisposableBean {

    public Dog() {
        System.out.println("dog ,,,,, construct ....");
    }

    /**
     * 容器创建，所有属性赋值好后，相当于init
     */
    public void afterPropertiesSet() throws Exception {
        System.out.println("dog .... init .... ");
    }

    public void destroy() throws Exception {
        System.out.println("dog ..... destroy ....");
    }
}
