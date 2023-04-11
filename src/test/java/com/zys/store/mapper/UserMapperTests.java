package com.zys.store.mapper;


import com.zys.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {

    @Autowired
    private UserMapper userMapper;
    @Test
    public void insert(){
        User user = new User();
        user.setUsername("yushuzhi2");
        user.setPassword("yushuzhi96");
        //返回插入操作影响的行数
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }
    @Test
    public void findByUsername(){
        User user = userMapper.findByUsername("yushuzhi2");
        System.out.println(user);
    }

    @Test
    public void updateUserTest(){
        userMapper.updatePasswordByUid(6,"1234","zys",new Date());
    }

    @Test
    public void updateInfoByUidTest(){
        User user = new User();
        user.setUid(9);
        user.setPhone("1231231");
        user.setEmail("test@gmail.com");
        user.setGender(1);


        userMapper.updateInfoByUid(user);
    }

    @Test
    public void updateAvatarTest(){
        userMapper.updateAvatarByUid(6,"abc","zys",new Date());

    }
}
