package com.ss.store.mapper;

import com.ss.store.entity.User;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

// @SpringBootTest: 表示当前类是一个测试类，不会随同项目一块打包
@SpringBootTest
// @RunWith: 表示启动这个单元测试类(不写，单元测试类无法运行),需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class UserMapperTests {

    @Resource
    private UserMapper userMapper;
    /*
    * 单元测试方法:
    * 1.必须被@Test修饰
    * 2.返回值类型必须是void
    * 3.方法的参数列表不指定任何类型
    * 4.方法的访问修饰符必须为public
    * */
    @Test
    public void insert(){
        User user=new User();
        user.setUsername("Excelsior");
        user.setPASSWORD("1234");
        Integer rows=userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void findByUsername(){
        User user=userMapper.findByUsername("Excelsior");
        System.out.println(user);
    }

    @Test
    public void updatePasswordByUid(){
        userMapper.updatePasswordByUid(10,"123","树树",new Date());
    }

    @Test
    public void findByUid(){
        System.out.println(userMapper.findByUid(10));
    }

    @Test
    public void updateInfoByUid(){
        User user=new User();
        user.setUid(11);
        user.setPhone("110");
        user.setEmail("jinggai@qq.com");
        user.setGender(0);
        userMapper.updateInfoByUid(user);
    }

    @Test
    public void updateAvatarByUid(){
        userMapper.updateAvatarByUid(11,"E:/pic/jinggai/1.jpg","树树",new Date());
    }

}
