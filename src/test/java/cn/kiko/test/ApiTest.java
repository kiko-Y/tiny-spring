package cn.kiko.test;

import java.util.Objects;

import org.junit.Test;

import cn.kiko.springframework.context.ApplicationContext;
import cn.kiko.springframework.context.support.ClassPathXmlApplicationContext;
import cn.kiko.test.bean.IUserDao;
import cn.kiko.test.bean.UserService;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-04
 */
public class ApiTest {

    @Test
    public void testFactoryBean() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserDao userDao = applicationContext.getBean("userDao", IUserDao.class);
        System.out.println(userDao.queryUserName("10002"));
    }

    @Test
    public void testUserServiceProto() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:spring.xml");
        UserService userService1 = applicationContext.getBean("userService", UserService.class);
        UserService userService2 = applicationContext.getBean("userService", UserService.class);
        System.out.println(userService1.getIUserDao());
        System.out.println(userService2.getIUserDao());
        System.out.println("same userDao :" + userService1.getIUserDao().equals(userService2.getIUserDao()));
        System.out.println("same service :" + userService1.equals(userService2));
    }

}
