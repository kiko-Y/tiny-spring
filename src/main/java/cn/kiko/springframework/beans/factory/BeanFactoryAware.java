package cn.kiko.springframework.beans.factory;

import cn.kiko.springframework.beans.BeansException;

/**
 * 实现此接口，既能感知到所属的 BeanFactory
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-15
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
