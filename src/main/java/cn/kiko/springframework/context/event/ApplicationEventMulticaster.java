package cn.kiko.springframework.context.event;

import cn.kiko.springframework.context.ApplicationEvent;
import cn.kiko.springframework.context.ApplicationListener;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-16
 */
public interface ApplicationEventMulticaster {

    /**
     * 添加 listener
     * @param listener
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * 移除 listener
     * @param listener
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * 广播事件
     * @param event 待广播事件
     */
    void multicastEvent(ApplicationEvent event);
}
