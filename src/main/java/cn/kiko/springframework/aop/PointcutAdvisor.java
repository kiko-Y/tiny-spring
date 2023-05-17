package cn.kiko.springframework.aop;

/**
 * 切入点 + 通知
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-16
 */
public interface PointcutAdvisor extends Advisor {


    /**
     * Get the Pointcut that drives this advisor.
     */
    Pointcut getPointcut();
}
