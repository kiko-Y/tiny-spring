package cn.kiko.test;

import org.junit.Test;

import cn.kiko.springframework.beans.factory.BeanFactory;
import cn.kiko.springframework.context.support.ClassPathXmlApplicationContext;
import cn.kiko.test.bean.UserDao;
import cn.kiko.test.bean.UserService;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-04
 */
public class ApiTest {

    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        applicationContext.registerShutdownHook();
        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);

        System.out.println("userService.getApplicationContext().equals(applicationContext) = "
                + userService.getApplicationContext().equals(applicationContext));
        BeanFactory beanFactory = userService.getBeanFactory();
        UserDao userDao = beanFactory.getBean("userDao", UserDao.class);
        userDao.queryUserName("10002");
    }

}
