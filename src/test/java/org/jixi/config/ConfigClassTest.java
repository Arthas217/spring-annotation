package org.jixi.config;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.jixi.aop.MathCalculator;
import org.jixi.bean.*;
import org.jixi.config.aop.AopConfigClass;
import org.jixi.control.BookController;
import org.jixi.dao.BookDao;
import org.jixi.service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
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

    public void getBeanNameByDefinition() {
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
        getBeanNameByDefinition();
    }

    @Test
    public void testScopeAndLazy() {
        // 容器启动并实例化
        ap = new AnnotationConfigApplicationContext(ConfigClass2.class);
        getBeanNameByDefinition();
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
        getBeanNameByDefinition();
    }

    @Test
    public void testFactoryBean() {
        ap = new AnnotationConfigApplicationContext(ConfigClass5.class);
        getBeanNameByDefinition();

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
        Dog dog = (Dog) ap.getBean("dog");
        ap.close();

    }

    @Test
    public void testLife3() {
        ap = new AnnotationConfigApplicationContext(ConfigClassForLifeCycle.class);
//        Cat cat = (Cat) ap.getBean("cat");
        ap.close();

    }

    @Test
    public void testLife4() {
        ap = new AnnotationConfigApplicationContext(ConfigClassForLifeCycle.class);
        ap.close();
    }

    @Test
    public void testPropertyValue() {
        ap = new AnnotationConfigApplicationContext(PropertyValueConfigClass.class);
        getBeanNameByDefinition();
        System.out.println(ap.getBean(PersonValue.class));

        //获取读取配置文件加载后，运行的环境变量中，通过它也能获取配置中的值
        ConfigurableEnvironment environment = ap.getEnvironment();
        String property = environment.getProperty("pv.nickName");
        System.out.println(property);
        ap.close();
    }

    @Test
    public void testAutoWired() {
        ap = new AnnotationConfigApplicationContext(AutowiredConfigClass.class);

        BookController bookController = ap.getBean(BookController.class);
        bookController.printBookService();

        //判断是否是容器中的BookService
        BookService bookService = ap.getBean(BookService.class);
        System.out.println(bookService);

        bookService.printBookDao();

        //注意如果BookDao在容器中出现多个的话，此处是报错的,因为是按类型找的
//        BookDao dao = ap.getBean(BookDao.class);

        Boss bean = ap.getBean(Boss.class);
        System.out.println(bean);
        Car car = ap.getBean(Car.class);
        System.out.println(car);
        Color color = ap.getBean(Color.class);
        System.out.println(color);

        System.out.println("测试使用的ap："+ap);
        ap.close();
    }


    /**
     * 第一种 激活方式  虚拟机启动参数  -Dspring.profiles.active=dev
     */
    @Test
    public void testProfile(){
        ap = new AnnotationConfigApplicationContext(ProfileConfigClass.class);
        getBeanNameByType(DataSource.class);
        ap.close();
    }

    /**
     * 第二种 代码激活方式 需要无参构造器，要保留完成有参中三个过程
     */
    @Test
    public void testProfile1(){
        //1）创建ApplicationContext对象（得到ioc容器）
        ap = new AnnotationConfigApplicationContext();
        //2) 需要设置激活的环境、注册register主配置类
        ap.getEnvironment().setActiveProfiles("dev");
        ap.register(ProfileConfigClass.class);
        //3) 刷新refresh容器
        ap.refresh();
        getBeanNameByType(DataSource.class);
        ap.close();
    }


    @Test
    public void testAOP(){
        ap = new AnnotationConfigApplicationContext(AopConfigClass.class);
        // 不要自己new一个 MathCalculator，而是使用容器中的组件MathCalculator
        MathCalculator mathCalculator = ap.getBean(MathCalculator.class);
        mathCalculator.div(1,1);
        ap.close();
    }


}