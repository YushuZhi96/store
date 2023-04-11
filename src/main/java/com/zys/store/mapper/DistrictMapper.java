package com.zys.store.mapper;

import com.zys.store.entity.District;

import java.util.List;

public interface DistrictMapper {

    /**
     * 根据父代码号查询区域信息
     * @param parent 父代码号
     * @return 某个父区域下所有的区域列表
     */
    List<District> findByParent(String parent);//查询的结果可能是多个,所以放在集合中

    String findNameByCode(String code);

}
