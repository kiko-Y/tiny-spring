package cn.kiko.springframework.context;

import cn.hutool.core.bean.BeanException;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-15
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     * @throws BeanException
     */
    void refresh() throws BeanException;
}
