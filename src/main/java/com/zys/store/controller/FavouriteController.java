package com.zys.store.controller;

import com.github.pagehelper.PageInfo;
import com.zys.store.entity.Favorites;
import com.zys.store.service.FavoritesService;
import com.zys.store.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/favorites")
public class FavouriteController extends BaseController{
    @Autowired
    private FavoritesService favoritesService;
    //处理查询收藏商品的请求
    @GetMapping("/queryFavorites")
    public JsonResult<PageInfo<Favorites>> queryFavorites(HttpSession session,Integer pageNum,Integer pageSize,Integer status){
        Integer uid =getUidFromSession(session);
        PageInfo<Favorites> favorites = favoritesService.queryFavorites(uid, pageNum,pageSize,status);
        return new JsonResult<>(ok,favorites);
    }

    @PostMapping("/addFavorites")
    public JsonResult<Integer> addFavorites(HttpSession session,Integer pid){
        //从session中取出uid
        Integer uid = getUidFromSession(session);
        //执行插入操作并返回fid
        int fid = favoritesService.addFavorites(uid, pid);
        return new JsonResult<>(ok,fid);
    }
}
