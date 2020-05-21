package org.jixi.config;

import org.jixi.bean.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 配置类ConfigClass相关注解测试
 */
public class ConfigClassTest {

    /**
     * 通过xml配置文件  spring容器注入bean
     */
    @Test
    public void testXmlPerson() {
        // Ioc容器对象   参数传配置文件的位置
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        // 得到组件bean 可以使用id、类型
        Object person = applicationContext.getBean("person");
//        Object person = applicationContext.getBean(Person.class);
        System.out.println(person);
    }

    /**
     * 通过注解 @Configuration & @Bean
     * 给容器中注册组件person
     */
    @Test
    public void testConfiguration() {
        // 参数是配置类class  （相当于传配置类的位置）
        AnnotationConfigApplicationContext ap = new AnnotationConfigApplicationContext(ConfigClass.class);
        Person person = ap.getBean(Person.class);
        System.out.println(person);
        String[] namesForType = ap.getBeanNamesForType(Person.class);
        for (String name : namesForType) {
            System.out.println(name);
        }
    }

    /**
     * 测试包扫描的bean名字
     * 以下是我们自己定义的bean名字
     * configClass1
     * configClass
     * configClass2
     * bookController
     * bookDao
     * bookService
     * person
     */
    @Test
    public void testComponentScan() {
        AnnotationConfigApplicationContext ap = new AnnotationConfigApplicationContext(ConfigClass1.class);
        String[] beanDefinitionNames = ap.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
    }

    @Test
    public void test02(){
        AnnotationConfigApplicationContext ap = new AnnotationConfigApplicationContext(ConfigClass2.class);
        String[] beanDefinitionNames = ap.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
        Person person1 = (Person)ap.getBean("person");
        Person person2 = (Person)ap.getBean("person");
        System.out.println(person1 == person2);
    }
}