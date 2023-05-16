package cn.kiko.test.bean;

import cn.kiko.springframework.beans.BeansException;
import cn.kiko.springframework.beans.factory.BeanClassLoaderAware;
import cn.kiko.springframework.beans.factory.BeanFactory;
import cn.kiko.springframework.beans.factory.BeanFactoryAware;
import cn.kiko.springframework.beans.factory.BeanNameAware;
import cn.kiko.springframework.context.ApplicationContext;
import cn.kiko.springframework.context.ApplicationContextAware;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-04-26
 */
public class UserService {

    private String uId;
    private String company;
    private String location;
    private IUserDao userDao;

    public String queryUserInfo() {
        return userDao.queryUserName(uId) + "," + company + "," + location;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public IUserDao getIUserDao() {
        return userDao;
    }

    public void setIUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }
}