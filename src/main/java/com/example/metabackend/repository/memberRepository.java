package com.example.metabackend.repository;

import com.example.metabackend.data.domain.Member;

import java.util.Optional;

public interface memberRepository { //member 관리 repository
    Member save(Member member);


    Optional<Member> findbyid(String memberid);


    Optional<Member> findByNickname(String name);

    Member updatescore(Member member);
//    Member findbyname(String name);


//    List<Member> findall();

}
