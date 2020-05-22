package org.jixi.bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @PostConstruct & @PreDestroy  & 单例
 * 通过注解方式，自定义容器bean的初始化和销毁
 */
@Component
public class Cat {

    public Cat() {
        System.out.println("Cat .... construct ....");
    }

    /**
     * 对象创建，赋值之后，调用
     */
    @PostConstruct()
    public void init() {
        System.out.println("Cat .... PostConstruct ....");
    }

    /**
     * 容器移除对象前，回调通知 清理工作
     */
    @PreDestroy()
    public void destroy() {
        System.out.println("Cat .... PreDestroy ....");
    }
}
