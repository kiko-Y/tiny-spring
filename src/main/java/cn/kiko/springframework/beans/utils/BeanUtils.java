package cn.kiko.springframework.beans.utils;

import java.lang.reflect.Field;

import cn.kiko.springframework.beans.BeansException;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-05
 */
public class BeanUtils {

    public static void setFieldValue(Object bean, String name, Object value) {
        Field field;
        try {
            field = bean.getClass().getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            throw new BeansException("No such field");
        }
        field.setAccessible(true);
        try {
            field.set(bean, value);
        } catch (IllegalAccessException e) {
            throw new BeansException("Set field error", e);
        }
    }
}
