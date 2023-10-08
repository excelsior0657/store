package com.ss.store.service.impl;

import com.ss.store.entity.Address;
import com.ss.store.mapper.AddressMapper;
import com.ss.store.service.IAddressService;
import com.ss.store.service.IDistrictService;
import com.ss.store.service.ex.*;
import javax.annotation.Resource;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {

    @Resource
    private AddressMapper addressMapper;
    // 在添加用户收货地址的业务层依赖于IDistrictService的业务层接口
    @Resource
    private IDistrictService districtService;

    @Value("${user.address.max-count}")
    private Integer maxCount;
    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        Integer count=addressMapper.countByUid(uid);

        if(count>=maxCount){
            throw new AddressCountLimitException("用户收货地址超出上限");
        }

        // 对address对象中的数据进行补全：省市区
        String provinceName = districtService.getNameByCode(address.getProvinceCode());
        String cityName = districtService.getNameByCode(address.getCityCode());
        String areaName = districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);

        // uid,isDefault
        address.setUid(uid);
        Integer isDefault=count==0?1:0;
        address.setIsDefault(isDefault);
        // 补全四项日志
        address.setCreatedUser(username);
        address.setCreateTime(new Date());
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());
        // 插入收货地址
        Integer rows=addressMapper.insert(address);
        if(rows!=1){
            throw new InsertException("插入用户地址时产生未知异常");
        }
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> addresses = addressMapper.findByUid(uid);
        List<Address> list=new ArrayList<>();
        for(Address x:addresses){
            Address address=new Address();
            address.setAid(x.getAid());
            address.setUid(x.getUid());
            address.setTag(x.getTag());
            address.setPhone(x.getPhone());
            address.setAddress(x.getAddress());
            address.setName(x.getName());
            address.setProvinceName(x.getProvinceName());
            address.setCityName(x.getCityName());
            address.setAreaName(x.getAreaName());
            address.setZip(x.getZip());
            list.add(address);
        }
        return list;
    }

    @Override
    public void setDefault(Integer aid, Integer uid, String username) throws AccessDeniedException {
        Address address = addressMapper.findByAid(aid);
        if(address==null){
            throw new AddressNotFoundException("收货地址不存在");
        }
        // 检测获取收货地址数据的归属
        if(!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }
        // 先将所有的收货地址设置为非默认
        Integer rows=addressMapper.updateNonDefault(uid);
        if(rows<1){
            throw new UpdateException("更新数据时产生未知异常");
        }
        // 设置默认地址
        rows = addressMapper.updateDefaultByAid(aid, username, new Date());
        if(rows!=1){
            throw new UpdateException("更新数据时产生未知异常");
        }
    }

    @Override
    public void delete(Integer aid, Integer uid, String username) throws AccessDeniedException {
        Address address=addressMapper.findByAid(aid);
        if(address==null){
            throw new AddressNotFoundException("用户地址未找到");
        }
        if(!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }
        Integer rows = addressMapper.deleteByAid(aid);
        if(rows!=1){
            throw new DeleteException("删除数据时产生未知异常");
        }
        Integer count=addressMapper.countByUid(uid);
        if(count==0){
            return;
        }
        if(address.getIsDefault()==0){
            return;
        }
        Address res = addressMapper.findLastModified(uid);
        rows=addressMapper.updateDefaultByAid(res.getAid(), username,new Date());
        if(rows!=1){
            throw new UpdateException("更新数据时产生未知异常");
        }
    }

    @Override
    public Address getByAid(Integer aid, Integer uid) throws AccessDeniedException {
        Address address=addressMapper.findByAid(aid);
        if(address==null){
            throw new AddressNotFoundException("收货地址未找到");
        }
        if(!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
        }
        address.setProvinceCode(null);
        address.setCityCode(null);
        address.setAreaCode(null);
        address.setCreatedUser(null);
        address.setCreateTime(null);
        address.setModifiedUser(null);
        address.setModifiedTime(null);
        return address;
    }


}
