package com.example.demo14.singleton;

import com.example.demo14.AppConfig;
import com.example.demo14.member.MemberRepository;
import com.example.demo14.member.MemberServiceImpl;
import com.example.demo14.member.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    // memberService와 orderService에서 참조하는 repository가 같은지? Test.(singleton)
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        // 테스트 메소드 꺼내기 위해 impl로 꺼내봄.
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService에서 참조하는 repository -> memberRepository1 = " + memberRepository1);
        System.out.println("orderService에서 참조하는 repository -> memberRepository1 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
        //AppConfig도 Bean에 등록.
        //임의의 다른 클래스가 singleton이 보장되도록 해줌. (CGLIB)
        //@Bean 이 붙은 메서드마다 이미 스프링 빈이 존재(스프링 컨테이너에 존재)하면 존재하는 Bean을 반환,
        //스프링 빈이 없으면 생성해서 스프링 빈으로 반환하는 코드가 동적으로 만들어짐.
        //덕분에 싱글톤 보장됨.


    }
}
