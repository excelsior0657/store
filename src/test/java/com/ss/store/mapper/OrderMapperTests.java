package com.ss.store.mapper;

import com.ss.store.entity.Address;
import com.ss.store.entity.Order;
import com.ss.store.entity.OrderItem;
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
public class OrderMapperTests {
    @Resource
    private OrderMapper orderMapper;

    @Test
    public void insertOrder(){
        Order order=new Order();
        order.setUid(11);
        order.setRecvName("凑肥鯮");
        Integer rows=orderMapper.insertOrder(order);
        System.out.println("rows="+rows);
    }

    @Test
    public void insertOrderItem(){
        OrderItem orderItem=new OrderItem();
        orderItem.setOid(1);
        orderItem.setPid(2);
        orderItem.setTitle("高档铅笔");
        Integer rows = orderMapper.insertOrderItem(orderItem);
        System.out.println("rows=" + rows);
    }
}
