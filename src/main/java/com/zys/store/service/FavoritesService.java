package com.zys.store.service;

import com.github.pagehelper.PageInfo;
import com.zys.store.entity.Favorites;

public interface FavoritesService {
    public PageInfo<Favorites> queryFavorites(Integer uid, Integer pageNum, Integer pageSize, Integer status);

    int addFavorites(Integer uid,Integer pid);
}
