package com.zys.store.service;

import com.zys.store.entity.Order;

public interface OrderService {
    Order create(Integer aid, Integer[] cids, Integer uid, String username);
}
