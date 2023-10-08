package com.ss.store.mapper;

import com.ss.store.entity.Address;
import com.ss.store.entity.User;
import com.ss.store.service.IAddressService;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

// @SpringBootTest: 表示当前类是一个测试类，不会随同项目一块打包
@SpringBootTest
// @RunWith: 表示启动这个单元测试类(不写，单元测试类无法运行),需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class AddressMapperTests {

    @Resource
    private AddressMapper addressMapper;

    @Test
    public void insert(){
        Address address=new Address();
        address.setUid(1);
        address.setName("树树");
        address.setPhone("17338676087");
        address.setAddress("江苏无锡JNAIC");
        addressMapper.insert(address);
    }

    @Test
    public void countByUid(){
        Integer count=addressMapper.countByUid(11);
        System.out.println(count);
    }

    @Test
    public void findByUid(){
        List<Address> addresses = addressMapper.findByUid(11);
        for(Address x:addresses){
            System.out.println(x);
        }
    }

    @Test
    public void findByAid(){
        System.out.println(addressMapper.findByAid(7));
    }
    @Test
    public void updateNonDefault(){
        addressMapper.updateNonDefault(11);
    }
    @Test
    public void updateDefaultByAid(){
        addressMapper.updateDefaultByAid(7,"凑井盖",new Date());
    }

    @Test
    public void deleteByAid(){
        addressMapper.deleteByAid(2);
    }

    @Test
    public void findLastModified(){
        System.out.println(addressMapper.findLastModified(11));
    }


}
