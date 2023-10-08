package com.ss.store.mapper;

import com.ss.store.entity.Product;

import java.util.List;

public interface ProductMapper {
    List<Product> findHotList();
    Product findById(Integer id);
}
