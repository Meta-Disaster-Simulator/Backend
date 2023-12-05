package com.example.metabackend.service;

import com.example.metabackend.data.domain.Member;
import com.example.metabackend.data.dto.SignupDTO;
import com.example.metabackend.repository.memberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional // Test 단위에서 Transactional 사용시 Test 완료 되면 DB 저장이 안됨 (Test 용도)
public class memberServiceTest {

    @Autowired
    private memberService memberService;
    @Autowired
    private memberRepository memberRepository;

    @Test
    public void 올바른_회원가입_테스트() { // 최근 작동 여부 : O
        SignupDTO form = new SignupDTO();
        form.setId("아이디");
        form.setNickname("강민기");
        form.setPassword("비밀번호");
        Member member = memberService.join(form);
        assertThat(member.getNickname())
                .isEqualTo(memberRepository.findbyid("아이디").get().getNickname());
    }

    @Test
    public void 아이디_중복_회원가입_에러_테스트() { // 최근 작동 여부 : O
        SignupDTO form = new SignupDTO();
        form.setId("Id");
        form.setNickname("name");
        form.setPassword("password");

        SignupDTO form2 = new SignupDTO();
        form2.setId("Id");
        form2.setNickname("na");
        form2.setPassword("pass");

        memberService.join(form);//메서드 실행 시 IllegalStateException 예외 발생하면 잘 실행됨
        assertThrows(IllegalStateException.class, ()-> {
            memberService.join(form2);
        });
    }




}
