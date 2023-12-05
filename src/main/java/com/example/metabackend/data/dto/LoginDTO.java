package com.example.metabackend.data.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {// 로그인 입력 DTO
    private String id; // 로그인 입력 아이디
    private String password; // 로그인 입력 패스워드
}
