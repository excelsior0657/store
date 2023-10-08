package com.ss.store.mapper;

import com.ss.store.entity.Address;
import com.ss.store.entity.Cart;
import com.ss.store.entity.CartVo;
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
public class CartMapperTests {

    @Resource
    private CartMapper cartMapper;

    @Test
    public void insert(){
        Cart cart=new Cart();
        cart.setUid(11);
        cart.setPid(10000032);
        cart.setPrice(666L);
        cart.setNum(1);
        cartMapper.insert(cart);
    }

    @Test
    public void updateNumByCid(){
        cartMapper.updateNumByCid(1,3,"树树",new Date());
    }

    @Test
    public void findByUidAndPid(){
        Cart cart=cartMapper.findByUidAndPid(11,10000032);
        System.out.println(cart);
    }

    @Test
    public void findVoByUid(){
        List<CartVo> list=cartMapper.findVoByUid(11);
        for(CartVo x:list){
            System.out.println(x);
        }
    }

    @Test
    public void findByCid(){
        Integer cid=4;
        Cart res= cartMapper.findByCid(cid);
        System.out.println(res);
    }

    @Test
    public void findVoByCids(){
        Integer[] cids={1,3,4};
        List<CartVo> list=cartMapper.findVoByCids(cids);
        System.out.println("count="+list.size());
        for(CartVo c:list) {
            System.out.println(c);
        }
    }


}
