package com.ss.store.service;

import com.ss.store.entity.User;
import com.ss.store.mapper.UserMapper;
import com.ss.store.service.ex.ServiceException;
import com.ss.store.service.impl.UserServiceImpl;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {

    @Resource
    private UserServiceImpl userService;

   @Test
    public void reg(){
       try {
           User user=new User();
           user.setUsername("sss");
           user.setPASSWORD("123456");
           userService.reg(user);
           System.out.println("OK");
       } catch (ServiceException e) {
           e.printStackTrace();
           // 获取异常的对象，在获取异常的名称
           System.out.println(e.getClass().getSimpleName());
           // 获取异常的描述信息
           System.out.println(e.getMessage());
       }
   }

   @Test
    public void login(){
       User user= userService.login("test","999");
       System.out.println(user);
   }
    @Test
    public void changePassword() {
        userService.changePassword(11,"井盖","1234","4321");
    }

    @Test
    public void getByUid(){
       User user=userService.getByUid(11);
        System.out.println(user);
    }

    @Test
    public void changeInfo(){
       User user=new User();
       user.setPhone("1818");
       user.setEmail("210408790@qq.com");
       user.setGender(0);
       userService.changeInfo(11,"Excelsior",user);
    }

    @Test
    public void changeAvatar(){
       userService.changeAvatar(11,"E:/pic/jinggai/6.jpg","树树");
    }
}
