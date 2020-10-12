package com.it.demo.interceptor;

import com.it.demo.excetion.ApiSecurityException;
import com.it.demo.service.SysBlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brave
 * @version 1.0
 * @date 2020/10/12
 */
@Component
public class ApiSecurityInterceptor implements HandlerInterceptor {

    @Autowired
    private SysBlacklistService sysBlacklistService;

    /**
     * 前置拦截器
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取ip
        String ip = request.getRemoteAddr();
        // 检查该ip是否为黑名单
        int count = sysBlacklistService.count(ip);

        if(count>0){
            throw new ApiSecurityException("您以被加入黑名单，不能访问...");
        }

        return true;
    }
}
