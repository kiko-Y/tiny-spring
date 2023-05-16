package cn.kiko.springframework.aop;

import java.lang.reflect.Method;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-16
 */
public interface MethodMatcher {

    /**
     * Perform static checking whether the given method matches. If this
     * @return whether or not this method matches statically
     */
    boolean matches(Method method, Class<?> targetClass);
}
