package com.example.demo14.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

//로거를 출력하기 위한 클래스.

//[d06b992f...] request scope bean create
//[d06b992f...] [http://localhost:8080/log-demo] controller test
//[d06b992f...] [http://localhost:8080/log-demo] service id =  testId
//[d06b992f...] request scope bean close
// 이렇게 뜨길 예상.

//@Scope(value = "request") 이 빈은 HTTP요청 당 하나씩 생성되고 HTTP요청이 끝나는 시점에 소멸됨.
@Component
@Scope(value = "request")
public class MyLogger {

    private String uuid;
    private String requestURL;

    // requestURL은 빈이 생성되는 시점에는 알수 없으므로, 외부에서 setter로 입력받음.
    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "]" + message);
    }

    // 이 빈이 생성되는 시점에 @PostConstruct 초기화 메서드를 사영해서 uuid를 생성하여 저장.  >> http 요청 당 하나 생성, uuid를 저장해두면 다른 http 요청과 구분가능.
    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create : " + this);
    }

    // 이 빈이 소멸되는 시점에 종료 메세지를 남김.
    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close : " + this);
    }
}
