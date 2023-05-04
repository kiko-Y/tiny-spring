package cn.kiko.test.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-04-26
 */
public class UserService {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private String name;

    public UserService(String name) {
        this.name = name;
    }

    public UserService() {

    }


    public void queryUserInfo() {
        LOGGER.info("查询用户信息 {}", name);
    }

    @Override
    public String toString() {
        return "UserService{" +
                "name='" + name + '\'' +
                '}';
    }
}
