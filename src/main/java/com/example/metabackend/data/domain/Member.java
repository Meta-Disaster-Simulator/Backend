package com.example.metabackend.data.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
    private String id;       // ID
    private String password; // password
    private int score;       // score
    private String nickname; // member nickname
}
