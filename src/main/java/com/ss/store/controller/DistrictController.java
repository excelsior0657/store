package com.ss.store.controller;

import com.ss.store.entity.BaseEntity;
import com.ss.store.entity.District;
import com.ss.store.entity.JsonResult;
import com.ss.store.service.IDistrictService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("districts")
@RestController
public class DistrictController extends BaseController {
    @Resource
    private IDistrictService districtService;

    @RequestMapping({"/",""})
    public JsonResult<List<District>> getByParent(String parent){
        List<District> districts = districtService.getByParent(parent);
        return new JsonResult<>(OK,districts);
    }
}
