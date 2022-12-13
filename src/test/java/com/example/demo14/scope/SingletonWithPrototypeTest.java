package com.example.demo14.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//*프로토타입 스코프 - 싱글톤 빈과 함께 사용시 문제점.

public class SingletonWithPrototypeTest {

    //1. A,B가 요청 했을 경우 프로토타입 빈을 각각 생성해서 카운트함(addCount 호출).
    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }
    //생성시점에 주입되어 한개의 프로토 타입이 사용됨,
    @Test
    void singletonClientUserPrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        Assertions.assertThat(count2).isEqualTo(2);
    }

    @Scope("singleton")
    static class ClientBean {
        //ObjectProvider 지정한 빈을 컨테이너에서 대신 찾아주는 DL서비스.
        //prototypeBeanProvider.getObject() 를 통해 항상 새로운 빈이 생성돠는 것을 확인할 수 있음.
        //ObjectProvider의 getObject()를 호출하면 내부에서는 스프링 컨테이너를 통해 해당 빈을 찾아서 반환.(DL)
        @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanProvider;

        public int logic(){
            PrototypeBean prototypeBean = getObject();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }

        private PrototypeBean getObject() {
            return prototypeBeanProvider.getObject();
        }

//        private final PrototypeBean prototypeBean; //생성시점에 주입. X 01

//        @Autowired
//        ApplicationContext applicationContext;

//        @Autowired
//        ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }

//        public int logic(){
////            PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class); // 주입시점 마다 생성.(하길 원함.)
//            prototypeBean.addCount();
//            int count = prototypeBean.getCount();
//            return count;
//        }
    }

//    @Scope("singleton")
//    static class ClientBean2 {
//        private final PrototypeBean prototypeBean; //생성시점에 주입. X 02 >> prototype 이 각각 생성되지만 사용할 때마다 계속 생성되지 않음.
//
//        @Autowired
//        ClientBean2(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }
//
//        public int logic() {
//            prototypeBean.addCount();
//            int count = prototypeBean.getCount();
//            return count;
//        }
//    }


    @Scope("prototype")
    static class PrototypeBean {

        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("prototypeBean.init");
            System.out.println("prototypeBean.init" + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("prototypeBean.destroy");
        }
    }
}
