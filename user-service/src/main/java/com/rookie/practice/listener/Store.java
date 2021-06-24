package com.rookie.practice.listener;

import com.rookie.practice.entity.SysUserInfo;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: <br/>
 * @Copyright(c): 2020, Rookie <br/>
 * @ProgramName: Store.java <br/>
 * @Date: 2020/4/3 11:13
 * @Author: NIE <br/>
 * @Version: 1.0
 */
public class Store<E extends SysUserInfo> {

    private final List<ProductListener> listeners = new ArrayList<>();

    private final Map<String, SysUserInfo> userMap = new HashMap<>();


    protected void addUser(String name,E user) {
        userMap.put(name, user);
        listeners.forEach(x -> x.publish(user));
    }

    public void addListener(ProductListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ProductListener listener) {
        listeners.remove(listener);
    }
}
