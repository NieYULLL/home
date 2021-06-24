package com.rookie.practice.listener;

import com.rookie.practice.entity.SysUserInfo;

/**
 * @Description: <br/>
 * @Copyright(c): 2020, Rookie <br/>
 * @ProgramName: Test.java <br/>
 * @Date: 2020/4/3 11:24
 * @Author: NIE <br/>
 * @Version: 1.0
 */
public class Test {
    public static void main(String[] args) {
        Store<SysUserInfo> store = new Store<>();
        CustomerListener customerListener = new CustomerListener();
        AdminListener adminListener = new AdminListener();
        store.addListener(customerListener);
        store.addListener(adminListener);
        store.addUser("hello",new SysUserInfo());
        store.removeListener(customerListener);
        store.addUser("world",new SysUserInfo());
    }
}
