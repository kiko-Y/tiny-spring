package cn.kiko.springframework.beans.factory.support;

import java.util.HashMap;
import java.util.Map;

import cn.kiko.springframework.beans.BeansException;
import cn.kiko.springframework.beans.factory.config.BeanDefinition;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-04
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);

        if (beanDefinition == null)
            throw new BeansException("No bean named '" + beanName + "' is defined");
        return beanDefinition;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }
}