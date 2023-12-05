package com.example.metabackend.data.dto.wsDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitDTO {
    private String nickname; // 이름
    private int score; // 사용자의 최고 점수
    private boolean islogin; // true면 새로 접속 false면 로그아웃
}
