package cn.kiko.test.aop;

import java.lang.reflect.Method;

import cn.kiko.springframework.aop.MethodBeforeAdvice;

public class UserServiceBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法：" + method.getName());
    }

}
