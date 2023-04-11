package com.zys.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
/**省市区的数据实体类*/
public class District implements Serializable {
    private Integer id;
    private String parent;
    private String code;
    private String name;
    /**
     * get,set
     * equals和hashCode
     * toString
     */
}

