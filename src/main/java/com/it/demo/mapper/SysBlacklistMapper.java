package com.it.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Brave
 * @version 1.0
 * @date 2020/10/12
 */
public interface SysBlacklistMapper {

    /**
     * 根据ip查询记录数
     * @param ip
     * @return 记录数
     */
    @Select("SELECT count(*) FROM sys_blacklist where del_flag=0 and status=1 and ip = #{ip}")
    int count(@Param("ip") String ip);

    /**
     * 新增黑名单
     * 其中id为自增，del_flag数据库默认设为0，status默认设为1
     * @param ip
     */
    @Insert("INSERT INTO sys_blacklist (ip) VALUES (#{ip})")
    void add(@Param("ip") String ip);
}
