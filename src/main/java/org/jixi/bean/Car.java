package org.jixi.bean;

/**
 * 以@Bean方式，自定义容器的初始化和销毁
 */
public class Car {

    public Car() {
        System.out.println("car .... construct .,,,");
    }

    public void init() {
        System.out.println("car .... init ....");
    }

    public void destroy() {
        System.out.println("car .... destroy ....");
    }
}
