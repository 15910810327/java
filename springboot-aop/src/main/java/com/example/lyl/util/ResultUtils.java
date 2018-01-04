package com.example.lyl.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lyl_b on 2017/8/5.
 */
public class ResultUtils {

    private static ResultUtils resultUtils;
    static {
        resultUtils = new ResultUtils();
    }
    public static ResultUtils getInstance(){
        return resultUtils;
    }

    public Map<String,String> resuErrorParamsMap(){
        Map<String,String> map=new HashMap<String, String>();
        map.put("code","10201");
        map.put("type","INVALID_PARAMETERS");
        map.put("message","");
        return map;
    }

    public Map<String,Object> resuSystemErrorMap(String message){
        Map<String,Object> resuMap=new HashMap<String, Object>();
        resuMap.put("code","500");
        resuMap.put("type","SERVICE_UNAVAILABLE");
        resuMap.put("message",message);
        return resuMap;
    }
    public Map<String,String> resuInNotMethodMap(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", "40001");
        map.put("type", "IN-NOR-METHOD");
        map.put("message", "");
        return map;
    }

    /**
     * 成功返回PageBean
     *
     * @param obj
     * @return
     */
    public Map<String, Object> seccessObject(Object obj) {
        Map<String, Object> resmap = new HashMap<String, Object>();
        resmap.put("code", "200");
        resmap.put("type", "success");
        resmap.put("message", obj);
        return resmap;
    }

    /**
     * 入参错误
     *
     * @return
     */
    public Map<String, Object> errorINVALID() {
        Map<String, Object> resmap = new HashMap<String, Object>();
        resmap.put("code", "10201");
        resmap.put("type", "PARAMETERS_ERROR");
        resmap.put("message", new Object[]{});
        return resmap;
    }

    /**
     * 没有数据
     *
     * @return
     */
    public Map<String, Object> erroFOUND() {
        Map<String, Object> resmap = new HashMap<String, Object>();
        resmap.put("code", "10203");
        resmap.put("type", "NO_DATA_ERROR");
        resmap.put("message", new Object[]{});
        return resmap;
    }

    /**
     * 系统异常
     */
    public Map<String, Object> errorSystem(String error) {
        Map<String, Object> resmap = new HashMap<String, Object>();
        resmap.put("code", "500");
        resmap.put("type", "SERVICE_ERROR");
        resmap.put("message", error);
        return resmap;
    }

    public Map<String, Object> respMap(Integer code, String msg, List<Object> data,
                                              Long totalcount, Integer pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "" + code);
        map.put("msg", msg);
        map.put("recordsTotal", "" + totalcount);
        map.put("pageSize", "" + pageSize);
        map.put("data", data);//
        return map;
    }
}
