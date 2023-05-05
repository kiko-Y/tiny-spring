package cn.kiko.test.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-04-26
 */
public class UserService {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private String uId;

    private UserDao userDao;

    public void queryUserInfo() {
        System.out.println("查询用户信息：" + userDao.queryUserName(uId));
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
