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
    public void 저장_test() {
        Member member = new Member();
        member.setName("name");
        member.setMemberId("ID");
        member.setMemberPassword("password");
        member.setRole("USER");
        memberRepository.save(member);
        assertThat(member.getName())
                .isEqualTo(memberRepository.findbyid("ID").get().getName());
    }





}
