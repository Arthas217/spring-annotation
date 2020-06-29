package org.jixi.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * 启动容器，将Red自定义组件加入容器中，red组件要使用到spring组件，必须实现了xxxAware接口，以接口回调方法方式。
 * 把spring底层一些组件注入到自定义的Bean中，然后加入到ioc容器中。
 *
 * 原理：在bean初始化之前，走Bean后置处理器接口 ，ApplicationContextAwareProcessor实现类中postProcessBeforeInitialization方法
 * 会判断ApplicationContextAware接口，把bean转化为相应的类型，然后setApplicationContext方法
 */
@Component
public class Red implements ApplicationContextAware, BeanNameAware, EmbeddedValueResolverAware {

    // 用此变量保存ioc容器
    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        System.out.println("自定义bean中传入的ioc容器" + applicationContext);

    }

    public void setBeanName(String name) {
        System.out.println("自定义当前bean的名字" + name);
    }

    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        String s = resolver.resolveStringValue("你好${os.name},我是#{20*2}");
        System.out.println("自定义bean解析的字符串 " + s);
    }
}
