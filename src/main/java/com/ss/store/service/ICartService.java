package com.ss.store.service;

import com.ss.store.entity.Cart;
import com.ss.store.entity.CartVo;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface ICartService {
    void addToCart(Integer uid,Integer pid,Integer amount,String username);
    List<CartVo> getVoByUid(Integer uid);
    Integer addNum(Integer cid,Integer uid,String username) throws AccessDeniedException;
    Integer subNum(Integer cid,Integer uid,String username) throws AccessDeniedException;
    List<CartVo> getVoByCids(Integer uid,Integer[] cids);
}
