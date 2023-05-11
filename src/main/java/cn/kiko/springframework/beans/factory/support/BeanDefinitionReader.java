package cn.kiko.springframework.beans.factory.support;

import cn.kiko.springframework.beans.BeansException;
import cn.kiko.springframework.core.io.Resource;
import cn.kiko.springframework.core.io.ResourceLoader;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-05
 */
public interface BeanDefinitionReader {
    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String... locations) throws BeansException;
}
