package com.it.demo.mapper;

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

}
