package com.example.metabackend.config;


import com.example.metabackend.repository.JDBCTemplatememberRepository;
import com.example.metabackend.repository.memberRepository;
import com.example.metabackend.service.MyDetailsService;
import com.example.metabackend.service.memberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class Config {

    @Autowired
    private DataSource dataSource;

    public Config(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public memberService memberservice() {
        return new memberService(memberRepository());
    }

    @Bean
    public memberRepository memberRepository() {
//        return new MemorymemberRepository();
        return new JDBCTemplatememberRepository(dataSource);
    }
    @Bean
    public MyDetailsService myDetailsService(){
        return new MyDetailsService(memberRepository());
    }
}
