package com.example.metabackend.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReturnStatusDTO {
    private String nickname;
    private int score;
}
