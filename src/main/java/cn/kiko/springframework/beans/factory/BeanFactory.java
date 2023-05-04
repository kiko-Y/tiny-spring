package cn.kiko.springframework.beans.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.kiko.springframework.beans.BeansException;
import cn.kiko.springframework.beans.factory.config.BeanDefinition;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-04-26
 */
public interface BeanFactory {
    Object getBean(String name) throws BeansException;
}
