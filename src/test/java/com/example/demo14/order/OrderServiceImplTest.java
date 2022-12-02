package com.example.demo14.order;

import com.example.demo14.discount.FixDiscountPolicy;
import com.example.demo14.member.Grade;
import com.example.demo14.member.Member;
import com.example.demo14.member.MemoryMemberRepository;
import com.example.demo14.member.order.Order;
import com.example.demo14.member.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}