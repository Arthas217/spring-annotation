package org.jixi.customer;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 使用@Import时，可以添加自定义类
 * 自定义实现逻辑，返回需要导入的组件的全类名数组
 */
public class CustomerImportSelector implements ImportSelector {
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // 参数importingClassMetadata可以得到当前标注@Import注解的类的所有注解信息
        return new String[]{"org.jixi.bean.Red"};
    }
}
