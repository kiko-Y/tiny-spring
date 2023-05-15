package cn.kiko.springframework.beans.factory;

/**
 * 实现此接口，既能感知到所属的 ClassLoader
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-15
 */
public interface BeanClassLoaderAware extends Aware {

    void setBeanClassLoader(ClassLoader classLoader);

}
