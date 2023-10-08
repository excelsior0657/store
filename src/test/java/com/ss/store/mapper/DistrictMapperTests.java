package com.ss.store.mapper;

import com.ss.store.entity.Address;
import com.ss.store.entity.District;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

// @SpringBootTest: 表示当前类是一个测试类，不会随同项目一块打包
@SpringBootTest
// @RunWith: 表示启动这个单元测试类(不写，单元测试类无法运行),需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class DistrictMapperTests {

    @Resource
    private DistrictMapper districtMapper;

    @Test
    public void findByParent(){
        List<District> districts = districtMapper.findByParent("110100");
        for(District x:districts){
            System.out.println(x);
        }
    }

    @Test
    public void findNameByCode(){
        String name=districtMapper.findNameByCode("610000");
        System.out.println(name);
    }
}
