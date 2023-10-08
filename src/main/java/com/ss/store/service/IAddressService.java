package com.ss.store.service;

import com.ss.store.entity.Address;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface IAddressService {
    void addNewAddress(Integer uid, String username, Address address);
    List<Address> getByUid(Integer uid);
    void setDefault(Integer aid,Integer uid,String username) throws AccessDeniedException;
    void delete(Integer aid,Integer uid,String username) throws AccessDeniedException;
    Address getByAid(Integer aid,Integer uid) throws AccessDeniedException;
}
