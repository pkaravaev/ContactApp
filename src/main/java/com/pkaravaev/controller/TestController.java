package com.pkaravaev.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/test/helloworld")
    public String helloWorld(){

        return "hello";
    }
}
