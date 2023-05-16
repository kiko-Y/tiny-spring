package cn.kiko.springframework.aop;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-16
 */
public interface ClassFilter {

    /**
     * Should the pointcut apply to the given interface or target class?
     * @param clazz the candidate target class
     * @return whether the advice should apply to the given target class
     */
    boolean matches(Class<?> clazz);
}
