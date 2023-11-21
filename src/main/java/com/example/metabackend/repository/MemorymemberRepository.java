package com.example.metabackend.repository;

import com.example.metabackend.data.domain.Member;

import java.util.HashMap;
import java.util.Optional;

public class MemorymemberRepository implements memberRepository{

    private HashMap<Integer, Member> db = new HashMap<>();
    private int memberKey = 0;


    @Override
    public Member save(Member member) {
        db.put(++memberKey,member);
        return member;
    }

    @Override
    public Optional<Member> findbyid(String id) {
        return db.values().stream()
                .filter(member -> member.getId().equals(id))
                .findAny();
    }

    @Override
    public Optional<Member> findByNickname(String name) {
        return Optional.empty();
    }

    public void Clear() {
        db.clear();
    }






}
