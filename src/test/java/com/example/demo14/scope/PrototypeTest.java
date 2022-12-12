package com.example.demo14.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

// *프로토타입 빈
// 스프링 컨테이너에 요청할 때 마다 새로 생성됨.
// 스프링 컨테이너는 프로토타입 빈의 생성과 의존관계 주입, 초기화까지만 관여함.
// 종료 메서드가 호츨되지 않음.
// 프로토타입 빈을 조회한 클라이언트가 관리해야 함. 종료 메서드에 대한 호출도 클라이언트가 직접 해야함.

//프로토타입 빈 요청
//프로토타입 스코프 빈을 스프링 컨테이너에 요청 -> 스프링 컨테이너는 이 시점에서  프로토타입 빈 생성 , 필요한 의존관계 주입.
//-> 생성한 프로토타입 빈을 클라이언트에 반환 -> 이후 같은 요청이 들어오면 항상 새로운 프로토타입 빈을 생성해서 반환.
//***따라서 스프링 컨테이너는 프로토타입 빈을 생성, 의존관계 주입, 초기화까지만 처리..
public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        //destroy를 호출해야한다면 직접호출해줘야함.
        prototypeBean1.destroy();
        prototypeBean2.destroy();
        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean {

        @PostConstruct
        public void init() {
            System.out.println("prototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("prototypeBean.destroy");
        }
    }

}
