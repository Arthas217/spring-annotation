import org.jixi.bean.Person;
import org.jixi.config.ConfigClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 主测试类
 */
public class Test {


    @SuppressWarnings("resource")
    public static void main(String[] args) {

        // 通过xml配置文件  spring容器注入bean
//        xml();

        // @Configuration配置类 & @Bean 给容器中注册组件
        annotation();
    }

    private static void xml() {
        // Ioc容器对象   参数传配置文件的位置
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        // 得到组件bean 可以使用id、类型
        Object person = applicationContext.getBean("person");
//        Object person = applicationContext.getBean(Person.class);
        System.out.println(person);
    }

    private static void annotation() {
        // 参数是注解类的Class类型  （相当于传配置类的位置）
        AnnotationConfigApplicationContext ap = new AnnotationConfigApplicationContext(ConfigClass.class);
        Person person = ap.getBean(Person.class);
        System.out.println(person);

        String[] namesForType = ap.getBeanNamesForType(Person.class);
        for (String name : namesForType) {
            System.out.println(name);
        }
    }
}
