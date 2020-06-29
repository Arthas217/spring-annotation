package org.jixi.aop;

/**
 * 业务逻辑类
 *
 * @Author zc217
 * @Date 2020/6/29
 */
public class MathCalculator {

    /**
     * 目标方法
     */
    public int div(int a, int b) {
        System.out.println("MathCalculator类 div方法被调用了.........");
        return a / b;
    }
}
