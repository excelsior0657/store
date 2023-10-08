package com.ss.store.service;

import com.ss.store.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

// 用户模块，用户注册功能，用户登录功能
public interface IUserService {
    void reg(User user);
    User login(String username,String password);
    void changePassword(Integer uid,String username,String oldPassword,String newPassword);
    void changeInfo(Integer uid,String username,User user);
    User getByUid(Integer uid);
    void changeAvatar(Integer uid, String avatar, String username);
}
