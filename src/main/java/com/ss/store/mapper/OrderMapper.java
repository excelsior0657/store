package com.ss.store.mapper;

import com.ss.store.entity.Order;
import com.ss.store.entity.OrderItem;

public interface OrderMapper {
    Integer insertOrder(Order order);
    Integer insertOrderItem(OrderItem orderItem);
}
