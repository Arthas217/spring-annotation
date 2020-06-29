package org.jixi.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * 日志切面类
 *
 * @Author zc217
 * @Date 2020/6/29
 */
@Aspect
public class LogAspect {

    // 抽取公共的切入点表达式
    // 1）本类引用   例如 @Before
    // 2）其他的切面类引用   例如 @After
    @Pointcut("execution(public int org.jixi.aop.MathCalculator.div(int,int))")
    public void pointCut() {
    }

    // 前置通知
    // 切入点表达式 指明目标方法div执行之前切入
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();// 方法签名
        String name = signature.getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("除法开始运行 ....  @Before注解 方法名" + name + "参数列表是 "
                + Arrays.asList(args));
    }

    // 后置通知 (无论方法是正常还是异常结束，都会执行）
    @After("org.jixi.aop.LogAspect.pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println("除法运行结束 ....  @After注解 方法名" + joinPoint.getSignature().getName()
                + "参数列表是 " + Arrays.asList(joinPoint.getArgs()));
    }

    // 返回通知 (---注意参数 JoinPoint joinPoint,必须在参数表的第一个位置，否则报错）
    @AfterReturning(value = "pointCut()", returning = "returnResult")
    public void logReturn(JoinPoint joinPoint,Object returnResult) {
        System.out.println("除法正常返回 .... @AfterReturning注解 方法名"+joinPoint.getSignature().getName()
                +"返回值是 " + returnResult);
    }

    // 异常通知
    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void logException(JoinPoint joinPoint,Exception exception) {
        System.out.println("除法异常 ....  @AfterThrowing注解  方法名"+joinPoint.getSignature().getName()
                +"异常信息是 " + exception);
    }

    // 注意方法返回值问题
    // 环绕监听会对有返回值的方法做处理，所以需要对logAround方法修改一下，让它有一个返回值，返回object && joinPoint.proceed()
    // 报错org.springframework.aop.AopInvocationException: Null return value from advice does not match primitive return type for: public int org.jixi.aop.MathCalculator.div(int,int)
    @Around("pointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("除法环绕 ....  @Around注解  方法名"+joinPoint.getSignature().getName()
                +"参数列表是 " + Arrays.asList(joinPoint.getArgs()));
        return joinPoint.proceed(); // 阻塞
    }
}
