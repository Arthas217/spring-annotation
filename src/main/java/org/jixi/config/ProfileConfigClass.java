package org.jixi.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.jixi.bean.Green;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * 含义：spring提供的可以根据当前环境，动态的激活和切换一系列组件的功能
 * 作用：指定组件在那个环境的情况下才能被注册到容器中，不指定的话，任何环境下该组件都能注册到容器中
 * dev test prod（数据源）
 */
@Configuration
// 加载外部配置文件
@PropertySource("classpath:/dbconfig.properties")
//@Profile("dev")
public class ProfileConfigClass implements EmbeddedValueResolverAware {

    //  @Value使用方式1
    @Value("${db.user}")
    private String user;

    // 使用方式3
    private String driverClass;

    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.driverClass = resolver.resolveStringValue("${db.driverClass}");
    }

    @Profile("test")
    @Bean
    public Green green(){
        System.out.println("green---------test");
        return new Green();
    }

    @Profile("test")
    @Bean("testDataSource")
    // @Value使用方式2
    public DataSource dataSourceTest(@Value("${db.password}") String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Profile("dev") //加环境标识@Profile("dev")，只有dev环境被激活组件才能注册到容器中
    @Bean("devDataSource")
    public DataSource dataSourceDev(@Value("${db.password}") String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/dev");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }


    @Profile("default")
    @Bean("prodDataSource")
    public DataSource dataSourceProd(@Value("${db.password}") String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/prod");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }


}
