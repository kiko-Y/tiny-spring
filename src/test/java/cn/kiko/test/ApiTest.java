package cn.kiko.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.kiko.springframework.beans.factory.support.DefaultListableBeanFactory;
import cn.kiko.springframework.beans.factory.xml.XmlBeanDefinitionReader;
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

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        // 从 spring.xml 中加载 beanDefinition
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
        System.out.println("uId = " + userService.getuId());
    }
}
