package com.zys.store.service;

import com.zys.store.entity.User;
import com.zys.store.service.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void registerTest(){
        try {
            User user = new User();
            user.setUsername("test02");
            user.setPassword("123456");
            userService.register(user);
            System.out.println("OK");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void loginTest(){
        User test02 = userService.login("test01", "123");
        System.out.println(test02);
    }
    @Test
    public void changePassword(){
        userService.changePassword(8,"test123","123","321");
    }

    @Test
    public void getByUid(){
        System.out.println(userService.getByUid(6).getUsername());
    }

    @Test
    public void changeInfo(){
        User user = new User();
        user.setPhone("1233123");
        user.setEmail("test@gmail.com");
        user.setGender(0);
        userService.changeInfo(9,user);
    }

    @Test
    public void changeAvatar() {
        userService.changeAvatar(6,"222","mmm");
    }




}
