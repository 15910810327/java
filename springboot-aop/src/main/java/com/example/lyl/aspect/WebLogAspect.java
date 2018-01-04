package com.example.lyl.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by lyl_b on 2017/12/22.
 */
@Aspect
@Component
public class WebLogAspect {
    private Logger _LOG = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("execution(public * com.example.lyl.controller..*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        _LOG.info("URL : " + request.getRequestURL().toString());
        _LOG.info("HTTP_METHOD : " + request.getMethod());
        _LOG.info("IP : " + request.getRemoteAddr());
        _LOG.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        _LOG.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        if(ret != null && ret.toString().length() > 1000){
            _LOG.info("RESPONSE : " + ret.toString().substring(0,1000));
        }else{
            _LOG.info("RESPONSE : " + ret);
        }
    }
}
