package com.ss.store.service;

import com.ss.store.entity.District;

import java.util.List;

public interface IDistrictService {
    List<District> getByParent(String parent);
    String getNameByCode(String code);
}
