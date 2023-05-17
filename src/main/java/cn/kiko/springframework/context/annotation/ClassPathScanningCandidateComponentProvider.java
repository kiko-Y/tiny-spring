package cn.kiko.springframework.context.annotation;

import java.util.LinkedHashSet;
import java.util.Set;

import cn.hutool.core.util.ClassUtil;
import cn.kiko.springframework.beans.factory.config.BeanDefinition;
import cn.kiko.springframework.stereotype.Component;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-17
 */
public class ClassPathScanningCandidateComponentProvider {

    /**
     * 扫描包中的所有包含 @Component 注解的类
     * @param basePackage 扫描包
     * @return
     */
    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        LinkedHashSet<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }
}
