package com.rookie.practice.listener;

import com.rookie.practice.entity.SysUserInfo;

/**
 * @Description: <br/>
 * @Copyright(c): 2020, Rookie <br/>
 * @ProgramName: ProductListener.java <br/>
 * @Date: 2020/4/3 11:17
 * @Author: NIE <br/>
 * @Version: 1.0
 */
public interface ProductListener {

    void publish(SysUserInfo sysUserInfo);
}
