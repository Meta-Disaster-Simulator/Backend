package com.example.metabackend.controller;

import com.example.metabackend.data.dto.LoginDTO;
import com.example.metabackend.service.memberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetPageController {


    private memberService memberservice;

    @Autowired
    public GetPageController(memberService memberService) {
        this.memberservice = memberService;
    }

    @GetMapping("/") // main page
    public String main_page() {
        return "navar";
    }

    @GetMapping("/signup") // 회원 가입 페이지
    public String signup_page() {
        return "member/signup";
    }


    @GetMapping("/login") // 로그인 페이지
    public String login_get(LoginDTO loginDTO) {

        return "member/login";
    }
    @GetMapping("/user") // 임시 권한 적용 페이지
    public String user_get(Model model) {
        model.addAttribute("info", "사용자 정보입니다.");
        return "user_access";
    }
    @GetMapping("/game") // game 페이지
    public String test(){
        return "game";
    }


}
