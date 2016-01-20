package com.zplcod.services;

import com.zplcod.dao.LoginlogDao;
import com.zplcod.dao.UserDao;
import com.zplcod.domain.Loginlog;
import com.zplcod.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Clancy on 2016/1/20.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private LoginlogDao loginlogDao;

    public boolean hasMatchUser(String name, String password) {
        return userDao.getMatchCount(name, password) > 0;

    }

    public User findUserbyUserName(String name){
        return userDao.findUserByName(name);
    }

    public void loginSuccess(User user  ){
        user.setCredits(5+user.getCredits());
        Loginlog loginlog = new Loginlog();
        loginlog.setUserId(user.getUserId());
        loginlog.setIp(user.getLastIp());
        loginlog.setLoginDate(user.getLastVisit());
        userDao.updateLoginInfo(user);
        loginlogDao.insertLoginLog(loginlog);

    }
    public LoginlogDao getLoginlogDao() {
        return loginlogDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }


}
