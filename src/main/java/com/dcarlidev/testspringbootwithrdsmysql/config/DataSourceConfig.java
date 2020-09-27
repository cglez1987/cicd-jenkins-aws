/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dcarlidev.testspringbootwithrdsmysql.config;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;

/**
 *
 * @author carlos
 */
@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource getDataSource() {

        String environment = System.getenv("APP_ENVIRONMENT") != null && System.getenv("APP_ENVIRONMENT").equals("PROD") ? "/prod/" : "/dev/";
        SsmClient client = SsmClient.create();
        String dbName = client.getParameter(GetParameterRequest.builder().name(environment + "dbname").build()).parameter().value();
        String userName = client.getParameter(GetParameterRequest.builder().name(environment + "dbusername").build()).parameter().value();;
        String password = client.getParameter(GetParameterRequest.builder().withDecryption(true).name(environment + "dbpassword").build()).parameter().value();;
        String hostname = client.getParameter(GetParameterRequest.builder().name(environment + "dburl").build()).parameter().value();;
        String port = client.getParameter(GetParameterRequest.builder().name(environment + "dbport").build()).parameter().value();;
        String driverClass = "com.mysql.cj.jdbc.Driver";
        String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName;
//        if (System.getenv("RDS_HOSTNAME") != null) {
//            dbName = System.getenv("RDS_DB_NAME");
//            userName = System.getenv("RDS_USERNAME");
//            password = System.getenv("RDS_PASSWORD");
//            hostname = System.getenv("RDS_HOSTNAME");
//            port = System.getenv("RDS_PORT");
//            jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName;
//        }
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.driverClassName(driverClass);
        builder.url(jdbcUrl);
        builder.username(userName);
        builder.password(password);
        return builder.build();
    }

}
