package cn.kiko.springframework.beans.factory;

import java.io.IOException;
import java.util.Properties;

import cn.kiko.springframework.beans.BeansException;
import cn.kiko.springframework.beans.PropertyValue;
import cn.kiko.springframework.beans.PropertyValues;
import cn.kiko.springframework.beans.factory.config.BeanDefinition;
import cn.kiko.springframework.beans.factory.config.BeanFactoryPostProcessor;
import cn.kiko.springframework.core.io.DefaultResourceLoader;
import cn.kiko.springframework.core.io.Resource;

/**
 * BeanDefinition String 属性 属性值占位替换处理器
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-17
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {
    /**
     * Default placeholder prefix: {@value}
     */
    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";

    /**
     * Default placeholder suffix: {@value}
     */
    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";


    private String location;


    /**
     * 属性中的占位符进行替换
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        try {
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            Properties properties = new Properties();
            properties.load(resource.getInputStream());
            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();

            for (String beanDefinitionName : beanDefinitionNames) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
                PropertyValues propertyValues = beanDefinition.getPropertyValues();

                for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                    Object value = propertyValue.getValue();
                    if (!(value instanceof String)) continue;
                    String strVal = (String) value;
                    StringBuilder stringBuilder = new StringBuilder(strVal);
                    int startIndex = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
                    int stopIndex = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
                    if (startIndex != -1 && stopIndex != -1 && startIndex < stopIndex) {
                        String propKey = strVal.substring(startIndex + 2, stopIndex);
                        String propVal = properties.getProperty(propKey);
                        stringBuilder.replace(startIndex, stopIndex + 1, propVal);
                        propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), stringBuilder.toString()));
                    }
                }
            }
        } catch (IOException e) {
            throw new BeansException("Could not load properties", e);
        }
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
