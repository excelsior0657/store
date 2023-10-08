package com.ss.store.controller;

import com.ss.store.entity.JsonResult;
import com.ss.store.entity.Product;
import com.ss.store.service.IProductService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController extends BaseController{
    @Resource
    private IProductService productService;

    @RequestMapping("hot_list")
    public JsonResult<List<Product>> getHotList(){
        List<Product> list=productService.findHotList();
        return new JsonResult<List<Product>>(OK,list);
    }

    @GetMapping("{id}/details")
    public JsonResult<Product> getById(@PathVariable("id") Integer id){
        Product product=productService.findById(id);
        return new JsonResult<>(OK,product);
    }
}
