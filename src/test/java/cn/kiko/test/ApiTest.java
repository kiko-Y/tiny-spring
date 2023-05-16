package cn.kiko.test;

import org.junit.Test;

import cn.kiko.springframework.aop.AdvisedSupport;
import cn.kiko.springframework.aop.TargetSource;
import cn.kiko.springframework.aop.aspectj.AspectJExpressionPointcut;
import cn.kiko.springframework.aop.framework.Cglib2AopProxy;
import cn.kiko.springframework.aop.framework.JdkDynamicAopProxy;
import cn.kiko.test.aop.IUserService;
import cn.kiko.test.aop.UserService;
import cn.kiko.test.aop.UserServiceInterceptor;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-04
 */
public class ApiTest {
    @Test
    public void testDynamicProxy() {
        IUserService userService = new UserService();

        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* cn.kiko.test.aop.IUserService.*(..))"));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());

        IUserService proxyJdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        System.out.println("测试结果: " + proxyJdk.queryUserInfo());

        IUserService proxyCglib = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        System.out.println("测试结果: " + proxyCglib.queryUserInfo());
    }

}
