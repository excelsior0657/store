package com.ss.store.service;

import com.ss.store.entity.Address;
import com.ss.store.entity.Order;

import java.nio.file.AccessDeniedException;

public interface IOrderService {
    Order create(Integer aid, Integer[] cids, Integer uid, String username) throws AccessDeniedException;
}
