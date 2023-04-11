package com.zys.store.service.impl;

import com.zys.store.entity.District;
import com.zys.store.mapper.DistrictMapper;
import com.zys.store.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public List<District> getByParent(String parent) {
        List<District> list = districtMapper.findByParent(parent);
        /**
         * 在进行网络数据传输时,为了尽量避免无效数据的传递,可以将无效数据
         * 设置为null,这样既节省流量,又提升了效率
         */
        for (District district : list) {
            district.setId(null);
            district.setParent(null);
        }
        return list;
    }

    @Override
    public String getNameByCode(String code) {
        return districtMapper.findNameByCode(code);
    }

}

