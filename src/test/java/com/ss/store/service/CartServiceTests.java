package com.ss.store.service;

import com.ss.store.entity.CartVo;
import com.ss.store.service.ex.ServiceException;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceTests {
    @Resource
    private ICartService cartService;

    @Test
    public void addToCart(){
        try{
            Integer uid=6;
            Integer pid=10000007;
            Integer amount=1;
            String username="管理员";
            cartService.addToCart(uid,pid,amount,username);
            System.out.println("OK.");
        }
        catch (ServiceException e){
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getVoByUid(){
        List<CartVo> list=cartService.getVoByUid(11);
        System.out.println("count="+list.size());
        for(CartVo x:list){
            System.out.println(x);
        }
    }

    @Test
    public void addNum(){
        try{
            Integer cid=3;
            Integer uid=11;
            String username="树树";
            Integer num=cartService.addNum(cid,uid,username);
            System.out.println("num="+num);
        } catch (AccessDeniedException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getVoByCids(){
        Integer[] cids={1,2,3,4};
        Integer uid=11;
        List<CartVo> list=cartService.getVoByCids(uid,cids);
        System.out.println("count="+list.size());
        for(CartVo x:list){
            System.out.println(x);
        }
    }
}
