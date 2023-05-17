package cn.kiko.springframework.aop.framework;

import cn.kiko.springframework.aop.AdvisedSupport;

/**
 * 用于获取代理对象 封装了 AdvisedSupport
 * @see AdvisedSupport
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-16
 */
public class ProxyFactory {
    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {
        if (advisedSupport.isProxyTargetClass()) {
            return new Cglib2AopProxy(advisedSupport);
        }

        return new JdkDynamicAopProxy(advisedSupport);
    }
}
