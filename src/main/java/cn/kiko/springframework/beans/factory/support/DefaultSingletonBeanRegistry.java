package cn.kiko.springframework.beans.factory.support;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.kiko.springframework.beans.BeansException;
import cn.kiko.springframework.beans.factory.DisposableBean;
import cn.kiko.springframework.beans.factory.config.SingletonBeanRegistry;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-04
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    // 如果根据 name 查cacheMap是null，说明没有查过，如果是NULL_OBJECT说明确实是空对象
    protected static final Object NULL_OBJECT = new Object();

    private Map<String, Object> singletonObjects = new HashMap<>();

    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }


    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    @Override
    public void destroySingletons() {
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();

        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }
}
