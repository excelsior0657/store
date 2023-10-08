package com.ss.store.service.impl;

import com.ss.store.entity.District;
import com.ss.store.mapper.DistrictMapper;
import com.ss.store.service.IDistrictService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements IDistrictService {

    @Resource
    private DistrictMapper districtMapper;
    @Override
    public List<District> getByParent(String parent) {
        List<District> districts = districtMapper.findByParent(parent);
        for(District x:districts){
            x.setParent(null);
            x.setId(null);
        }
        return districts;
    }

    @Override
    public String getNameByCode(String code) {
        String name=districtMapper.findNameByCode(code);
        return name;
    }
}
