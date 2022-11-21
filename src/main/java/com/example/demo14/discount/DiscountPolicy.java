package com.example.demo14.discount;


import com.example.demo14.member.Member;

public interface DiscountPolicy {

    // return 할인 대상 금액
    int discount(Member member, int price);

}
