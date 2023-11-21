package com.example.metabackend.repository;


import com.example.metabackend.data.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class memberRepositoryIT_Test {

    @Autowired
    private memberRepository memberRepository;

    @Test
    public void 저장_test() { // 최근 작동 여부 : O
        Member member = new Member();
        member.setNickname("name");
        member.setPassword("password");
        member.setId("Id");
        member.setScore(0);
        memberRepository.save(member);
        assertThat(member.getNickname())
                .isEqualTo(memberRepository.findbyid("ID").get().getNickname());
    }

    @Test
    public void 읽기_테스트(){// 최근 작동 여부 : O
        Member member = new Member();
        member.setNickname("name");
        member.setPassword("password");
        member.setId("Id");
        member.setScore(0);
        memberRepository.save(member);
        // 주의 member 는 VO 가 아니라서 객체의 값으로 비교해야 함
        assertThat(memberRepository.findByNickname("name").get().getNickname())
                .isEqualTo(memberRepository.findbyid("ID").get().getNickname());
    }





}
