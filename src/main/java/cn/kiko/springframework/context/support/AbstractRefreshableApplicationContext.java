package cn.kiko.springframework.context.support;

import cn.kiko.springframework.beans.BeansException;
import cn.kiko.springframework.beans.factory.ConfigurableListableBeanFactory;
import cn.kiko.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-15
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);


    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

}
