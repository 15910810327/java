package com.example.lyl.controller;


import com.example.lyl.service.NoteHandle;
import com.example.lyl.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by lyl_b on 2017/12/21.
 */
@RestController
@RequestMapping(value ="hello")
public class HelloController {

    @Autowired
    NoteHandle noteHandle;

    @ResponseBody@GetMapping("/say")
    public Map<String, Object> word(@RequestParam(required = false) String name){
        return ResultUtils.getInstance().seccessObject(new NoteHandle().sayHello(name));
    }

    @ResponseBody@RequestMapping("/error")
    public Map<String, Object> error(@RequestParam(required = false) String name){
        return ResultUtils.getInstance().seccessObject(new NoteHandle().sayHelloError(name));
    }
}
