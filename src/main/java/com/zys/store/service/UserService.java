package com.zys.store.service;

import com.zys.store.entity.User;

public interface UserService {
    void register(User user);

    User login(String username,String password);

    /**
     * changePassword方法需要什么参数:
     * 要先看底层持久层需要什么参数:uid,password,modifiedUser,modifiedTime
     * 1.修改人其实就是username,已经保存到session当中,通过控制层传递过来就行了
     * 2.在更新数据之前需要先根据uid查这个数据存不存在,uid也可以通过控制层传递
     * 3.新密码需要有
     * 4.修改时间不需要在参数列表,直接在方法内部new Date()就可以了
     * 5.旧密码
     * */
    void changePassword(Integer uid,
                        String username,
                        String oldPassword,
                        String newPassword);

    void changeInfo(Integer uid,User user);

    User getByUid(Integer uid);

    /**
     * 修改用户的头像
     * @param uid 用户uid
     * @param avatar 用户头像的路径
     * @param username 用户名称
     */
    void changeAvatar(Integer uid,
                      String avatar,
                      String username);//业务层一般叫username而不叫modifiedUser,因
    // 为业务层并没有直接和数据库关联

}
