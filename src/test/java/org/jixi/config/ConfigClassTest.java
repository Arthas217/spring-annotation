package org.jixi.config;

import org.jixi.bean.Car;
import org.jixi.bean.Dog;
import org.jixi.bean.Person;
import org.jixi.bean.Red;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;


/**
 * 配置类ConfigClass相关注解测试
 */
public class ConfigClassTest {

    AnnotationConfigApplicationContext ap;

    private void getBeanNameByType(Class c) {
        String[] beanNamesForType = ap.getBeanNamesForType(c);
        for (String name : beanNamesForType) {
            System.out.println(name);
        }
    }

    public void getBeanNameByDefinition(AnnotationConfigApplicationContext ap) {
        String[] beanDefinitionNames = ap.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
    }

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
        ap = new AnnotationConfigApplicationContext(ConfigClass.class);
        Person person = ap.getBean(Person.class);
        System.out.println(person);
        getBeanNameByType(person.getClass());
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
        ap = new AnnotationConfigApplicationContext(ConfigClass1.class);
        getBeanNameByDefinition(ap);
    }

    @Test
    public void testScopeAndLazy() {
        // 容器启动并实例化
        ap = new AnnotationConfigApplicationContext(ConfigClass2.class);
        getBeanNameByDefinition(ap);
        // 容器获取bean
        Person person1 = (Person) ap.getBean("person");
        Person person2 = (Person) ap.getBean("person");
        System.out.println(person1 == person2);
    }

    @Test
    public void testCondition() {
        // condition条件是判断系统是mac还是windows
        ap = new AnnotationConfigApplicationContext(ConfigClass3.class);
        getBeanNameByType(Person.class);
        // 获取所有bean及信息
        Map<String, Person> beansOfType = ap.getBeansOfType(Person.class);
        System.out.println(beansOfType);
    }

    @Test
    public void testImport() {
        ap = new AnnotationConfigApplicationContext(ConfigClass4.class);
        getBeanNameByDefinition(ap);
    }

    @Test
    public void testFactoryBean() {
        ap = new AnnotationConfigApplicationContext(ConfigClass5.class);
        getBeanNameByDefinition(ap);

        // 虽然获取的bean的名称是customerFactoryBean
        // 但是通过FactoryBean方式获取对象是getObject()返回的类型对象。
        Object bean1 = ap.getBean("customerFactoryBean");
        System.out.println("bean的类型：" + bean1.getClass());
        Object bean2 = ap.getBean("customerFactoryBean");
        System.out.println("两次获取bean是否是为同一个bean:" + (bean1 == bean2));

        Object bean3 = ap.getBean("&customerFactoryBean");
        System.out.println("想要ConfigClass5中@Bean标签的本身的这个bean（拿工长Bean的本身）：" + bean3.getClass());
    }

    @Test
    public void testLife() {

//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
//        Car car = (Car)applicationContext.getBean("car");
//        System.out.println(car);
//        car.destroy();

        ap = new AnnotationConfigApplicationContext(ConfigClassForLifeCycle.class);
        Car car = (Car) ap.getBean("car");
        Car car1 = (Car) ap.getBean("car");
        System.out.println(car);
        System.out.println(car1);
        ap.close();
        car.destroy();
        car1.destroy();
    }

    @Test
    public void testLife2() {
        ap = new AnnotationConfigApplicationContext(ConfigClassForLifeCycle.class);
        Car car = (Car) ap.getBean("car");

        // 当dog类 是多实例时，获取dog对象是才构建和初始化
        Dog dog = (Dog)ap.getBean("dog");
        ap.close();

    }
}