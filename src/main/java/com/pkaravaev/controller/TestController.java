package com.pkaravaev.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class TestController {


    @RequestMapping("/test/hello")
    public String helloWorld(){

        return "hello";
    }

    @RequestMapping("/test/ajax_test")
    public String testpage(){

        return "test";
    }

    @RequestMapping("/test/get_time")
    @ResponseBody
    public String getServerTime(){
        System.out.println("--------getServerTime----------");
        Date d = new Date();
        return d.toString();
    }
}
