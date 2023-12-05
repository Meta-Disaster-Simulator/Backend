package com.example.metabackend.data.dto.wsDTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerDTO { // 실시간 통신을 위한 DTO
    private String nickname; // 이름
    private int score; // 사용자의 최고 점수
    // 사용자의 위치
    private float pos_x;
    private float pos_y;
    private float pos_z;
    // 사용자의 회전 값
    private float rot_x;
    private float rot_y;
    private float rot_z;
    // 사용자 에니매이션 bool 값
    private boolean is_jump;
    private boolean is_walk;
    private boolean is_run;
}
