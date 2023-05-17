package cn.kiko.test.aop;

import cn.kiko.springframework.stereotype.Component;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-17
 */
@Component
public class PhotoService {
    public void show() {
        System.out.println("This is photoService!");
    }
}
