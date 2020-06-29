package org.jixi.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 在ioc容器中组件Boss，容器启动后默认调用无参构造器来创建对象，再进行初始化赋值等操作
 */
@Component
public class Boss {

    @Autowired  //标注属性上
    private Car car;

    @Autowired // 标注有参构造器
    // 方法参数自定义类型的值从ioc容器获取的car
    public Boss(Car car){
       this.car =car;
        System.out.println("Boss 有参构造器......");
    }

    @Autowired //标注方法上
    // spring容器创建当前对象，就会调用方法，完成赋值
    // 方法参数自定义类型的值从ioc容器获取的car
    public void setCar(Car car) {
        System.out.println("Boss setter方法......");
        this.car = car;
    }

//    // 标注在参数 car上（如果只有一个有参构造器，此注解可以省略）
//    public Boss(@Autowired Car car){
//        this.car =car;
//        System.out.println("Boss 有参car构造器......");
//    }

    public Car getCar() {
        return car;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "car=" + car +
                '}';
    }
}
