package com.example.demo14.order;

import com.example.demo14.discount.DiscountPolicy;
import com.example.demo14.discount.FixDiscountPolicy;
import com.example.demo14.discount.RateDiscountPolicy;
import com.example.demo14.member.Member;
import com.example.demo14.member.MemberRepository;
import com.example.demo14.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //구현체까지 의존.X
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy2 = new RateDiscountPolicy();

    //인터페이스에만 의존.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    // 주문 생성 요청
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        //아이디 조회
        Member member = memberRepository.findById(memberId);
        //할인 정책에 회원을 넘김. (Grede, member 중 넘길 건 알아서)
        //최종 할인 된 금액을 받음.
        int discountPrice = discountPolicy.discount(member, itemPrice);
        //최종 생성된 금액 반환.
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
