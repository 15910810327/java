package com.example.lyl.service;

import org.springframework.stereotype.Service;

/**
 * Created by lyl_b on 2017/12/22.
 */
@Service
public class NoteHandle {

    public String sayHello(String name){
        return "Hello:"+name;
    }

    public String sayHelloError(String name){
        return Integer.parseInt("--")+"";
    }
}
