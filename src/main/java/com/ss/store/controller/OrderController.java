package com.ss.store.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.ss.store.entity.JsonResult;
import com.ss.store.entity.Order;
import com.ss.store.service.IOrderService;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("orders")
public class OrderController extends BaseController {
    @Resource
    private IOrderService orderService;

    @RequestMapping("create")
    public JsonResult<Order> create(Integer aid, Integer[] cids, HttpSession session) throws AccessDeniedException {
        Integer uid=getuidFromSession(session);
        String username=getUsernameFromSession(session);
        Order data=orderService.create(aid,cids,uid,username);
        return new JsonResult<>(OK,data);
    }
}
