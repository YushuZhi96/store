package com.zys.store.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zys.store.entity.Favorites;
import com.zys.store.entity.Product;
import com.zys.store.mapper.FavoritesMapper;
import com.zys.store.service.FavoritesService;
import com.zys.store.service.ProductService;
import com.zys.store.service.exception.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritesServiceImpl implements FavoritesService {
    @Autowired
    private FavoritesMapper favoritesMapper;
    @Autowired
    private ProductService productService;

    //根据uid和商品收藏状态查询收藏数据的具体逻辑
    @Override
    public PageInfo<Favorites> queryFavorites(Integer uid, Integer pageNum, Integer pageSize, Integer status) {
        //开启分页功能，pageNum是当前页，pageSize是每页显示的数据量，这两个值都可以选择让前端传或者自己调整
        PageHelper.startPage(pageNum, pageSize);
        List<Favorites> favorites = favoritesMapper.queryFavoritesByUidAndStatus(uid, status);
        PageInfo<Favorites> pageInfo = new PageInfo<>(favorites);
        return pageInfo;
    }

    @Override
    public int addFavorites(Integer uid, Integer pid) {
        Favorites favorites = new Favorites();
        //根据pid查询商品信息
        Product product = productService.findById(pid);
        //填充favorites对象空白字段
        favorites.setUid(uid);
        favorites.setPid(pid);
        favorites.setImage(product.getImage());
        favorites.setPrice(product.getPrice());
        favorites.setTitle(product.getTitle());
        favorites.setSellPoint(product.getSellPoint());
        favorites.setStatus(1);
        int result = favoritesMapper.addFavorites(favorites);
        if (result == 0){
            throw new InsertException("服务器异常，收藏商品失败");
        }
        //取出fid返回给前端页面，以便在搜索界面取消收藏使用
        return favorites.getFid();
    }
}