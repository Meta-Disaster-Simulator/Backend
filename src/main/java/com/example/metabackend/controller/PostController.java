package com.example.metabackend.controller;


import com.example.metabackend.data.domain.Member;
import com.example.metabackend.data.dto.*;
import com.example.metabackend.service.memberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


//@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = {"POST", "GET"})
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
    public TokenInfo login(LoginDTO loginDTO) {
        TokenInfo tokenInfo = memberservice.login(loginDTO);
        return tokenInfo;
    }
    @PostMapping("/logout")// 로그아웃 요청
    @ResponseBody
    public ResponseEntity<?> logout(StatusDTO logoutDTO) {
        memberservice.logout(logoutDTO);
        return ResponseEntity.ok().build();
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

    @PostMapping("/stat")
    @ResponseBody
    public ReturnStatusDTO getStatus(@RequestBody StatusDTO statusDTO){
        Member member = memberservice.getStatus(statusDTO.getId());
        return new ReturnStatusDTO(member.getNickname(), member.getScore());
    }
    @PostMapping("/score")
    @ResponseBody
    public String getStatus(@RequestBody ScoreUpdateDTO scoreUpdateDTO){
        memberservice.updateScore(scoreUpdateDTO);
        return "success";
    }



}