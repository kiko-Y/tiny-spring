package cn.kiko.springframework.aop;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-16
 */
public interface PointCut  {

    /**
     * Return the ClassFilter for this pointcut.
     *
     * @return the ClassFilter (never <code>null</code>)
     */
    ClassFilter getClassFilter();

    /**
     * Return the MethodMatcher for this pointcut.
     *
     * @return the MethodMatcher (never <code>null</code>)
     */
    MethodMatcher getMethodMatcher();
}
