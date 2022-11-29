package com.example.demo14.autowired;

import com.example.demo14.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

//옵션처리
//주입할 스프링 빈이 없어도 동작해야 할 때
public class AutowiredTest {
    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }
//자동 주입 대상을 옵션으로 처리하는 방법 3가지 (자동 주입 대상이 없을 경우.)
    static class TestBean {

        // 1. @Autowired(required = false) : 자동 주입할 대상이 없으면 수정자 메서드 자세가 호출이 안됨.
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        //2. @Nullable :  자동 주입할 대상이 없으면 null이 입력됨.
        @Autowired
        public  void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        //3. Optional<> : 자동 주입할 대상이 없으면 'Optional.empty'가 입력됨.
        @Autowired(required = false)
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }

    }

}
