package com.zys.store.controller;

import com.zys.store.entity.Address;
import com.zys.store.service.AddressService;
import com.zys.store.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("addresses")
@RestController
public class AddressController extends BaseController {
    @Autowired
    private AddressService addressService;

    @RequestMapping("add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.addNewAddress(uid, username, address);
        return new JsonResult<>(ok);

    }

    @RequestMapping({"", "/"})
    public JsonResult<List<Address>> getByUid(HttpSession session){
        Integer uid = getUidFromSession(session);
        List<Address> data = addressService.getByUid(uid);
        return new JsonResult<>(ok,data);
    }


    @RequestMapping("{aid}/set_default")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid, HttpSession session){
        addressService.setDefault(
                aid,
                getUidFromSession(session),
                getUsernameFromSession(session)
        );
        return new JsonResult<>(ok);
    }

    @RequestMapping("{aid}/delete")
    public JsonResult<Void> delete(@PathVariable("aid") Integer aid,HttpSession session) {
        addressService.delete(
                aid,
                getUidFromSession(session),
                getUsernameFromSession(session));
        return new JsonResult<>(ok);
    }

}
