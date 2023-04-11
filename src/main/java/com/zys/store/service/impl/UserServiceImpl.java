package com.zys.store.service.impl;

import com.zys.store.entity.User;
import com.zys.store.mapper.UserMapper;
import com.zys.store.service.UserService;
import com.zys.store.service.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public void register(User user) {
        String username = user.getUsername();
        User result = userMapper.findByUsername(username);
        if(result!=null){
            throw new UsernameDuplicateException("Username is occupied");
        }
        /**
         * 密码加密
         */
        String oldPassword = user.getPassword();
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = getMD5Password(oldPassword, salt);
        user.setSalt(salt);
        user.setPassword(md5Password);

        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        Integer row = userMapper.insert(user);
        if(row != 1){
            throw new InsertException("Exception happened in registering");
        }

    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if(user == null){
            throw  new UsernameNotFoundException("User is not found!");
        }
        String oldPassword = user.getPassword();
        String salt = user.getSalt();
        String newPassword = getMD5Password(password, salt);
        if(!newPassword.equals(oldPassword)){
            throw new PasswordNotMatchException("Password is not correct");
        }

        if(user.getIsDelete() == 1){
            throw new UsernameNotFoundException("User is not found!");
        }

        User user1 = new User();
        user1.setUid(user.getUid());
        user1.setUsername(user.getUsername());
        user1.setAvatar(user.getAvatar());

        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result = userMapper.findByUid(uid);
        if(result == null && result.getIsDelete() == 1){
            throw new UsernameNotFoundException("User is not exists!");
        }
        //Compare the password
        String oldMd5Password = getMD5Password(oldPassword, result.getSalt());
        if(!result.getPassword().equals(oldMd5Password)){
            throw new PasswordNotMatchException("Password incorrect!");
        }
        //
        String newMd5Password = getMD5Password(newPassword, result.getSalt());
        Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password, username, new Date());
        if(rows != 1){
            throw new UpdateException("Unknown error!");
        }
    }

    @Override
    public void changeInfo(Integer uid, User user) {
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete() == 1){
            throw new UsernameNotFoundException("用户数据不存在");
        }
        user.setUid(uid);
        user.setModifiedTime(new Date());
        user.setModifiedUser(user.getUsername());

        Integer rows = userMapper.updateInfoByUid(user);
        if(rows != 1){
            throw new UpdateException("更新数据时产生异常");
        }
    }

    @Override
    public User getByUid(Integer uid) {
        //Query user
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete() == 1){
            throw new UsernameNotFoundException("User not found!");
        }
        User user = new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        return user;

    }
    @Override
    public void changeAvatar(Integer uid, String avatar, String username) {
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete() == 1){
            throw new UsernameNotFoundException("用户数据不存在");
        }
        Integer rows = userMapper.updateAvatarByUid(uid, avatar, username, new Date());
        if(rows != 1){
            throw new UpdateException("更新用户头像时产生未知异常");
        }

    }

    public String getMD5Password(String password,String salt){
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }

        return password;
    }
}
