package com.example.metabackend.controller;


import com.example.metabackend.data.dto.SignupDTO;
import com.example.metabackend.data.dto.TokenInfo;
import com.example.metabackend.service.memberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class PostController {
    private memberService memberservice;

    @Autowired
    public PostController(memberService memberService) {
        this.memberservice = memberService;
    }

      @PostMapping("/signup")// 회원가입 요청
    public String signup_member(SignupDTO form) {
        try {
            memberservice.join(form);
        } catch (IllegalStateException e) {
            return "redirect:/signup";
        }
        return "redirect:/";
    }

    @PostMapping("/login")// 로그인 요청
    @ResponseBody
    public TokenInfo login(login_form loginForm) {
        TokenInfo tokenInfo = memberservice.login(loginForm);
        return tokenInfo;
    }
    @PostMapping("/refresh")// 로그인 요청
    @ResponseBody
    public TokenInfo refreshToken(HttpServletRequest request) {
        String refreshToken = request.getHeader("Authorization");
        if (StringUtils.hasText(refreshToken) && refreshToken.startsWith("Bearer")) {
            refreshToken = refreshToken.substring(7);
        }
        return TokenInfo.builder()
                .grantType("Bearer")
                .accessToken(memberservice.generateAccessToken(refreshToken))
                .refreshToken(refreshToken)
                .build();
    }
}
