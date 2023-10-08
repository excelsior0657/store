package com.ss.store.controller;

import com.ss.store.controller.ex.*;
import com.ss.store.entity.JsonResult;
import com.ss.store.entity.User;
import com.ss.store.service.IUserService;
import com.ss.store.service.ex.InsertException;
import com.ss.store.service.ex.UsernameDuplicatedException;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// @Controller
@RestController // = @Controller + @ResponseBody
@RequestMapping("users")
public class UserController extends BaseController{
    @Resource
    private IUserService userService;
    @RequestMapping("reg")
    // @ResponseBody // 表示此方法的响应结果以json格式返回响应给前端
    public JsonResult<Void> reg(User user){
        userService.reg(user);
        return new JsonResult<>(OK);
    }
    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        User data = userService.login(username,password);
        // 服务器自身会自动创建有session对象，已经是一个全局的session对象。
        // SpringBoot直接使用session对象，直接将HttpSession类型的对象作为请求处理的参数，
        // 会自动将全局的session对象注入到请求处理方法的形参上。
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());

        // 获取session中绑定的数据
        System.out.println(getuidFromSession(session));
        System.out.println(getUsernameFromSession(session));
        return new JsonResult<User>(OK, data);
    }

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword,String newPassword,HttpSession session){
        Integer uid=getuidFromSession(session);
        String username=getUsernameFromSession(session);
        userService.changePassword(uid,username,oldPassword,newPassword);
        return new JsonResult<>(OK);
    }

    @RequestMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session){
        User data=userService.getByUid(getuidFromSession(session));
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user,HttpSession session){
        Integer uid=getuidFromSession(session);
        String username=getUsernameFromSession(session);
        userService.changeInfo(uid,username,user);
        return new JsonResult<>(OK);
    }


    // 设置上传文件的最大值
    public static final int AVATAR_MAX_SIZE = 10*1024*1024;
    // 设置上传文件的类型
    public static final List<String> AVATAR_TYPE=new ArrayList<>();
    static {
        AVATAR_TYPE.add("image/jpeg"); // 包含了jpg
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }
    /**
     * MultipartFile是SpringMVC提供的接口，
     * 可以接受任何类型的文件
     *
     * @RequestParam 将请求中的参数注入到请求处理方法的某个参数上
     * 名称不一致可使用，一致可忽略
     */
    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session,@RequestParam("file") MultipartFile file){
        if(file.isEmpty()){
            throw new FileEmptyException("文件为空");
        }
        if(file.getSize()>AVATAR_MAX_SIZE){
            throw new FileSizeException("文件大小超出限制");
        }
        String contentType= file.getContentType();
        if(!AVATAR_TYPE.contains(contentType)){
            throw new FileTypeException("文件类型不支持");
        }
        // 上传的文件.../upload/文件.png
        String parent=session.getServletContext().getRealPath("upload");
        // File对象指向这个路径, File是否存在
        File dir=new File(parent);
        // 父路径不存在则创建当前目录
        if(!dir.exists()){
            dir.mkdirs();
        }
        // 获取这个文件名称，UUID工具生成一个新的字符串作为文件名
        // 例如avatar01.png -> AHX-XV752XS-DG35-FAD03.png
        String orgFilename= file.getOriginalFilename();
        int index=orgFilename.lastIndexOf(".");
        // 记录后缀
        String suffix=orgFilename.substring(index);
        String filename= UUID.randomUUID().toString().toUpperCase()+suffix;
        // 创建一个新的空文件
        File dest=new File(dir,filename);
        // 将参数file中的数据写入到dest这个空文件中
        try {
            file.transferTo(dest);
        } catch (FileStateException e){
            throw new FileStateException("文件状态异常");
        } catch (IOException e) {
            throw new FileUploadIOException("文件读写异常");
        }

        Integer uid=getuidFromSession(session);
        String username=getUsernameFromSession(session);
        // 返回头像的路径/upload/test.png
        String avatar = "/upload/"+filename;
        userService.changeAvatar(uid,avatar,username);
        // 返回用户头像的路径给前端页面，用于头像展示使用
        System.out.println(parent);
        return new JsonResult<>(OK,avatar);
    }
}
