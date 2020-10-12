package com.it.demo.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * 限制某个ip在某个时间段内请求方法的次数
 * @author Brave
 * @version 1.0
 * @date 2020/10/12
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Order(Ordered.HIGHEST_PRECEDENCE) // 设置顺序为最高优先级
public @interface ApiSecurity {

    /**
     * 限制某时间段内可以访问的次数
     * 默认设置100次（根据业务逻辑可以动态变更）
     * @return
     */
    int count() default 100;

    /**
     * 限制访问的某一个时间段，单位为秒
     * 默认60秒（根据业务逻辑可以动态变更）
     * @return
     */
    int second() default 60;

    /**
     * TODO 方便后续拓展，此处不做业务逻辑处理
     * @return
     */
    String value() default "";

}
