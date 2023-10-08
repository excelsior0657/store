package com.ss.store.mapper;

import com.ss.store.entity.District;

import java.util.List;

public interface DistrictMapper {
    List<District> findByParent(String parent);
    String findNameByCode(String code);
}
