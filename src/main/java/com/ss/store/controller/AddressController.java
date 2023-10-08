package com.ss.store.controller;

import com.ss.store.entity.Address;
import com.ss.store.entity.JsonResult;
import com.ss.store.service.IAddressService;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RequestMapping("addresses")
@RestController
public class AddressController extends BaseController{
    @Resource
    private IAddressService addressService;

    @RequestMapping("add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session){
        Integer uid=getuidFromSession(session);
        String username=getUsernameFromSession(session);
        addressService.addNewAddress(uid,username,address);
        return new JsonResult<>(OK);
    }

    @RequestMapping({"","/"})
    public JsonResult<List<Address>> getAddressByUid(HttpSession session){
        Integer uid=getuidFromSession(session);
        List<Address> data=addressService.getByUid(uid);
        return new JsonResult<>(OK,data);
    }

    // RestFul风格的请求编写
    @RequestMapping("{aid}/set_default")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid, HttpSession session) throws AccessDeniedException {
        addressService.setDefault(aid,getuidFromSession(session),getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }

    @RequestMapping("{aid}/delete")
    public JsonResult<Void> delete(@PathVariable("aid") Integer aid,HttpSession session) throws AccessDeniedException {
        addressService.delete(aid,getuidFromSession(session),getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }
}
