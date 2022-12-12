package com.example.demo14.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
//*빈 스코프란? --> 빈이 존재할 수 있는 범위.
//*스프링이 지원하는 스코프
//싱글톤 : 기본 스코프, 스프링 컨테이너의 시작과 종료까지 유지되는 가장 넓은 범위의 스코프.
//프로토타입 : 스프링컨테이너는 프로토타입 빈의 생성과 의존관계 주입까지만 관여하는 매우 짧은 범위의 스코프.
//웹스코프 : request(웹 요청이 들어오고 나갈때 까지 유지되는 스코프), session(웹세션 생성 종료 때까지 유지), application(웹의 서블릿 컨텍스트와 같은 범위로 유지)


//싱글톤 빈 요청
//싱글톤 스코프 빈을 스프링 컨테이너에 요청 -> 스프링 컨테이너는 관리하는 스프링 빈을 반환. -> 같은 요청이 와도 같은 객체 반환.
public class SingletonTest {

    @Test
    void singletonBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);
        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);
        Assertions.assertThat(singletonBean1).isSameAs(singletonBean2);

        ac.close();
    }
    @Scope("singleton")
    static class SingletonBean {
        @PostConstruct
        public void init() {
            System.out.println("singleton.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("singleton.destroy");
        }
    }
}
