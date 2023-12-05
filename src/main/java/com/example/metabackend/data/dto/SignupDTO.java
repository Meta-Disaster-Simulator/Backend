package com.example.metabackend.data.dto;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SignupDTO { // 회원가입
    private String id; // 아이디
    private String password; // 비밀번호
    private String nickname; // 이름
}
