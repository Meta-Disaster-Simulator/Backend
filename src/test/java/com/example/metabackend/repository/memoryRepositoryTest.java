package com.example.metabackend.repository;

import com.example.metabackend.data.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class memoryRepositoryTest {
    private MemorymemberRepository repository = new MemorymemberRepository();

    @AfterEach
    public void aftereach() {
        repository.Clear();
    }
    @Test
    public void 저장() {
        Member member = new Member();
        member.setNickname("name");
        member.setPassword("password");
        member.setId("Id");
        member.setScore(0);
        repository.save(member);
        Assertions.assertThat(repository.findbyid("Id").get())
                .isEqualTo(member);
    }



}
