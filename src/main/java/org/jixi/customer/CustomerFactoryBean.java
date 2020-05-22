package org.jixi.customer;

import org.jixi.bean.Color;
import org.springframework.beans.factory.FactoryBean;

/**
 * 自定义创建一个spring定义的工长Bean
 */
public class CustomerFactoryBean implements FactoryBean<Color> {

    int count = 0;
    /**
     * 返回Color对象，并添加到容器中
     */
    public Color getObject() throws Exception {
        // 如果不是单例，每次是通过getObject获取的bean
        count++;
        System.out.println("是单例" + isSingleton() + "  ColorFactoryBean......getObject...." +
                "出现第" + count + "次");
        return new Color();
    }

    public Class<?> getObjectType() {
        return Color.class;
    }

    public boolean isSingleton() {
//        return true;
        return false;
    }
}
