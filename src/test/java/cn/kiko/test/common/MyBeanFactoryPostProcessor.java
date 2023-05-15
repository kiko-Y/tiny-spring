package cn.kiko.test.common;


import cn.kiko.springframework.beans.BeansException;
import cn.kiko.springframework.beans.PropertyValue;
import cn.kiko.springframework.beans.PropertyValues;
import cn.kiko.springframework.beans.factory.ConfigurableListableBeanFactory;
import cn.kiko.springframework.beans.factory.config.BeanDefinition;
import cn.kiko.springframework.beans.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
    }

}
