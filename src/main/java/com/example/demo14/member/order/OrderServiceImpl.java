package com.example.demo14.member.order;

import com.example.demo14.discount.DiscountPolicy;
import com.example.demo14.member.Member;
import com.example.demo14.member.MemberRepository;
import com.example.demo14.member.order.Order;
import com.example.demo14.member.order.OrderService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //구현체까지 의존.X
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy2 = new RateDiscountPolicy();

    //인터페이스에만 의존.
    //final 키워드 , 생성자 주입을 사용하면  필드에 final사용 가능.. 생성자에서 값이 설정되지 않는 오류를 컴파일 시점에서 막아줌.
    //컴파일 오류 : 필수 필드인 discountPolicy 값을 설정해야하는데 누락됐을 경우.
    //  java: variable discountPolicy might not have been initialized. 발생.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

// 수정자 주입방식(set)
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

    @Autowired
    private DiscountPolicy rateDiscountPolicy;

    //생성자 의존관계 주입
    // 같은 타입의 값이 여러 개일 경우 파라미터로 설정.
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository,  DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = rateDiscountPolicy;
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

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
