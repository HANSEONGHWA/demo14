package com.example.demo14.lifecycle;
//*빈 생명주기 콜백
//스프링빈 생성 및 소멸되기 직전에 호출해주는 메서드

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.security.auth.Destroyable;

//가상의 클라이언트
public class NetworkClient /*implements InitializingBean, DisposableBean*/ {

    // 접속해야하는 서버의 url
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        connect(); /*객체가 상성될 떄*/
        call("초기화 연결 메세지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect = " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + "message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close = " + url);
    }

    //1.빈 생명주기 콜백 지원 (인터페이스 implements InitializingBean, DisposableBean)
    //InitializingBean 초기화 빈
    //프로퍼티 셋팅이 끝나면 (의존관계주입이 끝나면) 호출됨.

    //초기화, 소멸 인터페이스 단점..
    //1.스프링 전용 인터페이스에 의존, 2.초기화, 소멸 메서드의 이름을 변경할 수 없음. 3.내가 코드를 고칠 수 없는 외부 라이브러리에 적용할 수 없음.
    //잘 사용하지 않음.
//    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메세지");
    }

    //DisposableBean
    //빈 종료 시 호출 됨.
//    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }

    // 2.빈 생명주기 콜백 지원 (빈 등록 초기화, 소멸 메서드)
    //설정 정보에 @Bean(initMethod = "init", destroyMethod = "close") 처럼 초기화, 소멸 메서드를 지정.

    //설정정보 사용 특징
    //1.메서드 이름 자유롭게 부여 가능, 2.스프링 빈, 스프링 코드에 의존하지 않음. 3.설정정보를 사용하기 때문에 코드를 고칠 수 없는 외부 라이브러리에서 적용할 수 없음.
    @PostConstruct
    public void init(){
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메세지");
    }
    @PreDestroy
    public void close(){
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
   // 3. 빈 생명주기 콜백 지원 (애노테이션 @PostConstruct, @PreDestory )
    //최신 스프링에서 권장하는 방법.
    //자바 표준 기술이라 다른 컨테이너에서도 동작.
    //외부라이브러리에는 적용 불가.

}
