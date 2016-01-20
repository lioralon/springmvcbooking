package com.zplcod.dao;

import com.zplcod.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Clancy on 2016/1/20.
 */
@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int getMatchCount(String name, String password) {
        String sql = "select count(*) from t_user where user_name=? and password =?";
        return jdbcTemplate.queryForObject(sql, Integer.class, new Object[]{name, password});
    }

    public User findUserByName(String name) {
        String sql = "select user_id,user_name,credits,last_visit,last_ip from t_user where user_name=?";
        User user = new User();
        jdbcTemplate.query(sql, new Object[]{name}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserId(resultSet.getInt("user_id"));
                user.setCredits(resultSet.getInt("credits"));
                user.setUserName(resultSet.getString("user_name"));
                user.setLastIp(resultSet.getString("last_ip"));
                String date = resultSet.getString("last_visit");

                if (!date.isEmpty()) {
                    try {
                        user.setLastVisit(new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").parse(date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return user;
    }

    public void updateLoginInfo(User user) {
        String sql = "update t_user set last_visit =?,last_ip=?, credits=? where user_id=?";
        jdbcTemplate.update(sql, new Object[]{user.getLastVisit(), user.getLastIp(), user.getCredits(), user.getUserId()});

    }
}
