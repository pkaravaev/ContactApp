package com.pkaravaev.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;

@Controller
public class TestController {

    @Autowired
    DataSource dataSource;

    @RequestMapping("/test/helloworld")
    public String helloWorld(){


        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "INSERT INTO user (name, phone, email, address, loginName, password) " +
                "VALUES (?,?,?,?,?,?)";

        Object[] param = new Object[]{"Pavel", "9999999", " mail@mail.ru", "Moscow", "pavel1990", "q1w2e3r4t5"};

        jdbcTemplate.update(sql, param);

        System.out.println("-----------SQL executed -----------");





        return "hello";
    }
}
