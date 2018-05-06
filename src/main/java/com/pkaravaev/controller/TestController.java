package com.pkaravaev.controller;


import com.pkaravaev.dao.UserDAO;
import com.pkaravaev.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserDAO userDAO;

    @RequestMapping("/test/helloworld")
    public String helloWorld(){


        List<User> all = userDAO.findAll();

        return "hello";
    }
}
