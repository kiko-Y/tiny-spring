package cn.kiko.test.bean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

import cn.kiko.springframework.beans.factory.FactoryBean;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-16
 */
public class ProxyBeanFactory implements FactoryBean<IUserDao> {

    @Override
    public IUserDao getObject() throws Exception {
        return (IUserDao) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class<?>[] {IUserDao.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


                        // 添加排除方法
                        if ("toString".equals(method.getName())) return this.toString();

                        if ("equals".equals(method.getName())) return this.equals(args[0]);

                        if ("hashCode".equals(method.getName())) return this.hashCode();

                        HashMap<String, String> map = new HashMap<>() {
                            {
                                put("10001", "Jessi");
                                put("10002", "lucy");
                                put("10003", "James");
                            }
                        };
                        return "You are proxied " + method.getName() + ": " + map.get(args[0].toString());
                    }
                });
    }

    @Override
    public Class<?> getObjectType() {
        return IUserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
