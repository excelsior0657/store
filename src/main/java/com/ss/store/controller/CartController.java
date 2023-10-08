package com.ss.store.controller;

import com.ss.store.entity.CartVo;
import com.ss.store.entity.JsonResult;
import com.ss.store.service.ICartService;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("carts")
public class CartController extends BaseController{
    @Resource
    private ICartService cartService;

    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid, Integer amount, HttpSession session){
        Integer uid=getuidFromSession(session);
        String username = getUsernameFromSession(session);
        cartService.addToCart(uid,pid,amount,username);
        return new JsonResult<>(OK);
    }

    @RequestMapping({"","/"})
    public JsonResult<List<CartVo>> getVoByUid(HttpSession session){
        Integer uid=getuidFromSession(session);
        List<CartVo> data=cartService.getVoByUid(uid);
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("{cid}/num/add")
    public JsonResult<Integer> addNum(@PathVariable("cid") Integer cid, HttpSession session) throws AccessDeniedException {
        Integer uid=getuidFromSession(session);
        String username=getUsernameFromSession(session);
        Integer data=cartService.addNum(cid,uid,username);
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("{cid}/num/sub")
    public JsonResult<Integer> subNum(@PathVariable("cid") Integer cid, HttpSession session) throws AccessDeniedException {
        Integer uid=getuidFromSession(session);
        String username=getUsernameFromSession(session);
        Integer data=cartService.subNum(cid,uid,username);
        return new JsonResult<>(OK,data);
    }

    @RequestMapping ("list")
    public JsonResult<List<CartVo>> getVoByCids(Integer[] cids,HttpSession session){
        Integer uid=getuidFromSession(session);
        List<CartVo> data=cartService.getVoByCids(uid,cids);
        return new JsonResult<>(OK,data);
    }
}
