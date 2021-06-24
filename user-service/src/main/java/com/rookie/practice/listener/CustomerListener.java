package com.rookie.practice.listener;

import com.rookie.practice.entity.SysUserInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: <br/>
 * @Copyright(c): 2020, Rookie <br/>
 * @ProgramName: CustomerListener.java <br/>
 * @Date: 2020/4/3 11:21
 * @Author: NIE <br/>
 * @Version: 1.0
 */
@Slf4j
public class CustomerListener implements ProductListener {
    @Override
    public void publish(SysUserInfo sysUserInfo) {
        log.info("customer add new User:{}",sysUserInfo);
    }
}
