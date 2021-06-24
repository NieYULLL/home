package com.rookie.practice.listener;

import com.rookie.practice.entity.SysUserInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: <br/>
 * @Copyright(c): 2020, Rookie <br/>
 * @ProgramName: AdminListener.java <br/>
 * @Date: 2020/4/3 11:22
 * @Author: NIE <br/>
 * @Version: 1.0
 */
@Slf4j
public class AdminListener implements ProductListener {
    @Override
    public void publish(SysUserInfo sysUserInfo) {
        log.info("admin add new User:{}",sysUserInfo);

    }
}
