package com.ss.store.service.impl;

import com.ss.store.entity.Address;
import com.ss.store.entity.CartVo;
import com.ss.store.entity.Order;
import com.ss.store.entity.OrderItem;
import com.ss.store.mapper.OrderMapper;
import com.ss.store.service.IAddressService;
import com.ss.store.service.ICartService;
import com.ss.store.service.IOrderService;
import com.ss.store.service.ex.InsertException;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private IAddressService addressService;
    @Resource
    private ICartService cartService;
    @Override
    public Order create(Integer aid, Integer[] cids, Integer uid, String username) throws AccessDeniedException {
        Date now=new Date();
        List<CartVo> carts=cartService.getVoByCids(uid,cids);

        long totalPrice=0L;
        for(CartVo x:carts){
            totalPrice+=x.getRealPrice()*x.getNum();
        }

        Order order=new Order();
        order.setUid(uid);
        Address address=addressService.getByAid(aid,uid);

        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());

        order.setTotalPrice(totalPrice);
        order.setStatus(0);
        order.setOrderTime(now);

        order.setCreatedUser(username);
        order.setCreateTime(now);
        order.setModifiedUser(username);
        order.setModifiedTime(now);

        Integer rows1=orderMapper.insertOrder(order);
        if(rows1!=1){
            throw new InsertException("插入订单数据时发生未知错误");
        }

        for (CartVo cart : carts) {
            // 创建订单商品数据
            OrderItem item = new OrderItem();
            // 补全数据：setOid(order.getOid())
            item.setOid(order.getOid());
            // 补全数据：pid, title, image, price, num
            item.setPid(cart.getPid());
            item.setTitle(cart.getTitle());
            item.setImage(cart.getImage());
            item.setPrice(cart.getRealPrice());
            item.setNum(cart.getNum());
            item.setCreatedUser(username);
            item.setCreateTime(now);
            item.setModifiedUser(username);
            item.setModifiedTime(now);
            // 插入订单商品数据
            Integer rows2 = orderMapper.insertOrderItem(item);
            if (rows2 != 1) {
                throw new InsertException("插入订单商品数据时出现未知错误，请联系系统管理员");
            }
        }
        return order;
    }
}
