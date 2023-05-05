package cn.kiko.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.kiko.springframework.beans.PropertyValue;
import cn.kiko.springframework.beans.PropertyValues;
import cn.kiko.springframework.beans.factory.config.BeanDefinition;
import cn.kiko.springframework.beans.factory.BeanFactory;
import cn.kiko.springframework.beans.factory.config.BeanReference;
import cn.kiko.springframework.beans.factory.support.DefaultListableBeanFactory;
import cn.kiko.test.bean.UserDao;
import cn.kiko.test.bean.UserService;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-04
 */
public class ApiTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void testBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", 10001));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
