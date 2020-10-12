package com.it.demo.service.Impl;

import com.it.demo.mapper.SysBlacklistMapper;
import com.it.demo.service.SysBlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Brave
 * @version 1.0
 * @date 2020/10/12
 */
@Service
public class SysBlacklistServiceImpl implements SysBlacklistService {

    @Autowired
    private SysBlacklistMapper sysBlacklistMapper;

    /**
     * 根据ip查询记录数
     * @param ip
     * @return 记录数
     */
    @Override
    public int count(String ip) {
        return sysBlacklistMapper.count(ip);
    }
}
