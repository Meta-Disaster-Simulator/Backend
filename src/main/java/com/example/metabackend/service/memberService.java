package com.example.metabackend.service;

import com.example.metabackend.JwtTokenProvider.JwtTokenProvider;
import com.example.metabackend.data.domain.Member;
import com.example.metabackend.data.dto.CreateMemberDTO;
import com.example.metabackend.data.dto.TokenInfo;
import com.example.metabackend.data.dto.login_form;
import com.example.metabackend.repository.memberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

public class memberService {
    private memberRepository memberrepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public memberService(memberRepository memberrpository) {
        this.memberrepository = memberrpository;

    }

    //회원 가입 로직
    // DTO 를 domain으로 수정
    // 동일한 id 있을 시에는 예외
    public Member join(CreateMemberDTO form) throws IllegalStateException{
        Member member = new Member();
        member.setNickname(form.getNickname());
        member.setId(form.getId());
        member.setPassword(passwordEncoder.encode(form.getPassword()));
        member.setScore(0); // 회원 가입 초기값
        //member.setRole("ROLE_ADMIN");
        Duplicate(member); // 동일 검사
        memberrepository.save(member);
        return member;
    }

    //로그인
    //
    public TokenInfo login(login_form loginForm) {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginForm.getId(), loginForm.getPassword());

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        return tokenInfo;
    }
    private void Duplicate(Member member) { // ID와 password 일치 코드
        memberrepository.findbyid(member.getId()).ifPresent(m -> {
            throw new IllegalStateException("이미 있는 ID");
        });


    }
}
