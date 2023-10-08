package com.ss.store.service.impl;

import com.ss.store.entity.User;
import com.ss.store.mapper.UserMapper;
import com.ss.store.service.IUserService;
import com.ss.store.service.ex.*;
import javax.annotation.Resource;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        // 判断用户名是否被注册
        String username=user.getUsername();
        User result=userMapper.findByUsername(username);
        if(result!=null){
            throw new UsernameDuplicatedException("用户名被占用");
        }
        // 密码加密方式: 用md5算法连续加载3次
        // (盐值(一个随机的字符串) + password + 盐值)
        String oldPassword=user.getPASSWORD();
        // 随机生成一个盐值
        String salt= UUID.randomUUID().toString().toUpperCase();
        // 将密码和盐值作为一个整体进行加密,忽略了原有密码的强度，提升了数据的安全性
        String md5Password=getMD5Password(oldPassword,salt);
        // 将加密后的密码设置到user
        user.setPASSWORD(md5Password);

        // 补全数据: 盐值的记录
        user.setSalt(salt);
        // 补全数据: is_delete设置为0
        user.setIsDelete(0);
        // 补全数据: 4个日志字段信息
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date=new Date();
        user.setCreateTime(date);
        user.setModifiedTime(date);

        Integer rows=userMapper.insert(user);
        if(rows!=1){
            throw new InsertException("在用户注册过程中产生了未知异常");
        }
    }

    @Override
    public User login(String username, String password) {
        // 根据用户名称查询用户是否存在，若不存在则抛出异常
        User result = userMapper.findByUsername(username);
        if(result==null){
            throw new UserNotFoundException("用户不存在");
        }
        // 检测用户的密码是否匹配
        // 1. 获取数据库加密后的密码
        String oldPassword= result.getPASSWORD();
        // 2. 将用户密码进行相同的md5算法加密,然后比较
        // 2.1 获取盐值
        String salt=result.getSalt();
        // 2.2 md5算法加密
        String newMd5Password=getMD5Password(password,salt);
        // 3. 比较密码
        if(!newMd5Password.equals(oldPassword)){
            throw new PasswordNotMatchException("用户密码错误");
        }
        // 判断is_delete字段值是否为1(被标记为删除)
        if(result.getIsDelete()==1){
            throw new UserNotFoundException("用户不存在");
        }
        // 将当前用户数据返回
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setPASSWORD(result.getPASSWORD());
        user.setAvatar(result.getAvatar());
        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result = userMapper.findByUid(uid);
        if(result==null||result.getIsDelete()==1){
            throw new UserNotFoundException("用户不存在");
        }
        String oldMd5Password=getMD5Password(oldPassword,result.getSalt());
        if(!oldMd5Password.equals(result.getPASSWORD())){
            throw new PasswordNotMatchException("密码错误");
        }
        // 新的密码加密后在设置到数据库中
        String newMd5Password=getMD5Password(newPassword,result.getSalt());
        Integer rows=userMapper.updatePasswordByUid(uid,newMd5Password,username,new Date());
        if(rows!=1){
            throw new UpdateException("更新密码时发生未知异常");
        }
    }

    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result=userMapper.findByUid(uid);
        if(result==null||result.getIsDelete()==1){
            throw new UserNotFoundException("用户不存在");
        }
        user.setUid(uid);
        // user.setUsername(username);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        Integer rows=userMapper.updateInfoByUid(user);
        if(rows!=1){
            throw new UpdateException("更新数据时产生未知异常");
        }
    }

    @Override
    public User getByUid(Integer uid) {
        User result=userMapper.findByUid(uid);
        if(result==null||result.getIsDelete()==1){
            throw new UserNotFoundException("用户不存在");
        }
        User user=new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        return user;
    }

    @Override
    public void changeAvatar(Integer uid, String avatar, String username) {
        User result=userMapper.findByUid(uid);
        if(result==null||result.getIsDelete()==1){
            throw new UserNotFoundException("用户不存在");
        }
        Integer rows=userMapper.updateAvatarByUid(uid,avatar,username,new Date());
        if(rows!=1){
            throw new UpdateException("更新用户头像时发生未知异常");
        }
    }

    // 定义md5算法加密
    private String getMD5Password(String password,String salt){
        // md5加密算法的调用(进行3次加密)
        for(int i=0;i<3;i++){
            password=DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        //返回加密后的密码
        return password;
    }
}
