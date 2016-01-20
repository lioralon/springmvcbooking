package com.zplcod.dao;

import com.zplcod.domain.Loginlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by Clancy on 2016/1/20.
 */
@Repository
public class LoginlogDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertLoginLog(Loginlog loginlog){
        String sql="insert into t_login_log(user_id,ip,login_datetime) values(?,?,?)";
        Object[] args= {loginlog.getUserId(),loginlog.getIp(),loginlog.getLoginDate()};
        jdbcTemplate.update(sql,args);

    }
}
