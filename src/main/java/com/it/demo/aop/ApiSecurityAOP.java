package com.it.demo.aop;

import com.it.demo.annotation.ApiSecurity;
import com.it.demo.constant.CacheConstant;
import com.it.demo.excetion.ApiSecurityException;
import com.it.demo.service.SysBlacklistService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author Brave
 * @version 1.0
 * @date 2020/10/12
 */
@Slf4j
@Aspect
@Component
public class ApiSecurityAOP {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SysBlacklistService sysBlacklistService;

    @Before("@annotation(apiSecurity)")
    public void around(ProceedingJoinPoint point, ApiSecurity apiSecurity){
        // 获取HttpRequest
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 判断request不能为空
        if (request == null) {
            throw new ApiSecurityException("HttpServletRequest有误...");
        }

        ApiSecurity security = this.getAnnotation(point);
        if(security == null) {
            return;
        }

        String ip = request.getRemoteAddr();
        String uri = request.getRequestURI();
        String key = CacheConstant.key + uri + ":" + ip;

        // 检查 ip是否访问过url
        Integer num = (Integer) redisTemplate.opsForValue().get(key);

        if(num == null){
            // 若没访问过，则存入redis并将注解中的时间段设置为过期时间
            redisTemplate.opsForValue().set(key, 1, apiSecurity.second(), TimeUnit.SECONDS);
        }

        // 检查访问次数是否大于注解定义的次数
        if(num > apiSecurity.count()){
            // 新增黑名单
            sysBlacklistService.add(ip);
            throw new ApiSecurityException("不能重复请求");
        }

        // 用redis的increment原子性加1
        redisTemplate.opsForValue().increment(key, 1);
    }

    /**
     * 获取注解
     * @param point
     * @return
     * @throws Exception
     */
    private ApiSecurity getAnnotation(ProceedingJoinPoint point) {

        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(ApiSecurity.class);
        }
        return null;
    }


}
