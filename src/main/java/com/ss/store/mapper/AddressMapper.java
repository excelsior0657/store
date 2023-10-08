package com.ss.store.mapper;

import com.ss.store.entity.Address;

import java.util.Date;
import java.util.List;

public interface AddressMapper {
    Integer insert(Address address);
    Integer countByUid(Integer uid);
    List<Address> findByUid(Integer uid);


    // 判断用户地址是否存在
    Address findByAid(Integer aid);
    Integer updateNonDefault(Integer uid);
    Integer updateDefaultByAid(Integer aid, String modifiedUser, Date modifiedTime);

    // 删除收货地址
    Integer deleteByAid(Integer aid);

    Address findLastModified(Integer uid);
}
