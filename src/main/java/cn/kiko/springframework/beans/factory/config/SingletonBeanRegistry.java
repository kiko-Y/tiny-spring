package cn.kiko.springframework.beans.factory.config;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-04
 * 获取单例对象的接口
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

    /**
     * 销毁单例对象
     */
    void destroySingletons();


    void registerSingleton(String beanName, Object singletonObject);
}
