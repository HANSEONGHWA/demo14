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

    //* memberRepository singleton test
    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()

    //*ConfigurationSingletonTest 실행 시 예상.
    //memberService
    //call AppConfig.memberService 호출, 출력
    //call AppConfig.memberRepository*
    //memberRepository
    //call AppConfig.memberRepository*
    //orderService
    //call AppConfig.orderService
    //call AppConfig.memberRepository*
    //memberRepository 3번 호출 예측

    //*ConfigurationSingletonTest 실행 후
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.orderService
    //memberRepository가 한 번 호출 됨.

    //AppConfig@CGLIB가 등록되어 싱글톤이 보장됨.
    //@Configuration을 붙이면 바이트 코드를 조작하는 CGLIB 기술을 사용해서 싱글톤 보장,
    //@Bean만 적용했을 경우 스프링 빈으로 등록 되지만,  싱글톤이 깨지고 때마다 새로 생성됨.(3번 호출)

    //스프링 설정 정보는 항상  @Configuration 을 사용!!

    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
        public MemberRepository memberRepository(){
        System.out.println("call AppConfig.memberRepository");
            return new MemoryMemberRepository();
        }

    @Bean
     public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(),discountPolicy());
    }
    @Bean
        public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
            return new RateDiscountPolicy();
        }
}

