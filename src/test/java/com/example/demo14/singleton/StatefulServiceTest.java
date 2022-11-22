package com.example.demo14.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;
//싱글톤 방식의 주의점
class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);
        //StatefulService는 같은 객체임. 따라서 price에 값이 들어가 바뀜.

        //ThreadA: A사용자 10000원 주문
//        statefulService1.order("userA", 10000);
        //ThreadA: B사용자 20000원 주문
//        statefulService2.order("userB", 20000);

        //필드 대신 공유되지 않는 지역변수, 파라미터, ThreadLocal 등 사용해야함. !!
        //ThreadA: A사용자 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        //ThreadA: B사용자 20000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        //ThreadA: 사용자A 주문 금액 조회
        //statefulService1, statefulService2 상관 없이 20000이 조회됨.
//        int price = statefulService1.getPrice();
        System.out.println("price = " + userAPrice);

//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}