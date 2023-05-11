package cn.kiko.springframework.beans.factory;

import cn.kiko.springframework.beans.BeansException;
import cn.kiko.springframework.beans.factory.config.AutowireCapableBeanFactory;
import cn.kiko.springframework.beans.factory.config.BeanDefinition;
import cn.kiko.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-06
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory,
        ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

}
