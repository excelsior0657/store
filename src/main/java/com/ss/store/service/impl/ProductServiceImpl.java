package com.ss.store.service.impl;

import com.ss.store.entity.Product;
import com.ss.store.mapper.ProductMapper;
import com.ss.store.service.IProductService;
import com.ss.store.service.ex.ProductNotFoundException;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Resource
    private ProductMapper productMapper;

    @Override
    public List<Product> findHotList() {
        List<Product> list=productMapper.findHotList();
        for(Product x:list){
            x.setPriority(null);
            x.setCreatedUser(null);
            x.setCreateTime(null);
            x.setModifiedUser(null);
            x.setModifiedTime(null);
        }
        return list;
    }

    @Override
    public Product findById(Integer id) {
        Product product=productMapper.findById(id);
        if(product==null){
            throw new ProductNotFoundException("商品未找到");
        }

        product.setPriority(null);
        product.setCreatedUser(null);
        product.setCreateTime(null);
        product.setModifiedUser(null);
        product.setModifiedTime(null);
        return product;
    }
}
