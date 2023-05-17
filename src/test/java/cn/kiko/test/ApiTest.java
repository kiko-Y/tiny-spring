package cn.kiko.test;

import org.junit.Test;

import cn.kiko.springframework.context.ApplicationContext;
import cn.kiko.springframework.context.support.ClassPathXmlApplicationContext;
import cn.kiko.test.aop.PhotoService;
import cn.kiko.test.aop.UserService;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-04
 */
public class ApiTest {
    @Test
    public void testPlaceholderReplace() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String token = userService.getToken();
        System.out.println("token = " + token);
        String userInfo = userService.queryUserInfo();
        System.out.println("userInfo = " + userInfo);
    }

    @Test
    public void testComponentScan() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        PhotoService photoService = applicationContext.getBean("photoService", PhotoService.class);
        photoService.show();
    }
}
