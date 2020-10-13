package com.it.demo.service;

/**
 * @author Brave
 * @version 1.0
 * @date 2020/10/12
 */
public interface SysBlacklistService {

    /**
     * 根据ip查询记录数
     * @param ip
     * @return 记录数
     */
    int count(String ip);

    /**
     * 新增黑名单
     * @param ip
     */
    void add(String ip);
}
