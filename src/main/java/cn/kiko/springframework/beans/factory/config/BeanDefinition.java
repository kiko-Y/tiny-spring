package cn.kiko.springframework.beans.factory.config;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-04-26
 */
public class BeanDefinition {
    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
