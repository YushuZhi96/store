package com.zys.store.service;

import com.zys.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTests {
    @Autowired
    private AddressService addressService;

    @Test
    public void addNewAddress() {
        Address address = new Address();
        address.setPhone("175726");
        address.setName("男朋友");
        addressService.addNewAddress(6,"test01",address);
    }

    @Test
    public void setDefault() {
        addressService.setDefault(9,11,"管理员");
    }

    @Test
    public void delete() {
        addressService.delete(1,11,"4.11删除");
    }


}
