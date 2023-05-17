package cn.kiko.springframework.aop.framework.autoproxy;

import java.util.Collection;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import cn.kiko.springframework.aop.AdvisedSupport;
import cn.kiko.springframework.aop.Advisor;
import cn.kiko.springframework.aop.ClassFilter;
import cn.kiko.springframework.aop.Pointcut;
import cn.kiko.springframework.aop.TargetSource;
import cn.kiko.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import cn.kiko.springframework.aop.framework.ProxyFactory;
import cn.kiko.springframework.beans.BeansException;
import cn.kiko.springframework.beans.factory.BeanFactory;
import cn.kiko.springframework.beans.factory.BeanFactoryAware;
import cn.kiko.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import cn.kiko.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * 自动代理
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-16
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    /**
     * 给指定的 bean 进行代理，看是否符合某个切入点表达式，符合就进行代理
     * @param beanClass
     * @param beanName
     * @return 代理对象
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {

        if (isInfrastructureClass(beanClass)) return null;

        Collection<AspectJExpressionPointcutAdvisor>
                advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();

        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            if (!classFilter.matches(beanClass)) continue;

            AdvisedSupport advisedSupport = new AdvisedSupport();

            TargetSource targetSource = null;
            try {
                targetSource = new TargetSource(beanClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(false);

            return new ProxyFactory(advisedSupport).getProxy();

        }

        return null;
    }

    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass) || Advisor.class.isAssignableFrom(beanClass);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
