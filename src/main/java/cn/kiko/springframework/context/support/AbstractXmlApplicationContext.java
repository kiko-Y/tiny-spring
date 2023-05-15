package cn.kiko.springframework.context.support;

import cn.kiko.springframework.beans.factory.support.DefaultListableBeanFactory;
import cn.kiko.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-15
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();

}