package cn.kiko.springframework.beans.factory.support;

import java.lang.reflect.Constructor;

import cn.kiko.springframework.beans.BeansException;
import cn.kiko.springframework.beans.factory.config.BeanDefinition;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-04
 */
public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;
}
