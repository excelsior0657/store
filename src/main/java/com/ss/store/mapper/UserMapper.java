package com.ss.store.mapper;

import com.ss.store.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;


public interface UserMapper {
    Integer insert(User user);
    User findByUsername(String username);
    Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);
    User findByUid(Integer uid);
    Integer updateInfoByUid(User user);
    // @Param("SQL映射文件中#{}占位符的变量名"): 当SQL语句的占位符和映射文件的接口方法参数名不一样时
    Integer updateAvatarByUid(@Param("uid") Integer uid,
                              @Param("avatar") String avatar,
                              @Param("modifiedUser") String modifiedUser,
                              @Param("modifiedTime") Date modifiedTime);
}
