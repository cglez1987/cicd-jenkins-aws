/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dcarlidev.testspringbootwithrdsmysql.config;

import com.amazonaws.xray.javax.servlet.AWSXRayServletFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author carlos
 */
@Configuration
public class Config {

    @Bean
    public AWSXRayServletFilter tracingFilter() {
        return new AWSXRayServletFilter("TestSpringBootCarlos");
    }

}
