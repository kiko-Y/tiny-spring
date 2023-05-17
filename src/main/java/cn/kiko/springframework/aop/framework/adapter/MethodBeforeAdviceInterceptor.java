package cn.kiko.springframework.aop.framework.adapter;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import cn.kiko.springframework.aop.MethodBeforeAdvice;

/**
 * 方法前置通知拦截器
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-16
 */
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {

    public MethodBeforeAdvice advice;

    public MethodBeforeAdviceInterceptor(MethodBeforeAdvice advice) {
        this.advice = advice;
    }

    public MethodBeforeAdviceInterceptor() {
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        advice.before(invocation.getMethod(), invocation.getArguments(), invocation.getThis());
        return invocation.proceed();
    }
}
