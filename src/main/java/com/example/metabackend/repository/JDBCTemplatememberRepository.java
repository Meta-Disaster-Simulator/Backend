package com.example.metabackend.repository;

import com.example.metabackend.data.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class JDBCTemplatememberRepository implements memberRepository {


    private JdbcTemplate jdbcTemplate;


    public JDBCTemplatememberRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Member save(Member member) {
        // SQL query를 정의
        String sql = "INSERT INTO member (id, password, score, nickname) VALUES (?, ?, ?, ?)";
        // SQL query 실행
        jdbcTemplate.update(sql, member.getId(), member.getPassword(), member.getScore(), member.getNickname());
        return member;
    }

    @Override
    public Optional<Member> findbyid(String id) {
        String sql = "SELECT * FROM member where id=?";
        List<Member> result = jdbcTemplate.query(sql,memberRowMapper(), id);

        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByNickname(String nickname) {
        String sql = "SELECT * FROM member where nickname=?";
        List<Member> result = jdbcTemplate.query(sql, memberRowMapper(), nickname);

        return result.stream().findAny();
    }

    @Override
    public Member updatescore(Member member) {
        String sql = "UPDATE member SET score=? WHERE nickname=?";
        jdbcTemplate.update(sql, member.getScore(), member.getNickname());
        return null;
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs,rowNum) ->{
            Member member = new Member();
            member.setId(rs.getString("id"));
            member.setPassword(rs.getString("password"));
            member.setScore(rs.getInt("score"));
            member.setNickname(rs.getString("nickname"));
            return member;
        };
    }
}
