package com.example.demo14;


import com.example.demo14.discount.DiscountPolicy;
import com.example.demo14.discount.FixDiscountPolicy;
import com.example.demo14.discount.RateDiscountPolicy;
import com.example.demo14.member.MemberRepository;
import com.example.demo14.member.MemberService;
import com.example.demo14.member.MemberServiceImpl;
import com.example.demo14.member.MemoryMemberRepository;
import com.example.demo14.order.OrderService;
import com.example.demo14.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {


//    public MemberService memberService(){
//        return new MemberServiceImpl(new MemoryMemberRepository());
//    }
//
//    public OrderService orderService(){
//        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
//    }


    // MemberServiceImpl을 만들고  MemoryMemberRepository를 사용함. 의존성 주입.
    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
        public MemberRepository memberRepository(){
            return new MemoryMemberRepository();
        }

    @Bean
     public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(),discountPolicy());
    }
    @Bean
        public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
            return new RateDiscountPolicy();
        }
}

