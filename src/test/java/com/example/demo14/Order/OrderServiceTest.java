package com.example.demo14.Order;

import com.example.demo14.AppConfig;
import com.example.demo14.member.Grade;
import com.example.demo14.member.Member;
import com.example.demo14.member.MemberService;
import com.example.demo14.member.MemberServiceImpl;
import com.example.demo14.order.Order;
import com.example.demo14.order.OrderService;
import com.example.demo14.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

//    MemberService memberService = new MemberServiceImpl();
//    OrderService orderService = new OrderServiceImpl();

    MemberService memberService;
    OrderService orderService;

    //테스트 실행 전 무조건 실행됨.
    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder(){
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
