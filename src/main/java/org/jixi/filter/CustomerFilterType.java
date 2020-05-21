package org.jixi.filter;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * 自定义过滤规则 返回值代表匹配还是不匹配
 */
public class CustomerFilterType implements TypeFilter {
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        // 获取当前类注解信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        // 获取当前类正在扫描类的信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        // 获取当前类资源（类的路径）
        Resource resource = metadataReader.getResource();
        String className = classMetadata.getClassName();
        System.out.println(annotationMetadata + "----->" + classMetadata + "----->" + resource + "----->" + className);

        // 简单做个逻辑判断
        // 返回true代表保留这个bean，因为匹配 && 使用的是includeFilters
        if (className.contains("er")) {
            return true;
        }
        return false;
    }
}
