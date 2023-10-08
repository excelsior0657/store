package com.ss.store.mapper;

import com.ss.store.entity.Cart;
import com.ss.store.entity.CartVo;

import java.util.Date;
import java.util.List;

public interface CartMapper {
    Integer insert(Cart cart);
    Integer updateNumByCid(Integer cid, Integer num, String modifiedUser, Date modifiedTime);
    Cart findByUidAndPid(Integer uid,Integer pid);
    List<CartVo> findVoByUid(Integer uid);
    Cart findByCid(Integer cid);
    List<CartVo> findVoByCids(Integer[] cids);

}
