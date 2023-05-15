package cn.kiko.springframework.context.support;

import cn.kiko.springframework.beans.BeansException;
import cn.kiko.springframework.beans.factory.config.BeanPostProcessor;
import cn.kiko.springframework.context.ApplicationContext;
import cn.kiko.springframework.context.ApplicationContextAware;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-15
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
