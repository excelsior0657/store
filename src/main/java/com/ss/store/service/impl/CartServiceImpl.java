package com.ss.store.service.impl;

import com.ss.store.entity.Cart;
import com.ss.store.entity.CartVo;
import com.ss.store.entity.Product;
import com.ss.store.mapper.CartMapper;
import com.ss.store.mapper.ProductMapper;
import com.ss.store.service.ICartService;
import com.ss.store.service.IProductService;
import com.ss.store.service.ex.CartNotFoundException;
import com.ss.store.service.ex.InsertException;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {
    @Resource
    private CartMapper cartMapper;

    @Resource
    private ProductMapper productMapper;

    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount, String username) {
        Cart res=cartMapper.findByUidAndPid(uid,pid);

        Date now=new Date();
        // 判断购物车是否有该商品，有则修改商品数量，没有则添加到购物车
        if(res==null){
            Cart cart=new Cart();
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            Product product=productMapper.findById(pid);
            cart.setPrice(product.getPrice());
            cart.setCreatedUser(username);
            cart.setCreateTime(now);
            cart.setModifiedUser(username);
            cart.setModifiedTime(now);
            Integer rows=cartMapper.insert(cart);
            if(rows!=1){
                throw new InsertException("添加购物车时发生未知异常");
            }
        }else{
            Integer cid=res.getCid();
            Integer num=res.getNum()+amount;
            Integer rows=cartMapper.updateNumByCid(cid,num,username,now);
            if(rows!=1){
                throw new InsertException("添加购物车时发生未知异常");
            }
        }
    }

    @Override
    public List<CartVo> getVoByUid(Integer uid) {
        return cartMapper.findVoByUid(uid);
    }

    @Override
    public Integer addNum(Integer cid, Integer uid, String username) throws AccessDeniedException {
        Cart res = cartMapper.findByCid(cid);
        if(res==null){
            throw new CartNotFoundException("购物车数据不存在");
        }
        if(!res.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
        }
        Integer num=res.getNum()+1;
        Date now = new Date();
        Integer rows=cartMapper.updateNumByCid(cid,num,username,now);
        if(rows!=1){
            throw new InsertException("修改商品数量时产生未知异常");
        }
        return num;
    }

    @Override
    public Integer subNum(Integer cid, Integer uid, String username) throws AccessDeniedException {
        Cart res = cartMapper.findByCid(cid);
        if(res==null){
            throw new CartNotFoundException("购物车数据不存在");
        }
        if(!res.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
        }
        Integer num=res.getNum()-1;
        Date now = new Date();
        Integer rows=cartMapper.updateNumByCid(cid,num,username,now);
        if(rows!=1){
            throw new InsertException("修改商品数量时产生未知异常");
        }
        return num;
    }

    @Override
    public List<CartVo> getVoByCids(Integer uid, Integer[] cids) {
        List<CartVo> list=cartMapper.findVoByCids(cids);
        Iterator<CartVo> it=list.iterator();
        while(it.hasNext()){
            CartVo cart=it.next();
            if(!cart.getUid().equals(uid)){
                list.remove(cart);
            }
        }
        return list;
    }


}
