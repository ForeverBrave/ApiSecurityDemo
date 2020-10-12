package com.it.demo.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Brave
 * @version 1.0
 * @date 2020/10/12
 */
@Data
public class SysBlacklist {
    /**
     * id
     */
    private Long id;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人
     */
    private Long createBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 更新人
     */
    private Long updateBy;
    /**
     * 删除标识  true：已删除  false：未删除
     */
    private Boolean delFlag;
    /**
     * 黑名单ip
     */
    private String ip;
    /**
     * 状态  true：启用  false：禁用
     */
    private Boolean status;
}
