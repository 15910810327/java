package com.example.lyl.Interceptor;

import com.example.lyl.util.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by lyl_b on 2017/6/24.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    static Logger _LOG;

    {
        _LOG = LoggerFactory.getLogger(GlobalExceptionHandler
                .class);
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map defaultErrorHandler(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Exception e) throws Exception {
        _LOG.error("", e);
        Map<String,Object> result = null;
        if(e instanceof org.springframework.web.bind.MissingServletRequestParameterException){
            result = ResultUtils.getInstance().resuSystemErrorMap(e.getMessage());
        }else if(e instanceof org.springframework.web.servlet.NoHandlerFoundException){
            result = ResultUtils.getInstance().resuSystemErrorMap(e.getMessage());
        }else{
            //TODO 其他异常可以发送邮件给开发者
            result = ResultUtils.getInstance().resuSystemErrorMap(e.getMessage());
        }
        return result;
    }
}
