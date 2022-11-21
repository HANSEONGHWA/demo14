package com.example.demo14.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {

    // 회원가입
    private static Map<Long, Member> store = new HashMap<>();

    //회원저장
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    //회원의 아이디로 회원 찾기
    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
