package com.pkaravaev;

import com.pkaravaev.config.SpringRootConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class TestDataSource {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringRootConfig.class);

        DataSource dataSource = context.getBean(DataSource.class);

//        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//
//        String sql = "INSERT INTO user  ('name', 'phone', 'email', 'address', 'loginName', 'password') VALUES (?,?,?,?,?,?)";
//
//        Object[] param = new Object[]{"Pavel", "9999999", " mail@mail.ru", "Moscow", "pavel1990", "q1w2e3r4t5"};
//
//        jdbcTemplate.update(sql, param);
//
//        System.out.println("-----------SQL executed -----------");
    }
}
