package org.jixi;

import org.jixi.bean.Person;
import org.jixi.config.ConfigClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {
        // 通过xml配置文件  spring容器注入bean
        xml();
        // @Configuration配置类 & @Bean 给容器中注册组件
        annotation();
    }

    private static void xml() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        Object person = applicationContext.getBean("person");
        System.out.println(person);
    }

    private static void annotation() {
        AnnotationConfigApplicationContext ap = new AnnotationConfigApplicationContext(ConfigClass.class);
        Person person = ap.getBean(Person.class);
        System.out.println(person);

        String[] namesForType = ap.getBeanNamesForType(Person.class);
        for (String name : namesForType) {
            System.out.println(name);
        }
    }
}
