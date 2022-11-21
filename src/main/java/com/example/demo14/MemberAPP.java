package com.example.demo14;

import com.example.demo14.member.Grade;
import com.example.demo14.member.Member;
import com.example.demo14.member.MemberService;
import com.example.demo14.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberAPP {
    public static void main(String[] args) {
        // 구현체를 의존하고 있어 바람직하지 않음.
//        MemberService memberService = new MemberServiceImpl();

        //인터페이스에만 의존할 수 있도록 AppConfig 사용하여 변경.
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        // 스프링 컨테이너.. 객체를 관리(@Bean)
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);


        Member member = new Member(1L, "memberA",Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());


    }
}
