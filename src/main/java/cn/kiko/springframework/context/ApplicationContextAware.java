package cn.kiko.springframework.context;

import cn.kiko.springframework.beans.BeansException;
import cn.kiko.springframework.beans.factory.Aware;

/**
 * 实现此接口，既能感知到所属的 ApplicationContext
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-15
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
