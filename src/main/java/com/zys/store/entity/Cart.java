package com.zys.store.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**购物车数据的实体类*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart extends BaseEntity {
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;
/**
 * get,set
 * equals和hashCode
 * toString
 */
}
