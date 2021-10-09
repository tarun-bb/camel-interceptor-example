package com.camel.interceptor.bean;

import org.springframework.stereotype.Component;

@Component
public class GetBean {

    public String sayHello() {
        return "Hello, world!";
    }

}
