package com.zys.store.service;

import com.zys.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictServiceTests {
    @Autowired
    private DistrictService districtService;

    @Test
    public void getByParent() {
        //86代表中国,所有的省父代码号都是86
        List<District> list = districtService.getByParent("86");
        for (District district : list) {
            System.err.println(district);
        }
    }
}

