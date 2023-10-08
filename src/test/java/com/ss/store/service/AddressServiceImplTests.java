package com.ss.store.service;

import com.ss.store.entity.Address;
import com.ss.store.service.impl.AddressServiceImpl;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.AccessDeniedException;


@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceImplTests {

    @Resource
    private AddressServiceImpl addressServiceImpl;

   @Test
    public void addNewAddress(){
       Address address=new Address();
       address.setUid(11);
       address.setName("肥鯮");
       address.setPhone("110");
       address.setAddress("中央公园");
       addressServiceImpl.addNewAddress(11,"管理员",address);
   }

   @Test
    public void setDefault() throws AccessDeniedException {
       addressServiceImpl.setDefault(4,11,"树树");
   }

    @Test
    public void delete() throws AccessDeniedException {
        addressServiceImpl.delete(5,11,"excelsior0657");
    }
}
