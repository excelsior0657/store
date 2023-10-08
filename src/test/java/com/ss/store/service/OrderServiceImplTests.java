package com.ss.store.service;

import com.ss.store.entity.Address;
import com.ss.store.entity.Order;
import com.ss.store.service.ex.ServiceException;
import com.ss.store.service.impl.AddressServiceImpl;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.AccessDeniedException;


@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceImplTests {
    @Resource
    private IOrderService orderService;

    @Test
    public void create(){
        try{
            Integer aid=7;
            Integer[] cids={1,3,4};
            Integer uid=11;
            String username="凑井盖";
            Order order=orderService.create(aid,cids,uid,username);
            System.out.println(order);
        } catch (ServiceException | AccessDeniedException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
