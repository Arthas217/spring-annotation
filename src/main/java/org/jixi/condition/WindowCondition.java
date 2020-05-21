package org.jixi.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 同MacCondition类
 */
public class WindowCondition implements Condition {
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String osname = context.getEnvironment().getProperty("os.name");
        return osname.contains("Windows");
    }
}
