package com.ss.store.controller;

import com.ss.store.controller.ex.*;
import com.ss.store.entity.JsonResult;
import com.ss.store.service.ex.*;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {
    // 注册成功的状态码
    public static final int OK=200;

    // 请求处理方法, 这个方法的返回值就是需要传递给前端的数据
    // 自动将异常对象传递给此方法的参数列表
    // 当项目产生异常时，统一拦截到此方法中，这个方法此时充当的就是请求处理方法，方法的返回值直接给到前端
    @ExceptionHandler({ServiceException.class,FileUploadException.class})
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result=new JsonResult<>(e);
        if(e instanceof UsernameDuplicatedException){
            result.setState(4000);
            result.setMessage("用户名已被占用");
        }else if(e instanceof UserNotFoundException){
            result.setState(4001);
            result.setMessage("用户名不存在");
        }else if(e instanceof PasswordNotMatchException){
            result.setState(4002);
            result.setMessage("密码错误");
        }else if(e instanceof AddressCountLimitException){
            result.setState(4003);
            result.setMessage("用户收货地址超出上限");
        }else if(e instanceof AddressNotFoundException){
            result.setState(4004);
            result.setMessage("用户收货地址数据不存在");
        }else if(e instanceof AccessDeniedException){
            result.setState(4005);
            result.setMessage("收货地址数据非法访问");
        }else if(e instanceof ProductNotFoundException){
            result.setState(4006);
            result.setMessage("商品未找到");
        }else if(e instanceof CartNotFoundException){
            result.setState(4007);
            result.setMessage("购物车不存在");
        }else if(e instanceof InsertException){
            result.setState(5000);
            result.setMessage("注册时产生未知异常");
        }else if(e instanceof UpdateException){
            result.setState(5001);
            result.setMessage("更新数据时产生未知异常");
        }else if(e instanceof DeleteException){
            result.setState(5002);
            result.setMessage("删除数据时产生未知异常");
        }else if (e instanceof FileEmptyException) {
            result.setState(6000);
            result.setMessage("文件为空");
        } else if (e instanceof FileSizeException) {
            result.setState(6001);
            result.setMessage("文件大小超出限制");
        } else if (e instanceof FileTypeException) {
            result.setState(6002);
            result.setMessage("文件类型不支持");
        } else if (e instanceof FileStateException) {
            result.setState(6003);
            result.setMessage("文件状态错误");
        } else if (e instanceof FileUploadIOException) {
            result.setState(6004);
            result.setMessage("文件上传时发生未知错误");
        }
        return result;
    }
    // 获取当前登录用户的uid
    protected final Integer getuidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }
    // 获取当前登录用户的用户名
    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }

}
