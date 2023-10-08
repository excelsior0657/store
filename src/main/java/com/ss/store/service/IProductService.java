package com.ss.store.service;

import com.ss.store.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> findHotList();
    Product  findById(Integer id);
}
