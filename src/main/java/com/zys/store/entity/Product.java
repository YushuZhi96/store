package com.zys.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 商品数据的实体类 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {
    private Integer id;
    private Integer categoryId;
    private String itemType;
    private String title;
    private String sellPoint;
    private Long price;
    private Integer num;
    private String image;
    private Integer status;
    private Integer priority;
    /**
     * get,set
     * equals和hashCode
     * toString
     */
}

