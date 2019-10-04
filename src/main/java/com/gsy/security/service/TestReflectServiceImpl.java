package com.gsy.security.service;

import org.springframework.stereotype.Service;

@Service("testReflectService")
public class TestReflectServiceImpl {

    public String first(String name){
        System.out.println(name);
        return name;
    }
}
