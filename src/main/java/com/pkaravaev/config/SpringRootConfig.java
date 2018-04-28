package com.pkaravaev.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.pkaravaev" )
public class SpringRootConfig   {

    //TODO: Services , DAO, DATASOURCe, EMAIL SENDER or some other Buisness layer Beans
}
