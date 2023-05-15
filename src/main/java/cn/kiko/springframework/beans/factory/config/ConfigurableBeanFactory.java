package cn.kiko.springframework.beans.factory.config;

import cn.kiko.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-06
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}
