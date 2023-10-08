package com.ss.store.service;

import com.ss.store.entity.District;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictServiceTest {
    @Resource
    private IDistrictService districtService;

    @Test
    public void getByParent(){
        List<District> districts = districtService.getByParent("86");
        for(District x:districts){
            System.out.println(x);
        }
    }
}
