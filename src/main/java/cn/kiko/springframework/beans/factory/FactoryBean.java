package cn.kiko.springframework.beans.factory;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-15
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<?> getObjectType();

     default boolean isSingleton() {
         return true;
     }
}
