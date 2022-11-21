package com.example.demo14.member;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);
}
