package com.example.metabackend.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TokenInfo {
    private String grantType; // token type
    private String accessToken;
    private String refreshToken;
}