package com.example.demo14.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component//(스프링 빈 등록 의존관계 설정할 수 없어 생성자에 @AutoWired 사용하여 자동의존관계 주입. )
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
