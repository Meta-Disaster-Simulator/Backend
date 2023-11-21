package com.example.metabackend.config;


import com.example.metabackend.repository.JDBCTemplatememberRepository;
import com.example.metabackend.repository.memberRepository;
import com.example.metabackend.service.MyDetailsService;
import com.example.metabackend.service.memberService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:/application.properties")
public class Config {
    @Bean
    @ConfigurationProperties(prefix="spring.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource(){
        DataSource dataSource = new HikariDataSource(hikariConfig());
        return dataSource;
    }

    @Bean
    public memberService memberservice() {
        return new memberService(memberRepository());
    }

    @Bean
    public memberRepository memberRepository() {
//        return new MemorymemberRepository();
        return new JDBCTemplatememberRepository(dataSource());
    }
    @Bean
    public MyDetailsService myDetailsService(){
        return new MyDetailsService(memberRepository());
    }
}
